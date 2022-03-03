package com.mingshashan.learn.juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MyThreadPool_V1 implements MyExecutorService {

    private int capacity;
    private int currentCapacity;

    BlockingQueue<Runnable> blockingQueue;
    Execution execution;


    public MyThreadPool_V1(int capacity) {
        this.capacity = capacity;
        currentCapacity = 0;
        blockingQueue = new LinkedBlockingDeque<>();
        execution = new Execution();
    }

    @Override
    public void submit(Runnable runnable) {
        blockingQueue.offer(runnable);
        execution.executeTask();
    }

    final class Execution extends Thread {
        void executeTask() {
            if (currentCapacity < capacity) {
                currentCapacity++;
                Thread t = new Thread(new Execution(), "currentCapacity - " + currentCapacity);
                t.start();
            }
        }

        @Override
        public void run() {
            while (true) {
                if (!blockingQueue.isEmpty()) {
                    blockingQueue.poll().run();
                }
            }
        }
    }
}
