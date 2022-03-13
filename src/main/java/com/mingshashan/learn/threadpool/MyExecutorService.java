package com.mingshashan.learn.threadpool;

public interface MyExecutorService {

    void submit(Runnable runnable);
}
