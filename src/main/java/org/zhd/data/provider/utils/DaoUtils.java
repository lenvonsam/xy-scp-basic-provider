package org.zhd.data.provider.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zhd.data.provider.mapper.CommonMapper;

import javax.annotation.PostConstruct;

/**
 * @author cth
 * @date 2019/06/03
 */
@Component
public class DaoUtils {
    private Logger log = LoggerFactory.getLogger(DaoUtils.class);
    @Autowired
    private CommonMapper commonMapper;

    private static CommonMapper commonMapperInst;
    @PostConstruct
    public void init() {
        commonMapperInst = commonMapper;
    }

    public static String getMaxCode(String columnName, String tableName) {
        String code = commonMapperInst.getMaxCode(columnName, tableName);
        Long codeLong = Long.valueOf(code) + 1;
        return fillLeft(codeLong.toString());
    }

    /**
     * 补位
     */
    private static String fillLeft(String source) {
        StringBuilder ret = new StringBuilder();
        if (null == source) {
            source = "";
        }
        int sourceLen = source.length();
        int len = 6;
        char fillChar = '0';
        if (sourceLen > len) {
            ret.append(source);
        } else {
            while (ret.toString().length() + sourceLen < len) {
                ret.append(fillChar);
            }
            ret.append(source);
        }
        return ret.toString();
    }
}
