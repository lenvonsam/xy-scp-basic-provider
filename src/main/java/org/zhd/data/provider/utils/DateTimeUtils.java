package org.zhd.data.provider.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author cth
 * @date 2019/06/03
 */
public class DateTimeUtils {
    private static final DateTimeFormatter WEB_SHORT_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * String 转 Date
     */
    public static Date stringToDate(String shortDateStr) {
        LocalDate localDate = LocalDate.parse(shortDateStr, WEB_SHORT_DTF);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }
}
