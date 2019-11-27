package com.wzt.demo.thirdpart.sms.vo;

import java.io.Serializable;
import java.util.Date;

public class MsgSendRecAddVO implements Serializable {
    /**
     * 消息类型
     */
    private String smsType;
    /**
     * 目标
     */
    private String target;
    /**
     * 短信模板编码
     */
    private String tempCode;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 状态
     */
    private String status;
    /**
     * 系统编码
     */
    private String sysCode;
    /**
     * 消息Id
     */
    private String messageId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date modDate;

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTempCode() {
        return tempCode;
    }

    public void setTempCode(String tempCode) {
        this.tempCode = tempCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }
}
