package com.tiger.remote.spi;


import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SpiInstance<T> {

    private Map<String, T> instanceMap = new ConcurrentHashMap<>();

    public T get(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Extension name == null");
        }
        if (instanceMap.get(name) == null) {
            throw new RuntimeException("instance name not found : " + name);
        }
        return instanceMap.get(name);
    }

    public void put(String name, T instance) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Extension name == null");
        }
        if (instanceMap.get(name) != null) {
            throw new RuntimeException("duplicate instance name not found : " + name);
        }
        instanceMap.put(name, instance);
    }

}
