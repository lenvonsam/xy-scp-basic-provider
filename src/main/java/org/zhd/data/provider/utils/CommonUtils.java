package org.zhd.data.provider.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 工具类
 * @author cth
 * @since 2019-06-10
 */
public class CommonUtils {
    private static final char UNDERLINE = '_';

    /**
     * 将带有_的字符串转为驼峰形式
     */
    public static String getHumpName(String underscoreName) {
        if (StringUtils.isBlank(underscoreName)) {
            return "";
        }
        int len = underscoreName.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = underscoreName.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(underscoreName.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 将带有_的字符串转为驼峰形式
     */
    public static String getUnderscoreName(String humpName) {
        if (StringUtils.isBlank(humpName)) {
            return "";
        }
        int len = humpName.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = humpName.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
