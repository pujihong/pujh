package com.hewei.pujh.examples.java.classInit;

/*
 *  类加载过程
 *  加载 -> 验证 -> 准备 -> 解析 -> 初始化 -> 使用 -> 卸载
 *
 *  对象的创建过程  初始化  实例化
 *
 *  a. 加载类
    为父类静态属性分配内存并赋值 / 执行父类静态代码段 （按代码顺序）
    为子类静态属性分配内存并赋值 / 执行子类静态代码段 （按代码顺序）
    b. 创建对象
    为父类实例属性分配内存并赋值 / 执行父类非静态代码段 （按代码顺序）
    执行父类构造器
    为子类实例属性分配内存并赋值 / 执行子类非静态代码段 （按代码顺序）
    执行子类构造器
 * */


public class InitClass {
    // 静态变量
    private static String staticField = "静态变量";
    // 变量
    private String field = "变量";

    // 静态代码块
    static {
        System.out.println(staticField);
        System.out.println("静态代码块");
    }
    {
        System.out.println(field);
        System.out.println("普通初始化块");
    }

    public InitClass() {
        System.out.println("构造方法");
    }

    public static void main(String[] args) {
        new InitClass();
    }
}
