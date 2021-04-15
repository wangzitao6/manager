package com.wzt.demo.example.service.impl;

import cn.hutool.core.util.HexUtil;
import com.wzt.demo.example.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wangzitao
 * @create 2021-04-15 10:23
 **/
@Service("testServiceImpl")
public class TestServiceImpl implements TestService {
    private static final Logger LOG = LoggerFactory.getLogger(TestServiceImpl.class);


    @Override
    public void test() {
        String aa = "0x2f";
        byte[] bb = HexUtil.decodeHex(aa);
        LOG.info(bb.toString());
    }
}
