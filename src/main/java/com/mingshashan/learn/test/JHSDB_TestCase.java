package com.mingshashan.learn.test;

public class JHSDB_TestCase {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceOjb = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("down");
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) throws InterruptedException {
        JHSDB_TestCase.Test test = new Test();
        test.foo();
        while (true) {
            Thread.sleep(100000L);
        }
    }
}