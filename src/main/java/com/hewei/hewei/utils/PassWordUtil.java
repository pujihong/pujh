package com.hewei.hewei.utils;

import com.hewei.hewei.base.Constant;
import org.springframework.util.DigestUtils;

public class PassWordUtil {

    public static String encodePassword(String account, String pwd) {
        String text = DigestUtils.md5DigestAsHex(Constant.SALT.getBytes()) +
                DigestUtils.md5DigestAsHex(account.getBytes()) +
                DigestUtils.md5DigestAsHex(pwd.getBytes());
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }

    /**
     * 初始化密码 123456
     *
     * @param account 用户账号
     * @return string
     */
    public static String initPassword(String account) {
        return encodePassword(account, "123456");
    }

    public static void main(String[] args) {
        System.out.println(PassWordUtil.encodePassword("admin", "admin"));
    }
}
