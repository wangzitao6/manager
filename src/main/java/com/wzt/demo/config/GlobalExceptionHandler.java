package com.wzt.demo.config;

import com.alibaba.fastjson.JSONException;
import com.wzt.demo.bean.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 *
 * @author wangzitao
 * @create 2018-05-17 10:25
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 校验异常的全局处理
     *
     * @param request
     * @param exception
     * @return
     * @throws Exception
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidHandler(MethodArgumentNotValidException exception,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {
        //按需重新封装需要返回的错误信息
        //解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
        Map<String, String> errorData = new HashMap<>(exception.getBindingResult().getErrorCount());
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            if ("POST".equals(request.getMethod()) && error.getField().startsWith("data.")) {
                errorData.put(error.getField().replace("data.", ""), error.getDefaultMessage());
            } else {
                errorData.put(error.getField(), error.getDefaultMessage());
            }
        }
        WebResult rs = WebResult.fail("400", "校验失败" + errorData.values());
        return rs;
    }

    @ResponseBody
    @ExceptionHandler(value = ShowClientServiceException.class)
    public Object showClientServiceErroHandler(HttpServletRequest request, ShowClientServiceException e) {
        WebResult result = WebResult.fail("400", e.getMessage());
        LOGGER.error("", e);
        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Object defaultErrorHandler(HttpServletRequest request, NoHandlerFoundException e) {
        WebResult result = WebResult.fail("404", "请求的接口地址不存在");
        LOGGER.error("", e);
        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public Object serviceErrorHandler(HttpServletRequest request, ServiceException e) {
        WebResult result = WebResult.fail("500", "业务处理发生异常");
        LOGGER.error("业务处理发生异常，请求路径[{}]", request.getRequestURL(), e);
        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Object illegalArgumentException(HttpServletRequest request, IllegalArgumentException e) {
        WebResult result = WebResult.fail("500", "参数不符合要求");
        LOGGER.error("参数不符合要求，请求路径[{}]", request.getRequestURL(), e);
        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = IllegalStateException.class)
    public Object illegalStateException(HttpServletRequest request, IllegalStateException e) {
        WebResult result = WebResult.fail("500", "业务处理发生异常");
        LOGGER.error("语句异常，请求路径[{}]", request.getRequestURL(), e);
        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Object httpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException e) {
        String msg;
        Throwable cause = e.getCause();
        if (cause != null && cause instanceof JSONException) {
            JSONException jsonException = (JSONException) cause;
            msg = jsonException.getMessage();
        } else {
            msg = e.getMessage();
        }

        WebResult result = WebResult.fail("400", msg);
        LOGGER.error("请求接口[{}]参数异常{}", request.getRequestURL(), e.getMessage());
        return result;
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object methodExceptionHandler(HttpServletRequest request, Exception e) {
        WebResult result = WebResult.fail("500", "服务端发生异常");
        LOGGER.error("非业务异常，请求路径[{}]", request.getRequestURL(), e);
        return result;
    }
}