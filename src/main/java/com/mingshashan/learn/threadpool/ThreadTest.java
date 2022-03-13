package com.mingshashan.learn.threadpool;

import java.util.concurrent.*;

/**
 * ThreadTest
 *
 * @author mingshashan
 */
public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

//        new ThreadTest().threadCreateTest();
        new ThreadTest().threadPoolTest();


    }

    public void threadCreateTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            final int x = i;
            new Thread(() -> {
                System.out.println("thread-" + x);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
    }


    public void threadPoolTest() throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        ThreadPoolExecutor threadPoolExecutor = new
                ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 1000; i++) {
            final int x = i;
            threadPoolExecutor.submit(() -> {
                System.out.println("thread-" + x);
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
        threadPoolExecutor.shutdown();
    }
}
