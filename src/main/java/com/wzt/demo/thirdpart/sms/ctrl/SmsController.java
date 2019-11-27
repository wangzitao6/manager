package com.wzt.demo.thirdpart.sms.ctrl;

import com.wzt.demo.bean.ReqParamBody;
import com.wzt.demo.bean.WebResult;
import com.wzt.demo.config.ShowClientServiceException;
import com.wzt.demo.thirdpart.sms.service.SmsService;
import com.wzt.demo.thirdpart.sms.vo.SmsTempletVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 短信接口
 *
 * @author wangzitao
 * @create 2018-06-10 10:28
 */
@Controller
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    SmsService smsService;

    /**
     * 发送短信服务
     *
     * @param body 参数包装类
     * @author wangzitao
     * @create 2018-06-10 10:50
     */
    @ResponseBody
    @RequestMapping("/send")
    public WebResult send(@Valid @RequestBody ReqParamBody<SmsTempletVO> body) {
        SmsTempletVO smsTempletVO = body.getData();
        if (smsTempletVO == null) {
            throw new ShowClientServiceException("业务数据不能为空");
        }
        String checkResult = smsTempletVO.checkParam();
        if (checkResult != null) {
            throw new ShowClientServiceException(checkResult);
        }
        String message = smsService.sendTempletSms(smsTempletVO);
        return WebResult.ok(message);
    }

}
