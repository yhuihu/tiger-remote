package com.yhhu.remote.config;

import com.yhhu.remote.constant.RemoteConstant;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description 配置类
 */
public class RemoteConfig {

    // ip地址
    private String remoteHost = RemoteConstant.DEFAULT_SERVER_HOST;

    // 端口
    private String remotePort = RemoteConstant.DEFAULT_SERVER_PORT;

    // accepted线程个数，一个端口只需要一个线程
    private int bossThread = 1;

    // netty运行线程数
    private int workThread = Runtime.getRuntime().availableProcessors() * 2;


}
