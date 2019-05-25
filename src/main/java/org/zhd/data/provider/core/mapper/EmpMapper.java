package org.zhd.data.provider.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zhd.data.provider.core.entity.Emp;

public interface EmpMapper extends BaseMapper<Emp> {
    int insertEmp(Emp emp);
}
