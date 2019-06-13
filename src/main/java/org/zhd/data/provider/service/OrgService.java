package org.zhd.data.provider.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xy.api.dto.BaseListDTO;
import org.zhd.data.provider.entity.OrgBean;
import org.zhd.data.provider.mapper.OrgMapper;
import org.zhd.data.provider.utils.DaoUtils;
import org.zhd.data.provider.utils.DefaultEnum;

import java.util.List;
import java.util.Map;

/**
 * @author cth
 * @date 2019/06/03
 */
@Service
public class OrgService {
    private Logger log = LoggerFactory.getLogger(OrgService.class);

    @Autowired
    private OrgMapper orgMapper;

    @Transactional
    @LcnTransaction
    public OrgBean saveOrg(OrgBean orgBean){
        // 获取顶级部门
        OrgBean orgDefault = orgMapper.selectById(DefaultEnum.ORG.getValue());
        if (orgDefault == null) {
            throw new RuntimeException("找不到顶级机构...");
        }
        // 赋值
        if (StringUtils.isBlank(orgBean.getOrgAbbreviate())) {
            orgBean.setOrgAbbreviate(orgBean.getOrgName());
        }
        orgBean.setMemberCode("0000");
        orgBean.setOrgCode(DaoUtils.getMaxCode("org_code", "basic_org"));
        orgBean.setOrgParent(orgDefault.getOrgCode());
        orgBean.setOrgNodecode(orgDefault.getOrgNodecode() + "." + orgBean.getOrgCode());

        // 区分更新还是保存
        int res = 0;
        if (orgBean.getOrgId() == null) {
            res = orgMapper.insert(orgBean);
        } else {
            res = orgMapper.updateById(orgBean);
        }
        if (res == 1) {
            log.info(">>>保存成功,id为:" + orgBean.getOrgId());
            return orgBean;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public BaseListDTO<OrgBean> selectOrgPage(Map<String, Object> params) {
        Integer currentPage = (Integer) params.get("currentPage");
        Integer pageSize = (Integer) params.get("pageSize");
        Page<OrgBean> page = new Page<>(currentPage, pageSize);
        // mp 条件构造器
        QueryWrapper<OrgBean> queryWrapper = new QueryWrapper<>();
        String orgName = (String) params.get("orgName");
        String orgCode = (String) params.get("orgCode");
        if (orgName != null) {
            queryWrapper.like("org_name", orgName);
        }
        if (orgCode != null) {
            queryWrapper.like("org_code", orgCode);
        }
        // 不显示顶级机构
        queryWrapper.ne("org_id", "1");
        // 排序
        queryWrapper.orderByDesc("org_id");
        // 分页查询
        IPage<OrgBean> resPage = orgMapper.selectPage(page, queryWrapper);
        return new BaseListDTO(resPage.getRecords(), (int) resPage.getTotal());
    }

    public int deleteOrg(List<Long> ids) {
        return orgMapper.deleteBatchIds(ids);
    }

    public OrgBean selectOrgById(Long id) {
        OrgBean org = orgMapper.selectById(id);
        if (org == null) {
            return null;
        }
        return org;
    }

    /**
     * 根据名称查询机构列表
     * @param orgName orgName支持模糊查询
     * @return list
     */
    public List<OrgBean> selectOrgList(String orgName) {
        QueryWrapper<OrgBean> queryWrapper = new QueryWrapper<>();
        if (orgName != null) {
            queryWrapper.like("org_name", orgName);
        }
        return orgMapper.selectList(queryWrapper);
    }
}
