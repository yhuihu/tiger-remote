package com.yhhu.remote.bootstrap;

import com.yhhu.remote.LifeCycle;
import com.yhhu.remote.config.RemoteConfig;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description
 */
public abstract class AbstractBootstrap implements LifeCycle<String> {

    RemoteConfig remoteConfig;

    public AbstractBootstrap() {
        this.remoteConfig = new RemoteConfig();
    }


    public AbstractBootstrap(RemoteConfig config) {
        this.remoteConfig = config;
    }

    @Override
    public void start(String... objects) {

    }

    protected abstract void doStart();

    protected abstract void doDestroy();

    @Override
    public void end(String... objects) {

    }

    // TODO
    protected ChannelInitializer channelInitializer() {
        return new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast();
            }
        };
    }
}
