package com.hewei.pujh.examples.java.thread;

/*
 *
 * */
public class ThreadLocalDemo {

    private static int count = 0;

    static void addCount() {
        count++;
    }

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                addCount();
            }
        }, "线程1");

        Thread b = new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                addCount();
            }
        }, "线程2");
        a.start();
        b.start();
        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count:" + count);
    }
}
