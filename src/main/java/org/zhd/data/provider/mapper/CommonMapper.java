package org.zhd.data.provider.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @author cth
 * @date 2019/06/03
 */
public interface CommonMapper {
    /**
     * 通用 获取最大code值
     * @param columnName 表字段名
     * @param tableName 表名
     * @return string
     */
    @Select("select max(${columnName}) from ${tableName}")
    String getMaxCode(String columnName, String tableName);
}
