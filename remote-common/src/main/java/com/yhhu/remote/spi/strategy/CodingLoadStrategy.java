package com.yhhu.remote.spi.strategy;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description 编解码加载
 */
public class CodingLoadStrategy implements LoadingStrategy {

    @Override
    public String directory() {
        return "META-INF/remote/";
    }

    @Override
    public int getPriority() {
        return MAX_PRIORITY;
    }
}
