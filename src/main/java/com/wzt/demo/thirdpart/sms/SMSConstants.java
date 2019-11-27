package com.wzt.demo.thirdpart.sms;

/**
 * 短信常量配置
 * @author wangzitao
 * @create 2018-06-10 10:28
 */
public interface SMSConstants {

    /**
     * 短信类型
     */
    interface SmsType {
        String ALIYUN = "aliyun";
    }

    /**
     * 短信返回参数
     */
    interface SmsParam {
        String OK = "OK";

        String SUCCESSS = "0";

        String FAIL = "-1";

        String WAIT = "2";

    }

}
