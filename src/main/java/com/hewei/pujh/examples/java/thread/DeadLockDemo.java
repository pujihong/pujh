package com.hewei.pujh.examples.java.thread;

/*
* 线程 A 通过 synchronized (resource1) 获得 resource1 的监视器锁，然后通过Thread.sleep(1000);让线程 A 休眠 1s 为的是让线程 B 得到执行然后获取到 resource2 的监视器锁。线程 A 和线程 B 休眠结束了都开始企图请求获取对方的资源，然后这两个线程就会陷入互相等待的状态，这也就产生了死锁。上面的例子符合产生死锁的四个必要条件。
学过操作系统的朋友都知道产生死锁必须具备以下四个条件：
互斥条件：该资源任意一个时刻只由一个线程占用。
请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
不剥夺条件:线程已获得的资源在末使用完之前不能被其他线程强行剥夺，只有自己使用完毕后才释放资源。
循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。
* */
public class DeadLockDemo {
    // 资源
    private static Object resource1 = new Object();
    private static Object resource2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
           synchronized (resource1) {
               System.out.println(Thread.currentThread() + " get resource1");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(Thread.currentThread() + " waiting get resource2");
               synchronized (resource2) {
                   System.out.println(Thread.currentThread() + " get resource2");
               }
           }
        },"线程 -1").start();

        new Thread(() -> {
            synchronized (resource2) {  // 改为synchronized (resource1)  即可避免死锁
                System.out.println(Thread.currentThread() + " get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + " get resource1");
                }
            }
        },"线程 -2").start();
    }
}
