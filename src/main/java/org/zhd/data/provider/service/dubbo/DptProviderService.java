package org.zhd.data.provider.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import org.xy.api.dpi.basic.DptDpi;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.dto.basic.DptDTO;

import java.util.List;
import java.util.Map;

@Service(version = "${api.service.version}", application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}", registry = "${dubbo.registry.id}", group = "${dubbo.group}")
public class DptProviderService implements DptDpi {
    @Override
    public DptDTO save(DptDTO model) throws Exception {
        return null;
    }

    @Override
    public BaseListDTO<DptDTO> findByPg(Map<String, Object> params) throws Exception {
        return null;
    }

    @Override
    public int delete(Long id) throws Exception {
        return 0;
    }

    @Override
    public int batchDelete(List<Long> ids) throws Exception {
        return 0;
    }

    @Override
    public DptDTO findOne(Long id) throws Exception {
        return null;
    }
}
