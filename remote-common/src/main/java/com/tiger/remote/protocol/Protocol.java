package com.tiger.remote.protocol;

import com.tiger.remote.exception.RemoteException;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description SPI必须实现父类
 */
public interface Protocol {

    /**
     * 对象序列化
     * 可能会抛出
     *
     * @param o 任何对象
     * @return
     */
    byte[] encode(Object o) throws RemoteException;

    /**
     * 字节反序列化为对象
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T decode(byte[] bytes, Class<T> clazz) throws RemoteException;
}
