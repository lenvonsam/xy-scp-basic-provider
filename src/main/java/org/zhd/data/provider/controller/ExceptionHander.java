package org.zhd.data.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xy.api.enums.ApiEnum;
import org.xy.api.utils.ApiUtil;

import java.util.Map;

@ControllerAdvice
public class ExceptionHander {
    private Logger log = LoggerFactory.getLogger(ExceptionHander.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map<String, Object> exceptionHander(Exception e) {
        e.printStackTrace();
        return ApiUtil.responseCode(null, ApiEnum.FAILURE, e.getMessage());
    }
}
