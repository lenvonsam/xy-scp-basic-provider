package org.zhd.data.provider.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zhd.data.provider.core.entity.Dpt;

import java.util.List;

public interface DptMapper extends BaseMapper<Dpt> {
    int insertDpt(Dpt dpt);

    List<Dpt> findDptList(Dpt dpt);
}
