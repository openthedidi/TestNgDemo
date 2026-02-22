package com.framework.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtils {

    private DateUtils() {
    }

    private static final DateTimeFormatter TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private static final DateTimeFormatter READABLE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getTimestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMAT);
    }

    public static String getReadableTimestamp() {
        return LocalDateTime.now().format(READABLE_FORMAT);
    }
}
