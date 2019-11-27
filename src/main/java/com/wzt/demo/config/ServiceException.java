package com.wzt.demo.config;

/**
 * 业务异常定义，当业务逻辑某处出错时主动抛出此异常.
 *
 * @author wangzitao
 * @create 2018-05-17 10:23
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
        super("业务异常");
    }

    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    } 
}
