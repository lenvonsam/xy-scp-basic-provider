package org.zhd.data.provider.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.zhd.data.provider.core.entity.Org;

public interface OrgMapper extends BaseMapper<Org> {
    int insertOrg(Org org);
}
