package com.wzt.demo.example.dto;

import java.io.Serializable;

/**
 * 通知回调接口返回信息
 * @author wangzitao
 * @date 2018/11/01 15:00
 **/
public class NotifyResultDTO implements Serializable{
    /**
     * 返回编码
     */
    private String code;
    /**
     * 错误信息描述
     */
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotifyResultDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
