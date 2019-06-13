package org.zhd.data.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dto.BaseListDTO;
import org.zhd.data.provider.entity.DptBean;
import org.zhd.data.provider.mapper.DptMapper;
import org.zhd.data.provider.utils.DaoUtils;
import org.zhd.data.provider.utils.DefaultEnum;

import java.util.List;
import java.util.Map;

/**
 * @author cth
 * @date 2019/06/03
 */
@Service
public class DptService {
    private Logger log = LoggerFactory.getLogger(DptService.class);

    @Autowired
    private DptMapper dptMapper;

    public DptBean saveDpt(DptBean dptBean){
        // 获取顶级部门
        DptBean dptDefault = dptMapper.selectById(DefaultEnum.DPT.getValue());
        if (dptDefault == null) {
            throw new RuntimeException("找不到顶级部门...");
        }
        // 赋值
        dptBean.setMemberCode("0000");
        dptBean.setDeptCode(DaoUtils.getMaxCode("dept_code", "basic_dept"));
        dptBean.setDeptParent(dptDefault.getDeptCode());
        dptBean.setDeptNodecode(dptDefault.getDeptNodecode() + "." + dptBean.getDeptCode());

        // 区分更新还是保存
        int res = 0;
        if (dptBean.getDeptId() == null) {
            res = dptMapper.insert(dptBean);
        } else {
            res = dptMapper.updateById(dptBean);
        }
        if (res == 1) {
            log.info(">>>保存成功,id为:" + dptBean.getDeptId());
            return dptBean;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public BaseListDTO<DptBean> selectDptPage(Map<String, Object> params) {
        Integer currentPage = (Integer) params.get("currentPage");
        Integer pageSize = (Integer) params.get("pageSize");
        Page<DptBean> page = new Page<>(currentPage, pageSize);
        // mp 条件构造器
        QueryWrapper<DptBean> queryWrapper = new QueryWrapper<>();
        String deptName = (String) params.get("deptName");
        String deptCode = (String) params.get("deptCode");
        if (deptName != null) {
            queryWrapper.like("dept_name", deptName);
        }
        if (deptCode != null) {
            queryWrapper.like("dept_code", deptCode);
        }
        // 不显示顶级部门
        queryWrapper.ne("dept_id", "1");
        // 排序
        queryWrapper.orderByDesc("dept_id");
        // 分页查询
        IPage<DptBean> resPage = dptMapper.selectPage(page, queryWrapper);
        return new BaseListDTO(resPage.getRecords(), (int) resPage.getTotal());
    }

    public int deleteDpt(List<Long> ids) {
        for (Long dptId: ids) {
            // 部门下有员工不能删除
            int count = dptMapper.countEmpByDptId(dptId);
            if (count > 0) {
                throw new RuntimeException("不能删除部门[" + dptId + "],原因是部门下有业务员");
            }
            dptMapper.deleteById(dptId);
        }
        return 1;
    }

    public DptBean selectDptById(Long id) {
        DptBean dpt = dptMapper.selectById(id);
        if (dpt == null) {
            return null;
        }
        return dpt;
    }

    /**
     * 根据名称查询部门列表
     * @param deptName deptName支持模糊查询
     * @return list
     */
    public List<DptBean> selectDptList(String deptName) {
        QueryWrapper<DptBean> queryWrapper = new QueryWrapper<>();
        if (deptName != null) {
            queryWrapper.like("dept_name", deptName);
        }
        return dptMapper.selectList(queryWrapper);
    }
}
