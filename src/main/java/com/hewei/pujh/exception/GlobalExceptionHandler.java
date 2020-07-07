package com.hewei.pujh.exception;

import com.hewei.pujh.base.ResultModel;
import com.hewei.pujh.enums.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/*
*  这里一定要加上rest 不然后转发到error然后被拦截
*  或者加上 @ResponseBody
* */
@RestControllerAdvice
@Slf4j

public class GlobalExceptionHandler {

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public ResultModel runtimeExceptionHandler(RuntimeException ex) {
        log.error("发生运行时异常异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public ResultModel nullPointerExceptionHandler(NullPointerException ex) {
        log.error("发生空指针异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public ResultModel classCastExceptionHandler(ClassCastException ex) {
        log.error("发生类型转换异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public ResultModel iOExceptionHandler(IOException ex) {
        log.error("发生IO异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public ResultModel noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        log.error("发生未知方法异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResultModel indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        log.error("发生数组越界异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResultModel requestNotReadable(HttpMessageNotReadableException ex) {
        log.error("发生400错误异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    public ResultModel requestTypeMismatch(TypeMismatchException ex) {
        log.error("发生400错误异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResultModel requestMissingServletRequest(MissingServletRequestParameterException ex) {
        log.error("发生400错误异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResultModel request405(HttpRequestMethodNotSupportedException ex) {
        log.error("发生405异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ResultModel request406(HttpMediaTypeNotAcceptableException ex) {
        log.error("发生406异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public ResultModel server500(RuntimeException ex) {
        log.error("发生服务器异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //栈溢出
    @ExceptionHandler({StackOverflowError.class})
    public ResultModel requestStackOverflow(StackOverflowError ex) {
        log.error("发生栈溢出异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

    //其他错误
    @ExceptionHandler({Exception.class})
    public ResultModel exception(Exception ex) {
        log.error("发生常异常！原因是：{}", ex.getMessage());
        return ResultModel.error(ErrorCodeEnum.SERVICE_EXCEPTION_ERROR.getCode());
    }

}
