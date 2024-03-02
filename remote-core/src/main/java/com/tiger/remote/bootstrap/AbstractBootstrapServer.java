package com.tiger.remote.bootstrap;

import com.tiger.remote.LifeCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * @author yhhu
 * @date 2022/4/16
 * @description
 */
public abstract class AbstractBootstrapServer implements LifeCycle<String> {

    private final static Logger logger = LoggerFactory.getLogger(AbstractBootstrapServer.class);

    @Override
    public void start(String... objects) {
        logger.info("tiger remote start begin : {}" , LocalDate.now());
        doStart();
        logger.info("tiger remote start finish : {}" , LocalDate.now());
    }

    protected abstract void doStart();

    protected abstract void doDestroy();

    @Override
    public void end(String... objects) {
        logger.info("tiger remote destroy begin : {}" , LocalDate.now());
        doDestroy();
        logger.info("tiger remote destroy finish : {}" , LocalDate.now());
    }
}
