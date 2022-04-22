package com.yhhu.remote.spi;

import com.yhhu.remote.constant.RemoteConstant;
import com.yhhu.remote.exception.RemoteException;
import com.yhhu.remote.spi.strategy.LoadingStrategy;
import com.yhhu.remote.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static java.util.ServiceLoader.load;
import static java.util.stream.StreamSupport.stream;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description SPI大工厂
 */
public class SpiDirector {

    private static final Logger logger = LoggerFactory.getLogger(SpiDirector.class);

    // 记录成功发现的类型
    private final ConcurrentMap<Class<?>, SpiLoader> spiLoadersMap = new ConcurrentHashMap<>(64);

    private static volatile List<LoadingStrategy> strategies = loadLoadingStrategies();

    private static volatile SpiDirector spiDirector;

    // 禁止自己创建该对象
    private SpiDirector() throws IOException {
        // 开始加载spi
        for (LoadingStrategy strategy : strategies) {
            try {
                logger.info("begin to load strategy : {}", strategy.strategyClass().getName());
                doRegisterSpiLoader(strategy);
                logger.info("finish load strategy : {}", strategy.strategyClass().getName());
            } catch (Exception ex) {
                logger.info("load strategy {} error :", strategy.strategyClass().getName(), ex);
            }
        }
    }

    /**
     * 根据策略加载SPI实现类
     *
     * @param strategy
     */
    private void doRegisterSpiLoader(LoadingStrategy strategy) throws IOException {
        String directory = strategy.directory();
        Class<?> aClass = strategy.strategyClass();
        if (directory.endsWith(RemoteConstant.SPI_SPLIT)) {
            directory = directory + aClass.getName();
        }
        SpiLoader loader = spiLoadersMap.get(aClass);
        if (loader == null) {
            loader = new SpiLoader();
            spiLoadersMap.put(aClass, loader);
        }
        Enumeration<URL> resources = ClassLoader.getSystemResources(directory);
        if (resources != null) {
            while (resources.hasMoreElements()) {
                loadFromResource(loader, resources.nextElement());
            }
        }
    }

    /**
     * 从resources中加载k/v格式的类
     *
     * @param spiLoader   SPI缓存加载类
     * @param resourceURL 资源地址
     */
    private void loadFromResource(SpiLoader spiLoader, URL resourceURL) {
        try {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceURL.openStream(), StandardCharsets.UTF_8))) {
                String line;
                String clazz;
                while ((line = reader.readLine()) != null) {
                    final int ci = line.indexOf('#');
                    if (ci >= 0) {
                        line = line.substring(0, ci);
                    }
                    line = line.trim();
                    if (line.length() > 0) {
                        try {
                            String name = null;
                            int i = line.indexOf('=');
                            if (i > 0) {
                                name = line.substring(0, i).trim();
                                clazz = line.substring(i + 1).trim();
                            } else {
                                clazz = line;
                            }
                            if (StringUtils.isNotEmpty(clazz)) {
                                cacheClass(spiLoader, Class.forName(clazz, true, spiLoader.getClass().getClassLoader()), name);
                            }
                        } catch (Throwable t) {
                            logger.error("Failed to load extension class (class line: " + line + ") in " + resourceURL + ", cause: " + t.getMessage(), t);
                        }
                    }
                }
            }
        } catch (Throwable t) {
            logger.error("Exception occurred when loading extension class (class file: " + resourceURL + ") in " + resourceURL, t);
        }
    }

    /**
     * 缓存SPI类
     *
     * @param spiLoader
     * @param forName
     * @param name
     */
    private void cacheClass(SpiLoader spiLoader, Class<?> forName, String name) {
        spiLoader.cacheClass(name, forName);
    }

    /**
     * 获取SPI实现类
     *
     * @param type 类型
     * @param <T>  泛型
     * @return {@link  SpiLoader}
     */
    public <T> SpiLoader<T> getSpiLoader(Class<T> type) {
        SpiLoader loader = spiLoadersMap.get(type);
        if (loader == null) {
            throw new RemoteException("SpiDirector not found class : " + type.getName());
        } else {
            return loader;
        }
    }

    /**
     * 拿到定义在service下的所有spi加载类
     *
     * @return
     */
    private static List<LoadingStrategy> loadLoadingStrategies() {
        return stream(load(LoadingStrategy.class).spliterator(), false).sorted().collect(Collectors.toList());
    }

    /**
     * 单例
     *
     * @return {@link SpiDirector}
     */
    public static SpiDirector getInstance() throws IOException {
        if (SpiDirector.spiDirector == null) {
            synchronized (SpiDirector.class) {
                if (SpiDirector.spiDirector == null) {
                    SpiDirector.spiDirector = new SpiDirector();
                }
            }
        }
        return SpiDirector.spiDirector;
    }

}
