package com.wzt.demo.thirdpart.sms.dto;

import java.io.Serializable;

/**
 * sms处理结果返回
 * @author wangzitao
 * @create 2018-06-11 17:38
 */
public class SMSResultDTO implements Serializable {
    /**
     * msg
     */
    private String msg;
    /**
     * messageId
     */
    private String messageId;
    /**
     * RequestId
     */
    private String RequestId;
    /**
     * status
     */
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
