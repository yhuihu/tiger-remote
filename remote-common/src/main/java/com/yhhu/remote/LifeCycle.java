package com.yhhu.remote;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description 生命周期
 */
public interface LifeCycle<T> {
    /**
     * 生命周期开始
     *
     * @param objects 自定义参数
     */
    void start(T... objects);

    /**
     * 生命周期结束
     *
     * @param objects 自定义参数
     */
    void end(T... objects);

    /**
     * 加载顺序处理，数值越大越后加载
     *
     * @return
     */
    default int getOrder() {
        return Integer.MAX_VALUE;
    }
}
