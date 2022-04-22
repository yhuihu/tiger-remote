package com.yhhu.remote.spi;

import com.yhhu.remote.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description SPI加载实现
 */
public class SpiLoader<T> {

    private volatile Map<String, T> classMap = new ConcurrentHashMap<>();

    public static <T> T load(Class<T> type, String name) throws Throwable {
        try {
            SpiLoader<T> spiLoader = SpiDirector.getInstance().getSpiLoader(type);
            return spiLoader.load(name);
        } catch (Exception ex) {
            throw new Throwable("spi load error !!!!!! please see the stack information to fix.", ex);
        }
    }

    private T load(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException(String.format("SpiLoader class:%s == null", name));
        }
        T tClass = classMap.get(name);
        if (tClass != null) {
            return tClass;
        }
        throw new IllegalArgumentException(String.format("SpiLoader class:%s == null", name));
    }

    public void cacheClass(String className, T t) {
        classMap.put(className, t);
    }
}
