package org.zhd.data.provider.controller.basic;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.utils.ApiUtil;
import org.zhd.data.provider.controller.BaseController;
import org.zhd.data.provider.entity.Org;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("v1/basicInfo")
@ApiIgnore
public class OrgController extends BaseController {
    @PostMapping("org")
    public Map<String, Object> saveOrg(Org org, HttpServletRequest request){
        log.info(">>>saveOrg start");
        orgService.saveOrg(org);
        return ApiUtil.responseCode();
    }

    @GetMapping("org")
    public BaseListDTO<Org> listOrg(HttpServletRequest request) {
        log.info(">>>listOrg start");
        Map<String, Object> map = new HashMap<>();
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        return orgService.findOrgListByPg(params);
    }

    @GetMapping("org/{id}")
    public Map<String, Object> getOrg(@PathVariable("id") Long id) {
        log.info(">>>getOrg start");
        Map<String, Object> map = new HashMap<>();
        Org res = orgService.findOrgById(id);
        map.put("obj", res);
        return ApiUtil.responseCode(map);
    }

    @DeleteMapping("org/{id}")
    public Map<String, Object> deleteOrg(@PathVariable("id") Long id) {
        log.info(">>>deleteOrg start");
        orgService.deleteOrg(id);
        return ApiUtil.responseCode();
    }

    @DeleteMapping("org")
    public Map<String, Object> batchDeleteOrg(HttpServletRequest request) {
        log.info(">>>batchDeleteOrg start");
        String[] ids = request.getParameterValues("spIds[]");
        List<Long> idList = Stream.of(ids).map(Long::valueOf).collect(Collectors.toList());
        int res = orgService.batchDeleteOrg(idList);
        return ApiUtil.responseCode();
    }

    @PutMapping("org")
    public Map<String, Object> updateOrg(Org org){
        log.info(">>>updateOrg start");
        orgService.saveOrg(org);
        return ApiUtil.responseCode();
    }
}
