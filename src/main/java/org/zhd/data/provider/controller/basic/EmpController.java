package org.zhd.data.provider.controller.basic;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.utils.ApiUtil;
import org.zhd.data.provider.controller.BaseController;
import org.zhd.data.provider.entity.EmpBean;

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
@Api(tags = {"业务员"}, description = "EmpController")
public class EmpController extends BaseController {
    @PostMapping("emp")
    @ApiOperation("新增业务员")
    public Map<String, Object> saveEmp(EmpBean empBean, HttpServletRequest request){
        log.info(">>>saveEmp start");
        if (empBean.getEmpId() != null) {
            throw new RuntimeException("新增时id不为空...");
        }
        empService.saveEmp(empBean);
        return ApiUtil.responseCode();
    }

    @GetMapping("emp")
    @ApiOperation("分页查询业务员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "currentPage", value = "当前页", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "employeeName", value = "名称", dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "employeeCode", value = "编码", dataTypeClass = String.class)
    })
    public BaseListDTO<EmpBean> selectEmpPage(HttpServletRequest request) {
        log.info(">>>listEmp start");
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        // 条件
        String employeeName = request.getParameter("employeeName");
        String employeeCode = request.getParameter("employeeCode");

        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        params.put("employeeName", employeeName);
        params.put("employeeCode", employeeCode);
        return empService.selectEmpPage(params);
    }

    @GetMapping("emp/{id}")
    @ApiOperation("获取单个业务员")
    @ApiImplicitParam(paramType = "path", name = "id", value = "业务员编号", dataTypeClass = Long.class, required = true)
    @ApiResponses(
            @ApiResponse(code = 0, message = "obj", response = EmpBean.class)
    )
    public Map<String, Object> selectEmpById(@PathVariable("id") Long id) {
        log.info(">>>getEmp start");
        Map<String, Object> map = new HashMap<>();
        EmpBean res = empService.selectEmpById(id);
        map.put("obj", res);
        return ApiUtil.responseCode(map);
    }

    @DeleteMapping("emp/{id}")
    @ApiOperation("删除业务员")
    @ApiImplicitParam(paramType = "path", name = "id", value = "业务员编号", dataTypeClass = Long.class, required = true)
    public Map<String, Object> deleteEmp(@PathVariable("id") Long id) {
        log.info(">>>deleteEmp start");
        empService.deleteEmp(Arrays.asList(id));
        return ApiUtil.responseCode();
    }

    @DeleteMapping("emp")
    @ApiOperation("批量删除业务员")
    @ApiImplicitParam(paramType = "query", name = "spIds[]", value = "id数组", allowMultiple = true,
            dataTypeClass = String.class, required = true)
    public Map<String, Object> batchDeleteEmp(HttpServletRequest request) {
        log.info(">>>batchDeleteEmp start");
        String[] ids = request.getParameterValues("spIds[]");
        List<Long> idList = Stream.of(ids).map(Long::valueOf).collect(Collectors.toList());
        empService.deleteEmp(idList);
        return ApiUtil.responseCode();
    }

    @PutMapping("emp")
    @ApiOperation("修改业务员")
    public Map<String, Object> updateEmp(EmpBean empBean){
        log.info(">>>updateEmp start");
        empService.saveEmp(empBean);
        return ApiUtil.responseCode();
    }

    @GetMapping("emp/list")
    @ApiOperation("查询业务员列表，支持业务员名称模糊查询")
    @ApiImplicitParam(paramType = "query", name = "empName", value = "业务员名称", dataTypeClass = String.class)
    public Map<String, Object> selectEmpList(String empName) {
        Map<String, Object> map = new HashMap<>();
        List<EmpBean> res = empService.selectEmpList(empName);
        map.put("total", res.size());
        map.put("list", res);
        return ApiUtil.responseCode(map);
    }
}
