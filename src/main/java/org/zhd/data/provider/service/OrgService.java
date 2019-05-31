package org.zhd.data.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xy.api.dto.BaseListDTO;
import org.zhd.data.provider.entity.Org;
import org.zhd.data.provider.mapper.OrgMapper;
import org.zhd.data.provider.utils.DaoUtils;
import org.zhd.data.provider.utils.DefaultEnum;

import java.util.List;
import java.util.Map;

@Service
public class OrgService {
    private Logger log = LoggerFactory.getLogger(OrgService.class);

    @Autowired
    private OrgMapper orgMapper;

    public Org saveOrg(Org org) throws Exception{
        // 获取顶级部门
        Org orgDefault = orgMapper.selectById(DefaultEnum.ORG.getValue());
        if (orgDefault == null) {
            log.info(">>>找不到顶级机构...");
            throw new Exception("找不到顶级部门...");
        }
        // 赋值
        org.setMemberCode("0000");
        org.setOrgCode(DaoUtils.getMaxCode("org_code", "basic_org"));
        org.setOrgParent(orgDefault.getOrgCode());
        org.setOrgNodecode(orgDefault.getOrgNodecode() + "." + org.getOrgCode());

        // 区分更新还是保存
        int res = 0;
        if (org.getOrgId() == null) {
            res = orgMapper.insert(org);
        } else {
            res = orgMapper.updateById(org);
        }
        if (res == 1) {
            log.info(">>>保存成功,id为:" + org.getOrgId());
            return org;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public BaseListDTO<Org> findOrgListByPg(Map<String, Object> params) {
        Integer currentPage = (Integer) params.get("currentPage");
        Integer pageSize = (Integer) params.get("pageSize");
        Page<Org> page = new Page<>(currentPage, pageSize);
        // mp 条件构造器
        QueryWrapper<Org> queryWrapper = new QueryWrapper<>();
        String orgName = (String) params.get("orgName");
        String orgCode = (String) params.get("orgCode");
        if (orgName != null) {
            queryWrapper.like("org_name", orgName);
        }
        if (orgCode != null) {
            queryWrapper.like("org_code", orgCode);
        }
        // 分页查询
        IPage<Org> resPage = orgMapper.selectPage(page, queryWrapper);
        return new BaseListDTO(resPage.getRecords(), (int) resPage.getTotal());
    }

    public int deleteOrg(Long id) {
        int res = orgMapper.deleteById(id);
        if (res != 1) {
            return -1;
        }
        return 0;
    }

    public int batchDeleteOrg(List<Long> ids) {
        for (Long id : ids) {
            int res = orgMapper.deleteById(id);
            if (res != 1) {
                return -1;
            }
        }
        return 0;
    }

    public Org findOrgById(Long id) {
        Org org = orgMapper.selectById(id);
        if (org == null) {
            return null;
        }
        return org;
    }
}
