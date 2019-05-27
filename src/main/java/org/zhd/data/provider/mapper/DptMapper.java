package org.zhd.data.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zhd.data.provider.entity.Dpt;

import java.util.List;

public interface DptMapper extends BaseMapper<Dpt> {
    // 主键自增长 xml写法
    @Deprecated
    int insertDpt(Dpt dpt);

    // 动态sql写法
    @Deprecated
    List<Dpt> findDptList(Dpt dpt);
}
