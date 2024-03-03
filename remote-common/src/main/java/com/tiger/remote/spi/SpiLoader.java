package com.tiger.remote.spi;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description SPI加载实现
 */
public class SpiLoader<T> {


    public static <T> T get(Class<T> type, String name) {
        SpiInstance<T> spiLoader = SpiDirector.getInstance().getSpiInstance(type);
        return spiLoader.get(name);
    }

    public static <T> void put(Class<T> type, T instance, String name) {
        SpiInstance<T> spiLoader = SpiDirector.getInstance().getSpiInstance(type);
        spiLoader.put(name, instance);
    }
}
