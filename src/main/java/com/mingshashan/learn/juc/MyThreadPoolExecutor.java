package com.mingshashan.learn.juc;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPoolExecutor extends AbstractExecutorService {

    private volatile AtomicInteger threadCount = new AtomicInteger();
    private volatile AtomicInteger state = new AtomicInteger(1);
    private volatile Lock lock = new ReentrantLock();
//    Condition
    private static int RUNNING = 0b0000001;
    private static int SHUTDOWN = 0b0000010;
    private static int STOP = 0b0000100;
    private static int TIDYING = 0b0001000;
    private static int terminated = 0b0010000;

    private int corePoolSize;
    private int maxPoolSize;
    private long idleTime;
    private TimeUnit timeUnit;
    private BlockingQueue taskQueue;
    private ThreadFactory threadFactory;
    private RejectedExecutionHandler rejectedExecutionHandler;

    public MyThreadPoolExecutor(int corePoolSize, int maxPoolSize, long idleTime, TimeUnit timeUnit, BlockingQueue taskQueue, ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.idleTime = idleTime;
        this.timeUnit = timeUnit;
        this.taskQueue = taskQueue;
        this.threadFactory = threadFactory;
        this.rejectedExecutionHandler = rejectedExecutionHandler;
    }

    public MyThreadPoolExecutor(int corePoolSize, int maxPoolSize, long idleTime, TimeUnit timeUnit, BlockingQueue taskQueue) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.idleTime = idleTime;
        this.timeUnit = timeUnit;
        this.taskQueue = taskQueue;
        this.threadFactory = new DefaultThreadFactory();
        this.rejectedExecutionHandler = new DefaultRejectedExecutionHandler();
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    /**
     * 提交执行任务
     * 模仿ThreadPoolExecutor
     * 1、if corePoolSize > currentThreads
     * new Worker and execute
     * 2、if corePoolSize = currentThreads and the taskQueue is not full
     * add task to taskQueue
     * 3、otherwise if corePoolSize = currentThread and the taskQueue is
     *
     * @param command
     */
    @Override
    public void execute(Runnable command) {
        if (!isRunning()) {
            throw new IllegalStateException("MyThreadPoolExecutor is not running");
        }
        if (getCurrentThreadCount() < corePoolSize) {
            
        }


    }

    private boolean isRunning() {
        return RUNNING == (state.get() & RUNNING);
    }

    private int getCurrentThreadCount() {
        return threadCount.get();
    }

    class Worker implements Runnable {
        Runnable firstTask;
        Thread thread;

        public Worker(Runnable r) {
            firstTask = r;
            thread = threadFactory.newThread(this);
        }

        @Override
        public void run() {
            runWorker(this);
        }
    }

    private void runWorker(Runnable runnable) {

    }

    static class DefaultThreadFactory implements ThreadFactory {

        ThreadGroup threadGroup = new ThreadGroup("MyThreadPoolExecutor");
        private static final AtomicInteger poolNumber = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(threadGroup, r, threadGroup.getName() + "-" + poolNumber.getAndIncrement() + "-pool-");
        }
    }

    static class DefaultRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            throw new RejectedExecutionException("Task " + r.toString() + " rejected from " + executor.toString());
        }
    }
}
