package com.tjetc.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.exception.CustomerException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalHandleExceptionController {

    /**
     * 处理无权限异常，返回JsonResult对象对应的json字符串
     * @return
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public JsonResult unauthorizedException() {
        return new JsonResult(1, "权限不足", null);
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public JsonResult exception(Exception e) {
        System.out.println(e);
        return new JsonResult(1, "系统出错了", null);
    }

    @ExceptionHandler(CustomerException.class)
    @ResponseBody
    public JsonResult customerException(CustomerException e) {
        System.out.println(e);
        return new JsonResult(1, e.getMessage(), null);
    }
}
