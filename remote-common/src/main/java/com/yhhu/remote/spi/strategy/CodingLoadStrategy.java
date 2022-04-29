package com.yhhu.remote.spi.strategy;

import com.yhhu.remote.protocol.Protocol;
import com.yhhu.remote.spi.SpiLoader;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description 编解码加载
 */
public class CodingLoadStrategy implements LoadingStrategy {

    @Override
    public String directory() {
        return "META-INF/protocol/";
    }

    @Override
    public int getPriority() {
        return MAX_PRIORITY;
    }

    public static Protocol getProtocolCoding(Class classType, String name) throws Throwable {
        if (!classType.isAssignableFrom(Protocol.class)) {
            throw new Exception("protocol class must extension AbstractProtocol.class");
        }
        Object load = SpiLoader.load(CodingLoadStrategy.class, name);
        return (Protocol) load;
    }
}
