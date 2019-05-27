package org.zhd.data.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dto.BaseListDTO;
import org.zhd.data.provider.entity.Dpt;
import org.zhd.data.provider.mapper.DptMapper;
import org.zhd.data.provider.utils.DefaultEnum;

import java.util.List;
import java.util.Map;

@Service
public class DptService {
    private Logger log = LoggerFactory.getLogger(DptService.class);

    @Autowired
    private DptMapper dptMapper;

    public Dpt saveDpt(Dpt dpt){
        // 获取顶级部门
        Dpt dptDefault = dptMapper.selectById(DefaultEnum.DPT.getValue());
        if (dptDefault == null) {
            log.info(">>>找不到顶级部门...");
            return null;
        }
//        if (dpt.getDeptCode().equals("000095")) {
//            throw new Exception("deptCode:000095 已经存在！");
//        }
        // 赋值
        dpt.setMemberCode("0000");
        dpt.setDeptParent(dptDefault.getDeptCode());
        dpt.setDeptNodecode(dptDefault.getDeptNodecode() + "." + dpt.getDeptCode());

        // 区分更新还是保存
        int res = 0;
        if (dpt.getDeptId() == null) {
            res = dptMapper.insert(dpt);
        } else {
            res = dptMapper.updateById(dpt);
        }
        if (res == 1) {
            log.info(">>>保存成功,id为:" + dpt.getDeptId());
            return dpt;
        } else {
            return null;
        }
    }

    public BaseListDTO<Dpt> findDptListByPg(Map<String, Object> params) {
        Integer currentPage = (Integer) params.get("currentPage");
        Integer pageSize = (Integer) params.get("pageSize");
        Page<Dpt> page = new Page<>(currentPage, pageSize);
        // mp 条件构造器
        QueryWrapper<Dpt> queryWrapper = new QueryWrapper<>();
        // 分页查询
        IPage<Dpt> resPage = dptMapper.selectPage(page, queryWrapper);
        return new BaseListDTO( resPage.getRecords(), (int) resPage.getTotal());
    }

    public int deleteDpt(Long id) {
        int res = dptMapper.deleteById(id);
        if (res != 1) {
            return -1;
        }
        return 0;
    }

    public int batchDeleteDpt(List<Long> ids) {
        for (Long id : ids) {
            int res = dptMapper.deleteById(id);
            if (res != 1) {
                return -1;
            }
        }
        return 0;
    }

    public Dpt findDptById(Long id) {
        Dpt dpt = dptMapper.selectById(id);
        if (dpt == null) {
            return null;
        }
        return dpt;
    }
}
