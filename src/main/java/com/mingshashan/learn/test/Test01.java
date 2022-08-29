package com.mingshashan.learn.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Test01 {
    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        List<String> result = new ArrayList<>();
        long start = System.currentTimeMillis();
        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {

            int finalI = i;
            Callable<String> stringCallable = () -> {
                int random = new Random().nextInt(2000);
                System.out.printf("i [%d] 睡眠时间 [%d]\n", finalI, random);
                Thread.sleep(random);
                return "i-" + finalI;
            };

            Future<String> future = threadPoolExecutor.submit(stringCallable);
            futureList.add(future);

        }

        for (Future<String> future : futureList) {
            try {
                result.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        System.out.printf("总耗时 = [%d]\n", (end - start));
        System.out.println(result.toArray(new String[]{}));
        threadPoolExecutor.shutdown();
    }
}
