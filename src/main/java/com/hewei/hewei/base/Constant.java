package com.hewei.hewei.base;

public class Constant {

    /**
     * 存储当前登录用户id的字段名
     */
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR =  2;

    /**
     * 增加的token前缀
     */
    public static final String TOKEN_PREFIX = "TOKEN_USER_";

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";

    /*
     *  加密用的盐
     * */
    public static final String SALT = "hewei";

}
