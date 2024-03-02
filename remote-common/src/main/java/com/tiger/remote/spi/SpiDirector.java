package com.tiger.remote.spi;

import com.tiger.remote.exception.RemoteException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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

            throw new RemoteException("SpiDirector not found class : " + type.getName());
        }
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
