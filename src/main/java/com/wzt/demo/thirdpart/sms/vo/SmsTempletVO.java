package com.wzt.demo.thirdpart.sms.vo;

import cn.hutool.core.util.StrUtil;
import com.wzt.demo.thirdpart.sms.SMSConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Map;

/**
 * 短信模板发送VO
 * @author wangzitao
 * @create 2018-06-10 10:50
 */
public class SmsTempletVO implements Serializable {
    /**
     * 使用的短信类型（默认aliyun）
     */
    private String smsType;
    /**
     * 短信模板编码,如果为阿里云发送，不能为空
     */
    private String tempCode;
    /**
     * 如果为阿里云发送，不能为空
     * 模板填充值（占位符-填充值）
     */
    private Map<String, String> fillValue;
    /**
     * 移动电话号码
     */
    @NotNull(message = "请填写手机号")
    @Pattern(regexp = "^((13[0-9])|(14[5-8])|(16[6])|(17[0|4|6|7|8])|(19[8-9])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8}$", message = "手机号码格式不对")
    private String phone;

    /**
     * 校验参数是否正确
     *
     * @return 如果符合要求返回null
     */
    public String checkParam() {
        if (StrUtil.isEmpty(this.smsType)) {
            this.smsType = SMSConstants.SmsType.ALIYUN;
        }
        if (!smsType.equals(SMSConstants.SmsType.ALIYUN)) {
            return "发送类型不能为空";
        }
        if (smsType.equals(SMSConstants.SmsType.ALIYUN)) {
            if (StrUtil.isEmpty(tempCode) || fillValue == null || fillValue.size() == 0) {
                return "阿里云短信必须传入模板编码和参数";
            }
        }
        return null;
    }


    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getTempCode() {
        return tempCode;
    }

    public void setTempCode(String tempCode) {
        this.tempCode = tempCode;
    }

    public Map<String, String> getFillValue() {
        return fillValue;
    }

    public void setFillValue(Map<String, String> fillValue) {
        this.fillValue = fillValue;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
