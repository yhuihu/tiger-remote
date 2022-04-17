package com.yhhu.remote.spi;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description 加载策略
 */
public interface LoadingStrategy {
    // spi扫描路径
    String directory();
}
