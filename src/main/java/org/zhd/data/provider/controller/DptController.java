package org.zhd.data.provider.controller;

import org.springframework.web.bind.annotation.*;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.utils.ApiUtil;
import org.zhd.data.provider.entity.Dpt;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("v1/basicInfo")
public class DptController extends BaseController {
    @PostMapping("dpt")
    public Map<String, Object> saveDpt(Dpt dpt){
        log.info(">>>saveDpt start");
        dptService.saveDpt(dpt);
        return ApiUtil.responseCode();
    }

    @GetMapping("dpt")
    public BaseListDTO<Dpt> listDpt(HttpServletRequest request) {
        log.info(">>>listDpt start");
        Map<String, Object> map = new HashMap<>();
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        Map<String, Object> params = new HashMap<>();
        params.put("currentPage", currentPage);
        params.put("pageSize", pageSize);
        return dptService.findDptListByPg(params);
    }

    @GetMapping("dpt/{id}")
    public Map<String, Object> getDpt(@PathVariable("id") Long id) {
        log.info(">>>getDpt start");
        Map<String, Object> map = new HashMap<>();
        Dpt res = dptService.findDptById(id);
        map.put("obj", res);
        return ApiUtil.responseCode(map);
    }

    @DeleteMapping("dpt/{id}")
    public Map<String, Object> deleteDpt(@PathVariable("id") Long id) {
        log.info(">>>deleteDpt start");
        dptService.deleteDpt(id);
        return ApiUtil.responseCode();
    }

    @DeleteMapping("dpt")
    public Map<String, Object> batchDeleteDpt(HttpServletRequest request) {
        log.info(">>>batchDeleteDpt start");
        String[] ids = request.getParameterValues("spIds[]");
        List<Long> idList = Stream.of(ids).map(Long::valueOf).collect(Collectors.toList());
        int res = dptService.batchDeleteDpt(idList);
        return ApiUtil.responseCode();
    }

    @PutMapping("dpt")
    public Map<String, Object> updateDpt(Dpt dpt){
        log.info(">>>updateDpt start");
        dptService.saveDpt(dpt);
        return ApiUtil.responseCode();
    }
}
