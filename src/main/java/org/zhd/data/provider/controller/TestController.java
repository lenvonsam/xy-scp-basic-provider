package org.zhd.data.provider.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xy.api.dpi.basic.OrgDpi;
import org.xy.api.dpi.extra.SmsDpi;
import org.xy.api.dto.basic.OrgDTO;
import org.xy.api.dto.extra.MsgTemplateGroupDTO;
import org.zhd.data.provider.mapper.CommonMapper;
import org.zhd.data.provider.service.OrgService;
import org.zhd.data.provider.service.dubbo.OrgProviderService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cth
 * @date 2019/06/03
 */
@RestController
@RequestMapping("v1/test")
@Profile("dev")
@Slf4j
public class TestController {
//    private Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private CommonMapper commonMapper;

    @Reference(application = "extra", check = false, retries = -1, loadbalance = "txlcn_random", version = "${api.service.version}", group = "${dubbo.group}")
    private SmsDpi smsDpi;

//    @Reference(check = false, retries = -1, loadbalance = "txlcn_random", version = "${api.service.version=}", application = "${dubbo.application.name}", group = "Test-cth-soa")
//    private OrgDpi orgDpi;
    @Autowired
    private OrgProviderService orgService;

    @GetMapping("testCode")
    public String testCode() {
        String res = commonMapper.getMaxCode("dept_code", "basic_dept");
        log.info(">>>" + res);
        return "success";
    }

    @GetMapping("testOrgSave")
    @LcnTransaction
    @Transactional
    public String testOrg(String tname, String orgName, HttpServletRequest req) throws Exception {
        String result = "";
        MsgTemplateGroupDTO msgTemp = new MsgTemplateGroupDTO();
        msgTemp.setName(tname);
        smsDpi.saveMessageTemplateGroup(msgTemp);
        result += "信息模板保存成功\n";
        OrgDTO org = new OrgDTO();
        org.setName(orgName);
        org.setAbbreviate(orgName);
        orgService.save(org);
//        orgDpi.save(org);
        result += "机构保存成功";
        if (req.getParameter("throw") != null) throw new Exception("txlc exception");
        return result;
    }
}
