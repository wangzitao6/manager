package com.wzt.demo.thirdpart.sms.service.impl;

import cn.hutool.json.JSONUtil;

import com.wzt.demo.thirdpart.sms.SMSApi;
import com.wzt.demo.thirdpart.sms.SMSConstants;
import com.wzt.demo.thirdpart.sms.dto.SMSResultDTO;
import com.wzt.demo.thirdpart.sms.service.SmsService;
import com.wzt.demo.thirdpart.sms.vo.MsgSendRecAddVO;
import com.wzt.demo.thirdpart.sms.vo.SmsTempletVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("smsService")
public class SmsServiceImpl implements SmsService {
    private static Logger log = LoggerFactory.getLogger(SmsServiceImpl.class);
    @Autowired
    SMSApi smsApi;

    @Override
    public String sendTempletSms(SmsTempletVO vo) {
        //1.初始化日志参数
        MsgSendRecAddVO msgsendRec = new MsgSendRecAddVO();
        msgsendRec.setSmsType(vo.getSmsType());
        msgsendRec.setTarget(vo.getPhone());
        msgsendRec.setTempCode(vo.getTempCode());
        if (SMSConstants.SmsType.ALIYUN.equals(vo.getSmsType())) {
            msgsendRec.setContent(JSONUtil.toJsonStr(vo.getFillValue()));
        }
        //2.发送短信
        SMSResultDTO returnDTO;
        returnDTO =smsApi.sendSms(vo);

        msgsendRec.setRemark(returnDTO.getMsg());
        msgsendRec.setMessageId(returnDTO.getMessageId());
        String status = returnDTO.getStatus();
        String message;
        if (SMSConstants.SmsParam.FAIL.equals(status)) {
            message = "短信发送失败，原因：" + returnDTO.getMsg();
        } else if (SMSConstants.SmsParam.WAIT.equals(status)) {
            message = "短信发送中：" + returnDTO.getMsg() + " " + returnDTO.getMessageId();
        } else if (SMSConstants.SmsParam.SUCCESSS.equals(status)) {
            message = "发送成功";
        } else {
            message = "短信发送中";
        }

        return message;
    }

}
