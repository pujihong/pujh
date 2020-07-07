package com.hewei.pujh.enums;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    SUCCESS(0,"成功"),
    UNKNOWN_ERROR(-1001, "未知错误"),
    USERNAME_OR_PASSWORD_ERROR(-1002, "用户名或密码错误"),
    USER_NOT_EXIST_ERROR(-1003, "用户不存在"),
    SERVICE_EXCEPTION_ERROR(-1004, "服务异常"),
    WRONG_PARAMS_ERROR(-1005, "参数设置错误"),
    OP_FAILED_ERROR(-1006, "操作失败");

    private Integer code;
    private String msg;

    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCodeEnum toEnum(Integer value) {
        for (ErrorCodeEnum e : ErrorCodeEnum.values()) {
            if (e.getCode().equals(value)) {
                return e;
            }
        }
        return null;
    }

}
