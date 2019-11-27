package com.wzt.demo.config;

/**
 * 需要展示给客户端信息的异常类型.
 *
 * @author wangzitao
 * @create 2018-05-17 10:23
 **/
public class ShowClientServiceException extends RuntimeException {
    public ShowClientServiceException(String message) {
        super(message);
    }
    public ShowClientServiceException(String message, Throwable cause) {
    	super(message, cause);
    }
    
}
