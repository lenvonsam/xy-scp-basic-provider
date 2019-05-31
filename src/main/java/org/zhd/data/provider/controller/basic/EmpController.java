package org.zhd.data.provider.controller.basic;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.utils.ApiUtil;
import org.zhd.data.provider.controller.BaseController;
import org.zhd.data.provider.entity.Emp;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("v1/basicInfo")
public class EmpController extends BaseController {
    @PostMapping("emp")
    @ApiOperation("新增业务员")
    public Map<String, Object> saveEmp(Emp emp, HttpServletRequest request){
        log.info(">>>saveEmp start");
        empService.saveEmp(emp);
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
    public BaseListDTO<Emp> listEmp(HttpServletRequest request) {
        log.info(">>>listEmp start");
        Map<String, Object> map = new HashMap<>();
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
        return empService.findEmpListByPg(params);
    }

    @GetMapping("emp/{id}")
    @ApiOperation("获取单个业务员")
    @ApiImplicitParam(paramType = "path", name = "id", value = "业务员编号", dataTypeClass = Long.class, required = true)
    @ApiResponses(
            @ApiResponse(code = 0, message = "obj", response = Emp.class)
    )
    public Map<String, Object> getEmp(@PathVariable("id") Long id) {
        log.info(">>>getEmp start");
        Map<String, Object> map = new HashMap<>();
        Emp res = empService.findEmpById(id);
        map.put("obj", res);
        return ApiUtil.responseCode(map);
    }

    @DeleteMapping("emp/{id}")
    @ApiOperation("删除业务员")
    @ApiImplicitParam(paramType = "path", name = "id", value = "业务员编号", dataTypeClass = Long.class, required = true)
    public Map<String, Object> deleteEmp(@PathVariable("id") Long id) {
        log.info(">>>deleteEmp start");
        empService.deleteEmp(id);
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
        int res = empService.batchDeleteEmp(idList);
        return ApiUtil.responseCode();
    }

    @PutMapping("emp")
    @ApiOperation("修改业务员")
    public Map<String, Object> updateEmp(Emp emp){
        log.info(">>>updateEmp start");
        empService.saveEmp(emp);
        return ApiUtil.responseCode();
    }
}
