package com.wzt.demo.example.vo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 回调通知新增VO
 * @author wangzitao
 * @date 2018/09/11 13:55
 **/
public class NotifyVO implements Serializable{
    /**
     * 外部唯一编码，一般为申请单号
     */
    @NotNull(message = "外部唯一编码不能为空")
    private String outUniqueCode;
    /**
     * 来源系统ID
     * <pre>
     * 调用方的系统id
     * </pre>
     */
    private String notifyJson;

    public String getOutUniqueCode() {
        return outUniqueCode;
    }

    public void setOutUniqueCode(String outUniqueCode) {
        this.outUniqueCode = outUniqueCode;
    }

    public String getNotifyJson() {
        return notifyJson;
    }

    public void setNotifyJson(String notifyJson) {
        this.notifyJson = notifyJson;
    }

    @Override
    public String toString() {
        return "NotifyVO{" +
                "outUniqueCode='" + outUniqueCode + '\'' +
                ", notifyJson='" + notifyJson + '\'' +
                '}';
    }
}
