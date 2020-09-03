package com.hewei.pujh.examples.java.classInit;

public class ChildClass extends FatherClass{
    // 静态代码块
    static {
        System.out.println("子类---静态代码块1");
    }
    // 静态变量
    private static String staticField = "子类---静态变量";
    // 变量
    private String field = "子类---变量";
    // 静态代码块
    static {
        System.out.println(staticField);
        System.out.println("子类---静态代码块2");
    }
    {
        System.out.println(field);
        System.out.println("子类---普通初始化块");
    }

    public ChildClass() {
        System.out.println("子类---构造方法");
    }

    public static void main(String[] args) {
        System.out.println("子类---main方法");
        new ChildClass();
    }
}
