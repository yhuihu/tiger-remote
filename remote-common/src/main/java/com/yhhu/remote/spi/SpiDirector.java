package com.yhhu.remote.spi;

import com.yhhu.remote.exception.RemoteException;
import com.yhhu.remote.spi.strategy.LoadingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.ServiceLoader.load;
import static java.util.stream.StreamSupport.stream;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description SPI大工厂
 */
public class SpiDirector {

    // 记录成功发现的类型
    private final ConcurrentMap<Class<?>, SpiLoader> spiLoadersMap = new ConcurrentHashMap<>(64);
    // 记录无法发现的类型
    private final List<Class<?>> spiLoaderNotFoundList = new ArrayList<>();

    private static volatile List<LoadingStrategy> strategies = loadLoadingStrategies();

    private static volatile SpiDirector spiDirector;

    // 禁止自己创建该对象
    private SpiDirector() {

    }

    /**
     * 获取SPI实现类
     *
     * @param type 类型
     * @param <T>  泛型
     * @return {@link  SpiLoader}
     */
    public <T> SpiLoader<T> getSpiLoader(Class<T> type) {
        if (spiLoadersMap.get(type) == null && spiLoaderNotFoundList.contains(type)) {
            throw new RemoteException("SpiDirector not found class : " + type.getName());
        } else {
            // TODO 加载spi
            SpiLoader spiLoader = doFindSpiLoader(type);
            if (spiLoader != null) {
                spiLoadersMap.put(type, spiLoader);
                return spiLoader;
            }
            throw new RemoteException("SpiDirector not found class : " + type.getName());
        }
    }

    /**
     * spi读取方法
     *
     * @param type
     * @param <T>
     * @return
     */
    private <T> SpiLoader doFindSpiLoader(Class<T> type) {

    }

    private static List<LoadingStrategy> loadLoadingStrategies() {
        return stream(load(LoadingStrategy.class).spliterator(), false).sorted().collect(Collectors.toList());
    }

    /**
     * 单例
     *
     * @return {@link SpiDirector}
     */
    public static SpiDirector getInstance() {
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
