package com.wzt.demo.thirdpart.sms.service;


import com.wzt.demo.thirdpart.sms.vo.SmsTempletVO;

/**
 * 短信服务接口
 * @author wangzitao
 * @create 2018-06-10 10:28
 */
public interface SmsService {

	/**
	 * 发送短信(通过短信模板)
	 * @param smsTempletVO 发送手机号、短信模板编码，模板填充值
	 * @author wangzitao
	 * @create 2018-06-10 10:28
	 */
	String sendTempletSms(SmsTempletVO smsTempletVO);

}