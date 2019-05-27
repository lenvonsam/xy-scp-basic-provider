package org.zhd.data.provider.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.utils.ApiUtil;
import org.zhd.data.provider.entity.Emp;
import org.zhd.data.provider.utils.DateTimeUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("v1/basicInfo")
public class EmpController extends BaseController{
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
