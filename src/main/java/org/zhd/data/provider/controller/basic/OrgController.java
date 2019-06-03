package org.zhd.data.provider.controller.basic;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.utils.ApiUtil;
import org.zhd.data.provider.controller.BaseController;
import org.zhd.data.provider.entity.OrgBean;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cth
 * @date 2019/06/03
 */
@RestController
@RequestMapping("v1/basicInfo")
@Api(tags = {"机构"}, description = "OrgController")
public class OrgController extends BaseController {
    @PostMapping("org")
    @ApiOperation("新增机构")
    public Map<String, Object> saveOrg(OrgBean orgBean, HttpServletRequest request) {
        log.info(">>>saveOrg start");
        orgService.saveOrg(orgBean);
        return ApiUtil.responseCode();
    }

    @GetMapping("org")
    @ApiOperation("分页查询机构信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "currentPage", value = "当前页", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "orgName", value = "名称", dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "orgCode", value = "编码", dataTypeClass = String.class)
    })
    public BaseListDTO<OrgBean> selectOrgPage(HttpServletRequest request) {
        log.info(">>>listOrg start");
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        // 条件
        String orgName = request.getParameter("orgName");
        String orgCode = request.getParameter("orgCode");

        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        params.put("orgName", orgName);
        params.put("orgCode", orgCode);
        return orgService.selectOrgPage(params);
    }

    @GetMapping("org/{id}")
    @ApiOperation("获取单个机构")
    @ApiImplicitParam(paramType = "path", name = "id", value = "机构编号", dataTypeClass = Long.class, required = true)
    @ApiResponses(
            @ApiResponse(code = 0, message = "obj", response = OrgBean.class)
    )
    public Map<String, Object> selectOrgById(@PathVariable("id") Long id) {
        log.info(">>>getOrg start");
        Map<String, Object> map = new HashMap<>();
        OrgBean res = orgService.selectOrgById(id);
        map.put("obj", res);
        return ApiUtil.responseCode(map);
    }

    @DeleteMapping("org/{id}")
    @ApiOperation("删除机构")
    @ApiImplicitParam(paramType = "path", name = "id", value = "机构编号", dataTypeClass = Long.class, required = true)
    public Map<String, Object> deleteOrg(@PathVariable("id") Long id) {
        log.info(">>>deleteOrg start");
        orgService.deleteOrg(Arrays.asList(id));
        return ApiUtil.responseCode();
    }

    @DeleteMapping("org")
    @ApiOperation("批量删除机构")
    @ApiImplicitParam(paramType = "query", name = "spIds[]", value = "id数组", allowMultiple = true,
            dataTypeClass = String.class, required = true)
    public Map<String, Object> batchDeleteOrg(HttpServletRequest request) {
        log.info(">>>batchDeleteOrg start");
        String[] ids = request.getParameterValues("spIds[]");
        List<Long> idList = Stream.of(ids).map(Long::valueOf).collect(Collectors.toList());
        orgService.deleteOrg(idList);
        return ApiUtil.responseCode();
    }

    @PutMapping("org")
    @ApiOperation("修改机构")
    public Map<String, Object> updateOrg(OrgBean orgBean) {
        log.info(">>>updateOrg start");
        orgService.saveOrg(orgBean);
        return ApiUtil.responseCode();
    }
}
