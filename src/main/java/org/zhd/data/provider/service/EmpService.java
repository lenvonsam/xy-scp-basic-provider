package org.zhd.data.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dto.BaseListDTO;
import org.zhd.data.provider.entity.EmpBean;
import org.zhd.data.provider.mapper.EmpMapper;
import org.zhd.data.provider.utils.DaoUtils;

import java.util.List;
import java.util.Map;

/**
 * @author cth
 * @date 2019/06/03
 */
@Service
public class EmpService {
    private Logger log = LoggerFactory.getLogger(EmpService.class);

    @Autowired
    private EmpMapper empMapper;

    public EmpBean saveEmp(EmpBean empBean){
        // 赋值
        empBean.setMemberCode("0000");
        empBean.setEmployeeCode(DaoUtils.getMaxCode("employee_code", "basic_employee"));

        // 区分更新还是保存
        int res = 0;
        if (empBean.getEmpId() == null) {
            res = empMapper.insert(empBean);
        } else {
            res = empMapper.updateById(empBean);
        }
        if (res == 1) {
            log.info(">>>保存成功,id为:" + empBean.getEmpId());
            return empBean;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public BaseListDTO<EmpBean> selectEmpPage(Map<String, Object> params) {
        Integer currentPage = (Integer) params.get("currentPage");
        Integer pageSize = (Integer) params.get("pageSize");
        Page<EmpBean> page = new Page<>(currentPage, pageSize);
        // mp 条件构造器
        QueryWrapper<EmpBean> queryWrapper = new QueryWrapper<>();
        String employeeName = (String) params.get("employeeName");
        String employeeCode = (String) params.get("employeeCode");
        if (employeeName != null) {
            queryWrapper.like("employee_name", employeeName);
        }
        if (employeeCode != null) {
            queryWrapper.like("employee_code", employeeCode);
        }
        // 排序
        queryWrapper.orderByDesc("employee_id");
        // 分页查询
        IPage<EmpBean> resPage = empMapper.selectPage(page, queryWrapper);
        return new BaseListDTO( resPage.getRecords(), (int) resPage.getTotal());
    }

    public int deleteEmp(List<Long> ids) {
        return empMapper.deleteBatchIds(ids);
    }

    public EmpBean selectEmpById(Long id) {
        EmpBean emp = empMapper.selectById(id);
        if (emp == null) {
            return null;
        }
        return emp;
    }

    /**
     * 根据名称查询业务员列表
     * @param empName empName支持模糊查询
     * @return list
     */
    public List<EmpBean> selectEmpList(String empName) {
        QueryWrapper<EmpBean> queryWrapper = new QueryWrapper<>();
        if (empName != null) {
            queryWrapper.like("employee_name", empName);
        }
        return empMapper.selectList(queryWrapper);
    }
}
