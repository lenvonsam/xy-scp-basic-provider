package org.zhd.data.provider.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.xy.api.dpi.basic.OrgDpi;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.dto.basic.OrgDTO;
import org.zhd.data.provider.entity.DptBean;
import org.zhd.data.provider.entity.OrgBean;
import org.zhd.data.provider.service.OrgService;

import java.util.List;
import java.util.Map;

/**
 * @author samy
 * @date 2019-06-12
 */
@Service(version = "${api.service.version}", application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}", group = "${dubbo.group}")
public class OrgProviderService implements OrgDpi {
    @Autowired
    private OrgService orgService;

    @Override
    @TxcTransaction
    @Transactional
    public void save(OrgDTO model) throws Exception {
        OrgBean org = new OrgBean();
        if (model.getId() != null) org.setOrgId(model.getId());
        org.setOrgAbbreviate(model.getAbbreviate());
        org.setOrgName(model.getName());
        orgService.saveOrg(org);
    }

    @Override
    public BaseListDTO<OrgDTO> selectPage(Map<String, Object> params) throws Exception {
        return null;
    }

    @Override
    public void delete(List<Long> ids) throws Exception {

    }

    @Override
    public OrgDTO selectById(Long id) throws Exception {
        return null;
    }
}
