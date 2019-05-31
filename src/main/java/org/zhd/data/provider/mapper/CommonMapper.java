package org.zhd.data.provider.mapper;

import org.apache.ibatis.annotations.Select;

public interface CommonMapper {
    @Select("select max(${columnName}) from ${tableName}")
    String getMaxCode(String columnName, String tableName);
}
