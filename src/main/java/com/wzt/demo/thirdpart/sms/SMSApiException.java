package com.wzt.demo.thirdpart.sms;

/**
 * 短信异常
 * @author wangzitao
 * @create 2018-06-10 10:28
 */
public class SMSApiException extends  RuntimeException {
    public SMSApiException(String messag) {
        super(messag);
    }
}
