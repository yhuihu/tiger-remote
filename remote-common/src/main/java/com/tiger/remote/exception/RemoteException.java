package com.tiger.remote.exception;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description
 */
public class RemoteException extends RuntimeException {

    public RemoteException() {
    }

    public RemoteException(Throwable cause) {
        super(cause);
    }

    public RemoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public RemoteException(String message) {
        super(message);
    }

}
