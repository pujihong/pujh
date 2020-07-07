package com.hewei.pujh.base;

import com.hewei.pujh.enums.ErrorCodeEnum;

public class ResultModel {

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
        return new ResultModel(ErrorCodeEnum.SUCCESS.getCode(), "成功", data);
    }

    public static ResultModel success() {
        return new ResultModel(ErrorCodeEnum.SUCCESS.getCode(), "成功");
    }

    public static ResultModel error() {
        return new ResultModel(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), "未知错误");
    }

    public static ResultModel error(int errorCode) {
        ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.toEnum(errorCode);
        String errorMessage;
        if (errorCodeEnum == null) {
            errorMessage = ErrorCodeEnum.UNKNOWN_ERROR.getMsg();
        } else {
            errorMessage = errorCodeEnum.getMsg();
        }
        return new ResultModel(errorCode, errorMessage);
    }

    public static ResultModel error(String errorMessage) {
        return new ResultModel(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), errorMessage);
    }

    public static ResultModel error(int errorCode, String errorMessage) {
        return new ResultModel(errorCode, errorMessage);
    }
}
