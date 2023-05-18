package com.mingshashan.learn.java11;

import java.util.ArrayList;

public class Foo {
    public static void main(String[] args) {
        new Foo().foo();
    }

    public void foo() {
        var value = 1;
        var list = new ArrayList<>();
        list.add(value);
        list.add(1);
    }
}
