package com.hw.common.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author  
 * @date 2020/10/22 2:35 下午
 */
public class DateUtils {
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd hh:mm:ss";

    public static String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }
    public static Date nowToDate() {
        return new Date();
    }

    public static String format(LocalDateTime time) {
        return format(time, DEFAULT_PATTERN);
    }

    public static String format(LocalDateTime time, String pattern) {
        return DateTimeFormatter.ofPattern(DEFAULT_PATTERN).format(time);
    }
}
