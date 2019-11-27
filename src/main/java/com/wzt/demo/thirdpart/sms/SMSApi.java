package com.wzt.demo.thirdpart.sms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.wzt.demo.thirdpart.sms.dto.SMSResultDTO;
import com.wzt.demo.thirdpart.sms.vo.SmsTempletVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信发送
 *
 * @author wangzitao
 * @create 2018-06-10 10:28
 */
@Component("smsApi")
public class SMSApi {

    @Value("${sms.accessKeyId}")
    private String accessKeyId;
    @Value("${sms.accessKeySecret}")
    private String accessKeySecret;
    @Value("${sms.product}")
    private String product;
    @Value("${sms.domain}")
    private String domain;
    @Value("${sms.signName}")
    private String signName;


    static final String TIMEOUT = "5000";

    public SMSResultDTO sendSms(SmsTempletVO smsVO) {
        SMSResultDTO resultDTO = new SMSResultDTO();

        //连接主机的超时时间（单位：毫秒）
        System.setProperty("sun.net.client.defaultConnectTimeout", TIMEOUT);
        //从主机读取数据的超时时间（单位：毫秒）
        System.setProperty("sun.net.client.defaultReadTimeout", TIMEOUT);

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        //此处可能会抛出异常，注意catch
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            resultDTO.setStatus(SMSConstants.SmsParam.FAIL);
            resultDTO.setMsg(e.getMessage());
            return resultDTO;
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(smsVO.getPhone());
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsVO.getTempCode());
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        String jsonParam = JSON.toJSONString(smsVO.getFillValue());
        request.setTemplateParam(jsonParam);

        try {
            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            resultDTO.setRequestId(sendSmsResponse.getRequestId());
            resultDTO.setMessageId(sendSmsResponse.getBizId());
            resultDTO.setMsg(sendSmsResponse.getMessage());
            //发送成功
            if (sendSmsResponse.getCode() != null && SMSConstants.SmsParam.OK.equals(sendSmsResponse.getCode())) {
                resultDTO.setStatus(SMSConstants.SmsParam.SUCCESSS);
            } else {
                resultDTO.setStatus(SMSConstants.SmsParam.FAIL);
            }
            return resultDTO;
        } catch (Exception e) {
            resultDTO.setStatus(SMSConstants.SmsParam.FAIL);
            resultDTO.setMsg(e.getMessage());
            return resultDTO;
        }
    }

}
