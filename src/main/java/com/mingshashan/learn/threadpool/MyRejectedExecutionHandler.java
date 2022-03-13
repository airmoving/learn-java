package com.mingshashan.learn.threadpool;

/**
 * MyRejectedExecutionHandler
 *
 * @author mingshashan
 */
public interface MyRejectedExecutionHandler {

    void rejectedExecution(Runnable runnable, MyThreadPoolExecutor myThreadPoolExecutor);
}
