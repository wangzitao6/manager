package com.wzt.demo.example.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 回调通知JSON VO
 * @author wangzitao
 * @date 2018/09/11 13:55
 **/
public class NotifyJsonVO implements Serializable{
    /**
     * 申请单号
     */
    @NotNull(message = "申请单号不能为空")
    private String reqCode;
    /**
     * 存储路径
     */
    @NotNull(message = "存储路径不能为空")
    private String contactSavePath;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getContactSavePath() {
        return contactSavePath;
    }

    public void setContactSavePath(String contactSavePath) {
        this.contactSavePath = contactSavePath;
    }

    @Override
    public String toString() {
        return "NotifyJsonVO{" +
                "reqCode='" + reqCode + '\'' +
                ", contactSavePath='" + contactSavePath + '\'' +
                '}';
    }
}
