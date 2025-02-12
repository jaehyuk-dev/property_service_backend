package com.propertyservice.property_service.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtil {
    private static final DateTimeFormatter FORMAT_YEAR_MONTH = DateTimeFormatter.ofPattern("yyyy-MM");
    private static final DateTimeFormatter FORMAT_YEAR_MONTH_DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter FORMAT_YEAR_MONTH_DAY_HOUR_MIN = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // YYYY-MM 포맷
    public String formatYearMonth(LocalDateTime dateTime) {
        return dateTime.format(FORMAT_YEAR_MONTH);
    }

    // YYYY-MM-DD 포맷
    public String formatYearMonthDay(LocalDateTime dateTime) {
        return dateTime.format(FORMAT_YEAR_MONTH_DAY);
    }

    // YYYY-MM-DD HH:MM 포맷
    public String formatYearMonthDayHourMin(LocalDateTime dateTime) {
        return dateTime.format(FORMAT_YEAR_MONTH_DAY_HOUR_MIN);
    }
}
