package com.mingshashan.learn.longpolling.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class LongPollingServlet extends HttpServlet {

    private Random random = new Random();
    private AtomicLong count = new AtomicLong(0);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long i = count.getAndIncrement();
        System.out.printf("第 %n 次 long polling\n", i);

        int seconds = random.nextInt(100);
        System.out.printf("sleep %n seconds\n", seconds);
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.interrupted();
        }
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(String.valueOf(i));
    }
}
