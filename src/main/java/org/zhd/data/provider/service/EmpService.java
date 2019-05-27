package org.zhd.data.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dto.BaseListDTO;
import org.zhd.data.provider.entity.Emp;
import org.zhd.data.provider.mapper.EmpMapper;
import org.zhd.data.provider.utils.DefaultEnum;

import java.util.List;
import java.util.Map;

@Service
public class EmpService {
    private Logger log = LoggerFactory.getLogger(EmpService.class);

    @Autowired
    private EmpMapper empMapper;

    public Emp saveEmp(Emp emp){
        // 获取顶级部门
        Emp empDefault = empMapper.selectById(DefaultEnum.DPT.getValue());
        if (empDefault == null) {
            log.info(">>>找不到顶级部门...");
            return null;
        }
        // 赋值
        emp.setMemberCode("0000");

        // 区分更新还是保存
        int res = 0;
        if (emp.getEmpId() == null) {
            res = empMapper.insert(emp);
        } else {
            res = empMapper.updateById(emp);
        }
        if (res == 1) {
            log.info(">>>保存成功,id为:" + emp.getEmpId());
            return emp;
        } else {
            return null;
        }
    }

    public BaseListDTO<Emp> findEmpListByPg(Map<String, Object> params) {
        Integer currentPage = (Integer) params.get("currentPage");
        Integer pageSize = (Integer) params.get("pageSize");
        Page<Emp> page = new Page<>(currentPage, pageSize);
        // mp 条件构造器
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        // 分页查询
        IPage<Emp> resPage = empMapper.selectPage(page, queryWrapper);
        return new BaseListDTO( resPage.getRecords(), (int) resPage.getTotal());
    }

    public int deleteEmp(Long id) {
        int res = empMapper.deleteById(id);
        if (res != 1) {
            return -1;
        }
        return 0;
    }

    public int batchDeleteEmp(List<Long> ids) {
        for (Long id : ids) {
            int res = empMapper.deleteById(id);
            if (res != 1) {
                return -1;
            }
        }
        return 0;
    }

    public Emp findEmpById(Long id) {
        Emp emp = empMapper.selectById(id);
        if (emp == null) {
            return null;
        }
        return emp;
    }
}
