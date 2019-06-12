package org.zhd.data.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zhd.data.provider.service.DptService;
import org.zhd.data.provider.service.EmpService;
import org.zhd.data.provider.service.OrgService;

/**
 * @author cth
 * @date 2019/06/03
 */
@Controller
public class BaseController {
    protected Logger log = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected DptService dptService;
    @Autowired
    protected OrgService orgService;
    @Autowired
    protected EmpService empService;
}
