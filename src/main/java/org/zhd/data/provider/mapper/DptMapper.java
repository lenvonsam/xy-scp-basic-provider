package org.zhd.data.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zhd.data.provider.entity.DptBean;

import java.util.List;

public interface DptMapper extends BaseMapper<DptBean> {
    // 主键自增长 xml写法
    @Deprecated
    int insertDpt(DptBean dptBean);

    // 动态sql写法
    @Deprecated
    List<DptBean> findDptList(DptBean dptBean);
}
