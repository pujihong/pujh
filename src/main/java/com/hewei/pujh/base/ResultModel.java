package com.hewei.pujh.base;

import java.util.HashMap;
import java.util.Map;

public class ResultModel {
    public static final int SUCCESS = 0;
    public static final int UNKNOWN_ERROR = -1001;
    public static final int USERNAME_OR_PASSWORD_ERROR = -1002;
    public static final int USER_NOT_EXIST_ERROR = -1003;
    public static final int USER_TYPE_NOT_EXIST = -1004;
    public static final int SERVICE_EXCEPTION_ERROR = -1005;
    public static final int WRONG_PARAMS_ERROR = -1006;
    public static final int OLD_PASSWORD_ERROR = -1007;
    public static final int OP_FAILED_ERROR = -1008;
    public static final int PASSWORD_AS_SAME = -1009;
    public static final int IDNO_EXIST = -1010;


    private static Map<Integer, String> codeMap = new HashMap<>();

    static {
        codeMap.put(SUCCESS, "成功");
        codeMap.put(UNKNOWN_ERROR, "未知错误");
        codeMap.put(USERNAME_OR_PASSWORD_ERROR, "用户名或密码错误");
        codeMap.put(USER_NOT_EXIST_ERROR, "用户不存在");
        codeMap.put(USER_TYPE_NOT_EXIST, "用户类型错误");
        codeMap.put(SERVICE_EXCEPTION_ERROR, "服务异常");
        codeMap.put(WRONG_PARAMS_ERROR, "参数设置错误");
        codeMap.put(OLD_PASSWORD_ERROR, "原密码错误");
        codeMap.put(OP_FAILED_ERROR, "操作失败");
        codeMap.put(PASSWORD_AS_SAME, "新密码不能和旧密码一样");
    }

    /**
     * 返回码
     */
    private int code;
    /**
     * 返回结果描述
     */
    private String message;
    /**
     * 返回对象
     */
    private Object data;

    private boolean encode = false;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isEncode() {
        return encode;
    }

    public void setEncode(boolean encode) {
        this.encode = encode;
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public ResultModel(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultModel success(Object data) {
        return new ResultModel(SUCCESS, "成功", data);
    }

    public static ResultModel success() {
        return new ResultModel(SUCCESS, "成功");
    }

    public static ResultModel error() {
        return new ResultModel(-1001, "未知错误");
    }

    public static ResultModel error(int errorCode) {
        String errorMessage = codeMap.get(errorCode);
        if (errorMessage == null) {
            errorMessage = codeMap.get(UNKNOWN_ERROR);
        }
        return new ResultModel(errorCode, errorMessage);
    }

    public static ResultModel error(String errorMessage) {
        return new ResultModel(UNKNOWN_ERROR, errorMessage);
    }

    public static ResultModel error(int errorCode, String errorMessage) {
        return new ResultModel(errorCode, errorMessage);
    }
}
