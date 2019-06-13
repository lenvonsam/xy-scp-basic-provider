package org.zhd.data.provider.controller.basic;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.utils.ApiUtil;
import org.zhd.data.provider.controller.BaseController;
import org.zhd.data.provider.entity.DptBean;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cth
 * @date 2019/06/03
 */
@RestController
@RequestMapping("v1/basicInfo")
@Api(tags = {"部门"}, description = "DptController")
public class DptController extends BaseController {
    @PostMapping("dpt")
    @ApiOperation("新增部门")
    public Map<String, Object> saveDpt(DptBean dptBean){
        log.info(">>>saveDpt start");
        if (dptBean.getDeptId() != null) {
            throw new RuntimeException("新增时id不为空...");
        }
        dptService.saveDpt(dptBean);
        return ApiUtil.responseCode();
    }

    @GetMapping("dpt")
    @ApiOperation("分页查询部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "currentPage", value = "当前页", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页数量", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(paramType = "query", name = "deptName", value = "名称", dataTypeClass = String.class),
            @ApiImplicitParam(paramType = "query", name = "deptCode", value = "编码", dataTypeClass = String.class)
    })
    public BaseListDTO<DptBean> selectDptPage(HttpServletRequest request) {
        log.info(">>>listDpt start");
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        // 条件
        String deptName = request.getParameter("deptName");
        String deptCode = request.getParameter("deptCode");

        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        params.put("deptName", deptName);
        params.put("deptCode", deptCode);
        return dptService.selectDptPage(params);
    }

    @GetMapping("dpt/{id}")
    @ApiOperation("获取单个部门")
    @ApiImplicitParam(paramType = "path", name = "id", value = "部门编号", dataTypeClass = Long.class, required = true)
    @ApiResponses(
            @ApiResponse(code = 0, message = "obj", response = DptBean.class)
    )
    public Map<String, Object> selectDptById(@PathVariable("id") Long id) {
        log.info(">>>getDpt start");
        Map<String, Object> map = new HashMap<>();
        DptBean res = dptService.selectDptById(id);
        map.put("obj", res);
        return ApiUtil.responseCode(map);
    }

    @DeleteMapping("dpt/{id}")
    @ApiOperation("删除部门")
    @ApiImplicitParam(paramType = "path", name = "id", value = "部门编号", dataTypeClass = Long.class, required = true)
    public Map<String, Object> deleteDpt(@PathVariable("id") Long id) {
        log.info(">>>deleteDpt start");
        dptService.deleteDpt(Arrays.asList(id));
        return ApiUtil.responseCode();
    }

    @DeleteMapping("dpt")
    @ApiOperation("批量删除部门")
    @ApiImplicitParam(paramType = "query", name = "spIds[]", value = "id数组", allowMultiple = true,
            dataTypeClass = String.class, required = true)
    public Map<String, Object> batchDeleteDpt(HttpServletRequest request) {
        log.info(">>>batchDeleteDpt start");
        String[] ids = request.getParameterValues("spIds[]");
        List<Long> idList = Stream.of(ids).map(Long::valueOf).collect(Collectors.toList());
        dptService.deleteDpt(idList);
        return ApiUtil.responseCode();
    }

    @PutMapping("dpt")
    @ApiOperation("修改部门")
    public Map<String, Object> updateDpt(DptBean dptBean) {
        log.info(">>>updateDpt start");
        dptService.saveDpt(dptBean);
        return ApiUtil.responseCode();
    }

    @GetMapping("dpt/list")
    @ApiOperation("查询部门列表，支持部门名称模糊查询")
    @ApiImplicitParam(paramType = "query", name = "dptName", value = "部门名称", dataTypeClass = String.class)
    public Map<String, Object> selectDptList(String dptName) {
        Map<String, Object> map = new HashMap<>();
        List<DptBean> res = dptService.selectDptList(dptName);
        map.put("total", res.size());
        map.put("list", res);
        return ApiUtil.responseCode(map);
    }
}
