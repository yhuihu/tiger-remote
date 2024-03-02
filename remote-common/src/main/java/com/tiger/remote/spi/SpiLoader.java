package com.tiger.remote.spi;

import com.tiger.remote.util.StringUtils;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description SPI加载实现
 */
public class SpiLoader<T> {

    public static <T> T getSpiLoader(Class<T> type, String name) {
        SpiLoader<T> spiLoader = SpiDirector.getInstance().getSpiLoader(type);
        return spiLoader.load(name);
    }

    public T load(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Extension name == null");
        }
        return null;
    }
}
