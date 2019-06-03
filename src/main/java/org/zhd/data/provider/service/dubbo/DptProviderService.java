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
    public void save(DptDTO model) {
    }

    @Override
    public BaseListDTO<DptDTO> selectPage(Map<String, Object> params) {
        return null;
    }

    @Override
    public void delete(List<Long> ids) {
    }

    @Override
    public DptDTO selectById(Long id) {
        return null;
    }
}
