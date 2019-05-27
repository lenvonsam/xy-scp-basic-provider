package org.zhd.data.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zhd.data.provider.service.DptService;
import org.zhd.data.provider.service.EmpService;
import org.zhd.data.provider.service.OrgService;

@Controller
class BaseController {
    Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    DptService dptService;
    @Autowired
    OrgService orgService;
    @Autowired
    EmpService empService;
}
