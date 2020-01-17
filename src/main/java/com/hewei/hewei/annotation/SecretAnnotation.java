package com.hewei.hewei.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对前端加密过的数据进行解密
 * 默认不加解密
 * 对返回的参数进行加密
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SecretAnnotation {
    boolean encode() default false;
    boolean decode() default false;
}
