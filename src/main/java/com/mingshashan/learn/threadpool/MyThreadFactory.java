package com.mingshashan.learn.threadpool;

/**
 * MyThreadFactory
 *
 * @author mingshashan
 */
public interface MyThreadFactory {

    Thread newThread(Runnable runnable);
}
