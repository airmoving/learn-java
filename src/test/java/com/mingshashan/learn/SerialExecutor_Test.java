package com.mingshashan.learn;

import com.mingshashan.learn.threadpool.SerialExecutor;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executor;

public class SerialExecutor_Test {

    Executor executor;

    @Before
    public void prepare() {
        executor = new Executor() {
            public void execute(final Runnable command) {
                Thread thread = new Thread(command);
                thread.start();
            }
        };
    }

    @Test
    public void test_01() {
        SerialExecutor serialExecutor = new SerialExecutor(executor);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            serialExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI);
                }
            });
        }

    }
}
