package org.zhd.data.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhd.data.provider.mapper.CommonMapper;

@RestController
@RequestMapping("v1/test")
@Profile("dev")
public class TestController {
    private Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private CommonMapper commonMapper;

    @GetMapping("testCode")
    public String testCode() {
        String res = commonMapper.getMaxCode("dept_code", "basic_dept");
        log.info(">>>" + res);
        return "success";
    }
}
