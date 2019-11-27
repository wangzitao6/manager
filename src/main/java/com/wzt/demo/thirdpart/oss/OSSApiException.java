package com.wzt.demo.thirdpart.oss;

/**
 * OSS异常
 * @author wangzitao
 * @create 2018-06-02 10:28
 */
public class OSSApiException extends  RuntimeException {
    public OSSApiException(String messag) {
        super(messag);
    }
}
