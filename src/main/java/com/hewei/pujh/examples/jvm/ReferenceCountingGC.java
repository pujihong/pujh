package com.hewei.pujh.examples.jvm;

/**
 * 引用计算算法的缺陷
 * 当两个对象相互引用时不会被GC回收
 */

/**
 *  配置VM参数
 * -verbose:gc
 * -Xms20m
 * -Xmx20m
 * -Xmn10m
 * -XX:+PrintGCDetails
 * -XX:SurvivorRatio=8
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;
    /**
     * 这个成员属性的唯一意义就是占点内存，以便能在GC日志中看清楚是否有回收过
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        // 假设在这行发生GC，objA和objB是否能被回收？
         System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }

    /*
     * 虚拟机并没有因为这两个对象互相引用就放弃回收它们，
     * 这也从侧面说明了Java虚拟机并不是通过引用计数算法来判断对象 是否存活的。
     */
}