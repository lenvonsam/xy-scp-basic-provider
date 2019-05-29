package org.zhd.data.provider.controller.basic;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.utils.ApiUtil;
import org.zhd.data.provider.controller.BaseController;
import org.zhd.data.provider.entity.Emp;
import org.zhd.data.provider.utils.DateTimeUtils;
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
public class EmpController extends BaseController {
    /**
     * @api {post} v1/basicInfo/emp 新增
     * @apiDescription  新增员工信息
     * @apiName saveEmp
     * @apiGroup emp
     * @apiParam {Emp} emp 对象
     * @apiParam {String} emp.deptCode 部门code
     * @apiParamExample {json} Request-Example:
     * {
     *     "emp":"员工对象",
     *     "spEmpBirthday":"2019-05-28",
     *     "spEmpJoindate":"2019-05-28"
     * }
     * @apiSuccessExample {json} Success-Response:
     * {
     *      "list":{List <emp>}
     *      "total":"10",
     *      "return_code":"0",
     *      "message":"success"
     * }
     * @apiVersion 1.0.0
     */
    @PostMapping("emp")
    public Map<String, Object> saveEmp(Emp emp, HttpServletRequest request){
        log.info(">>>saveEmp start");
        String spEmpBirthday = request.getParameter("spEmpBirthday");
        String spEmpJoindate = request.getParameter("spEmpJoindate");
        if (spEmpBirthday != null) {
            emp.setEmployeeBirthday(DateTimeUtils.stringToDate(spEmpBirthday));
        }
        if (spEmpJoindate != null) {
            emp.setEmployeeJoindate(DateTimeUtils.stringToDate(spEmpJoindate));
        }
        empService.saveEmp(emp);
        return ApiUtil.responseCode();
    }

    /**
     * @api {get} v1/basicInfo/emp 分页查询
     * @apiDescription  分页查询员工信息
     * @apiName listEmp
     * @apiGroup emp
     * @apiParamExample {json} Request-Example:
     * {
     *     "currentPage":"0",
     *     "pageSize":"10"
     * }
     * @apiSuccessExample {json} Success-Response:
     * {
     *      "list":{List <emp>}
     *      "total":"10",
     *      "return_code":"0",
     *      "message":"success"
     * }
     * @apiVersion 1.0.0
     */
    @GetMapping("emp")
    public BaseListDTO<Emp> listEmp(HttpServletRequest request) {
        log.info(">>>listEmp start");
        Map<String, Object> map = new HashMap<>();
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        return empService.findEmpListByPg(params);
    }

    @GetMapping("emp/{id}")
    public Map<String, Object> getEmp(@PathVariable("id") Long id) {
        log.info(">>>getEmp start");
        Map<String, Object> map = new HashMap<>();
        Emp res = empService.findEmpById(id);
        map.put("obj", res);
        return ApiUtil.responseCode(map);
    }

    @DeleteMapping("emp/{id}")
    public Map<String, Object> deleteEmp(@PathVariable("id") Long id) {
        log.info(">>>deleteEmp start");
        empService.deleteEmp(id);
        return ApiUtil.responseCode();
    }

    @DeleteMapping("emp")
    public Map<String, Object> batchDeleteEmp(HttpServletRequest request) {
        log.info(">>>batchDeleteEmp start");
        String[] ids = request.getParameterValues("spIds[]");
        List<Long> idList = Stream.of(ids).map(Long::valueOf).collect(Collectors.toList());
        int res = empService.batchDeleteEmp(idList);
        return ApiUtil.responseCode();
    }

    @PutMapping("emp")
    public Map<String, Object> updateEmp(Emp emp){
        log.info(">>>updateEmp start");
        empService.saveEmp(emp);
        return ApiUtil.responseCode();
    }
}
