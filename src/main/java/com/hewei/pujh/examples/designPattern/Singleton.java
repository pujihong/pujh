package com.hewei.pujh.examples.designPattern;

/**
 * 单例模式
 */
public class Singleton {

    // 懒汉式
/*    private static Singleton instance;
    private Singleton () {}
    public static Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }*/

    // 饿汉式
/*
    private static Singleton instance = new Singleton();
    private Singleton() {}

    private static Singleton getInstance() {
        return instance;
    }
*/
    // 双重校验锁实现对象单例
    private volatile static Singleton instance;

    private Singleton() {
    }

    private static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
