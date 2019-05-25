package org.zhd.data.provider.service.dubbo;

import org.xy.api.dpi.basic.EmployeeDpi;
import org.xy.api.dto.BaseListDTO;
import org.xy.api.dto.basic.EmployeeDTO;

import java.util.List;
import java.util.Map;

public class EmpProviderServiceImpl implements EmployeeDpi {
    @Override
    public EmployeeDTO save(EmployeeDTO model) {
        return null;
    }

    @Override
    public BaseListDTO<EmployeeDTO> findByPg(Map<String, Object> params) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int batchDelete(List<Long> ids) {
        return 0;
    }

    @Override
    public EmployeeDTO findOne(Long id) {
        return null;
    }
}
