package org.zhd.data.provider.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtils {
    private static final DateTimeFormatter webShortDtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // String 转 Date
    public static Date stringToDate(String shortDateStr) {
        LocalDate localDate = LocalDate.parse(shortDateStr, webShortDtf);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }
}
