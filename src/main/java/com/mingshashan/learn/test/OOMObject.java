package com.mingshashan.learn.test;

import java.util.ArrayList;
import java.util.List;

public class OOMObject {

    public byte[] placeHolder = new byte[64 * 1024];

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i <= num; i++) {
            Thread.sleep(500);
            list.add(new OOMObject());
        }
        System.gc();
        while (true) {
            Thread.sleep(10000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}
