package org.zhd.data.provider.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.xy.api.dpi.basic.OrgDpi;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.dto.basic.OrgDTO;
import org.zhd.data.provider.core.entity.Org;
import org.zhd.data.provider.core.mapper.OrgMapper;
import org.zhd.data.provider.utils.DefaultEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
@Service(version = "${api.service.version}", application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}", group = "${dubbo.group}")
public class OrgProviderServiceImpl implements OrgDpi {
    private Logger log = LoggerFactory.getLogger(OrgProviderServiceImpl.class);

    @Autowired
    private OrgMapper orgMapper;

    @Override
    public OrgDTO save(OrgDTO model) {
        // 获取顶级机构
        Org orgDefault = orgMapper.selectById(DefaultEnum.ORG.getValue());
        if (orgDefault == null) {
            log.info(">>>找不到顶级机构...");
            return null;
        }
        // 赋值
        Org org = new Org();
//        org.setBasicShare();
//        org.setCompanyCode();
        org.setMemberCode(model.getMemberCode());
        org.setOrgAbbreviate(model.getAbbreviate());
        org.setOrgAccounts(model.getBankAccount());
        org.setOrgAddr(model.getAddr());
        org.setOrgBankname(model.getBankName());
        org.setOrgCode(model.getCode());
        org.setOrgCorporation(model.getCorporation());
        org.setOrgFax(model.getFax());
        // TODO

        // 区分更新还是保存
        int res = 0;
        if (model.getId() == null) {
            res = orgMapper.insertOrg(org);
        } else {
            org.setOrgId(model.getId());
            res = orgMapper.updateById(org);
        }
        if (res == 1) {
            log.info(">>>保存成功,id为:" + org.getOrgId());
            return model;
        } else {
            return null;
        }
    }

    @Override
    public BaseListDTO<OrgDTO> findByPg(Map<String, Object> params) {
        Integer currentPage = (Integer)params.get("currentPage");
        Integer pageSize = (Integer)params.get("pageSize");
        Page<Org> page = new Page<>(currentPage, pageSize);
        // mp 条件构造器
        QueryWrapper<Org> queryWrapper = new QueryWrapper<>();

        // 分页查询
        IPage<Org> resPage = orgMapper.selectPage(page, queryWrapper);
        List<OrgDTO> orgList = new ArrayList<>();
        for(Org org: resPage.getRecords()) {
            OrgDTO temp = new OrgDTO();
            temp.setAddr(org.getOrgAddr());
            temp.setName(org.getOrgName());
            temp.setCode(org.getOrgCode());
            temp.setPhone(org.getOrgPhone());
            temp.setBankName(org.getOrgBankname());
            temp.setCorporation(org.getOrgCorporation());
            temp.setFax(org.getOrgFax());
            temp.setTaxNo(org.getOrgTanu());
            orgList.add(temp);
        }
        return new BaseListDTO(orgList, (int) resPage.getTotal());
    }

    @Override
    public int delete(Long id) {
        int res = orgMapper.deleteById(id);
        if (res != 1) {
            return -1;
        }
        return 0;
    }

    @Override
    public int batchDelete(List<Long> ids) {
        for (Long id: ids) {
            int res = orgMapper.deleteById(id);
            if (res != 1) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public OrgDTO findOne(Long id) {
        Org org = orgMapper.selectById(id);
        if (org == null) {
            return null;
        }
        OrgDTO orgDTO = new OrgDTO();
        // TODO
        return orgDTO;
    }
}
