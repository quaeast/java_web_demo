package cn.quaeast;

import java.util.concurrent.Executors;

public class Test {
    public static void doSomething() {
        final int value = 0;
//        value++;
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(value);
            }
        });
    }

    public static void main(String[] args) {
        doSomething();
    }
}
