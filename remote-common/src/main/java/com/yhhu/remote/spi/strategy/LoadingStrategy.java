package com.yhhu.remote.spi.strategy;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description 加载策略
 */
public interface LoadingStrategy extends Prioritized {
    // spi扫描路径
    String directory();
}
