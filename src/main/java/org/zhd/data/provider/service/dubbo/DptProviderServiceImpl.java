package org.zhd.data.provider.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.xy.api.dpi.basic.DptDpi;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.dto.basic.DptDTO;
import org.zhd.data.provider.core.entity.Dpt;
import org.zhd.data.provider.core.mapper.DptMapper;
import org.zhd.data.provider.utils.DefaultEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Service(version = "${api.service.version}", application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}", group = "${dubbo.group}")
public class DptProviderServiceImpl implements DptDpi {
    private Logger log = LoggerFactory.getLogger(DptProviderServiceImpl.class);

    @Autowired
    private DptMapper dptMapper;

    @Override
    public DptDTO save(DptDTO model) {
        // 获取顶级部门
        Dpt dptDefault = dptMapper.selectById(DefaultEnum.DPT.getValue());
        if (dptDefault == null) {
            log.info(">>>找不到顶级部门...");
            return null;
        }
        // 赋值
        Dpt dpt = new Dpt();
        dpt.setMemberCode(model.getMemberCode());
        dpt.setDeptCode(model.getCode());
        dpt.setDeptName(model.getName());
        dpt.setOrgCode(model.getOrgCode());
        dpt.setDeptManager(model.getManager());
        dpt.setDeptRemark(model.getRemark());
        dpt.setDeptParent(dptDefault.getDeptCode());
        dpt.setDeptNodecode(dptDefault.getDeptNodecode() + "." + model.getCode());

        // 区分更新还是保存
        int res = 0;
        if (model.getId() == null) {
            res = dptMapper.insertDpt(dpt);
        } else {
            dpt.setDeptId(model.getId());
            res = dptMapper.updateById(dpt);
        }
        if (res == 1) {
            log.info(">>>保存成功,id为:" + dpt.getDeptId());
            return model;
        } else {
            return null;
        }
    }

    @Override
    public BaseListDTO<DptDTO> findByPg(Map<String, Object> params) {
        Integer currentPage = (Integer) params.get("currentPage");
        Integer pageSize = (Integer) params.get("pageSize");
        Page<Dpt> page = new Page<>(currentPage, pageSize);
        // mp 条件构造器
        QueryWrapper<Dpt> queryWrapper = new QueryWrapper<>();

        // 分页查询
        IPage<Dpt> resPage = dptMapper.selectPage(page, queryWrapper);
        List<DptDTO> dtolist = new ArrayList<>();
        for (Dpt dpt : resPage.getRecords()) {
            DptDTO temp = new DptDTO();
            temp.setId(dpt.getDeptId());
            temp.setCode(dpt.getDeptCode());
            temp.setMemberCode(dpt.getMemberCode());
            temp.setManager(dpt.getDeptManager());
            temp.setName(dpt.getDeptName());
            temp.setOrgCode(dpt.getOrgCode());
            temp.setRemark(dpt.getDeptRemark());
            dtolist.add(temp);
        }
        return new BaseListDTO(dtolist, (int) resPage.getTotal());
    }

    @Override
    public int delete(Long id) {
        int res = dptMapper.deleteById(id);
        if (res != 1) {
            return -1;
        }
        return 0;
    }

    @Override
    public int batchDelete(List<Long> ids) {
        for (Long id : ids) {
            int res = dptMapper.deleteById(id);
            if (res != 1) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public DptDTO findOne(Long id) {
        Dpt dpt = dptMapper.selectById(id);
        if (dpt == null) {
            return null;
        }
        DptDTO dptDto = new DptDTO();
        dptDto.setId(dpt.getDeptId());
        dptDto.setCode(dpt.getDeptCode());
        dptDto.setName(dpt.getDeptName());
        dptDto.setMemberCode(dpt.getMemberCode());
        dptDto.setManager(dpt.getDeptManager());
        dptDto.setRemark(dpt.getDeptRemark());
        dptDto.setOrgCode(dpt.getOrgCode());
        return dptDto;
    }
}
