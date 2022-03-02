package com.mingshashan.learn.juc;

public interface MyExecutorService {

    void submit(Runnable runnable);
}
