package com.yhhu.remote.protocol;

import com.yhhu.remote.exception.RemoteException;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description
 */
public abstract class AbstractProtocol implements Protocol {
    @Override
    public byte[] encode(Object o) throws RemoteException {
        if (o == null) {
            throw new RemoteException("encode param null is not allow");
        }
        try {
            return doEncode(o);
        } catch (Exception ex) {
            throw new RemoteException("process encode throw exception : ", ex);
        }

    }

    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) throws RemoteException {
        try {
            return doDecode(bytes, clazz);
        } catch (Exception ex) {
            throw new RemoteException("process encode throw exception : ", ex);
        }
    }

    public abstract byte[] doEncode(Object obj);

    public abstract <T> T doDecode(byte[] bytes, Class<T> t);
}
