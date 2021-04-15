package com.wzt.demo.example.ctrl;

import com.wzt.demo.bean.WebResult;
import com.wzt.demo.example.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangzitao
 * @create 2018-05-17 10:25
 **/
@RestController
@RequestMapping("/test")
public class TestController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    TestService testService;

    @GetMapping("/demo")
    public WebResult selectStudentById() {
        testService.test();
        return WebResult.ok();
    }

}
