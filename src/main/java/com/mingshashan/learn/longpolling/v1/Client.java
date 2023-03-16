package com.mingshashan.learn.longpolling.v1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

public class Client {

    public static final String REQUEST_URL =
            "http://localhost:8888/long-polling";

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        Client client = new Client();
        client.longPolling();
    }

    private void longPolling() {
        while (true) {
            int i = atomicInteger.getAndIncrement();
            System.out.printf("第 %n 次 long polling\n", i);
            HttpURLConnection connection = null;
            try {
                URL url = new URL(REQUEST_URL);
                connection = (HttpURLConnection) url.getContent();
                connection.setReadTimeout(50000);
                connection.setConnectTimeout(3000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept-Charset", "UTF-8");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Charset", "UTF-8");
                connection.connect();
                if (200 == connection.getResponseCode()) {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String msg;
                        while ((msg = reader.readLine()) != null) {
                            sb.append(msg);
                        }
                        System.out.printf("第 %n 次收到的消息为%s\n", i, sb.toString());
                    } finally {
                        if (null != reader) {
                            reader.close();
                        }
                    }
                }
            } catch (Exception e) {

            } finally {
                if (null != connection) {
                    connection.disconnect();
                }
            }

        }
    }
}
