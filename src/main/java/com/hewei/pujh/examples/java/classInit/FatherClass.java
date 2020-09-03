package com.hewei.pujh.examples.java.classInit;

public class FatherClass {
    // 静态代码块
    static {
        System.out.println("父类---静态代码块1");
    }
    // 静态变量
    private static String staticField = "父类---静态变量";
    // 变量
    private String field = "父类---变量";

    // 静态代码块
    static {
        System.out.println(staticField);
        System.out.println("父类---静态代码块2");
    }
    {
        System.out.println(field);
        System.out.println("父类---普通初始化块");
    }

    public FatherClass() {
        System.out.println("父类---构造方法");
    }
}
