package com.mingshashan.learn.juc;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MyThreadPool implements MyExecutorService {

    private int capacity;
    private int currentCapacity;

    BlockingQueue<Runnable> blockingQueue;


    public MyThreadPool(int capacity) {
        this.capacity = capacity;
        currentCapacity = 0;
        blockingQueue = new LinkedBlockingDeque<>();

    }

    @Override
    public void submit(Runnable runnable) {

    }

    final class Execution extends Thread {
        void executeTask() {

        }

        @Override
        public void run() {
            while (true) {
                if (!blockingQueue.isEmpty()) {

                }
            }
        }
    }
}
