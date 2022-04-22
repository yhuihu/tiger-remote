package com.yhhu.remote.spi.strategy;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description 加载策略
 */
public interface LoadingStrategy extends Prioritized {

    /**
     * 返回实现类SPI扫描路径
     *
     * @return {@link String}
     */
    String directory();

    /**
     * 用以读取spi的文件名，默认为类名
     *
     * @return {@link Class}
     */
    default Class<?> strategyClass() {
        return this.getClass();
    }
}
