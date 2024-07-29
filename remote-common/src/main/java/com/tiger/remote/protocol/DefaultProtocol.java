package com.tiger.remote.protocol;

import com.tiger.remote.code.CodingLoadStrategy;
import com.tiger.remote.exception.RemoteException;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description
 */
public class DefaultProtocol extends AbstractProtocol {

    private final CodingLoadStrategy codingLoadStrategy = new CodingLoadStrategy();


    @Override
    public byte[] doEncode(Object obj) {
        return new byte[0];
    }

    @Override
    public <T> T doDecode(byte[] bytes, Class<T> t) {
        return null;
    }
}
