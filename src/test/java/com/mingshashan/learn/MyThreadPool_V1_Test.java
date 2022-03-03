package com.mingshashan.learn;

import com.mingshashan.learn.juc.MyExecutorService;
import com.mingshashan.learn.juc.MyThreadPool_V1;
import org.junit.Test;

public class MyThreadPool_V1_Test {

    @Test
    public void test_01() {
        MyExecutorService myThreadPool = new MyThreadPool_V1(10);

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            myThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            });
        }
    }
}
