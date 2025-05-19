package com.stephenhu.bipennis.util.Regular;

import java.util.regex.Pattern;

/**
 * @author Stephen Hu
 */
public final class DateRegular {
    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
    /**
     * 判断是否为有效的日期格式
     * 支持：2023-01-01 | 2023-01-01 | 2023-01-01 | 2023-01-01 | 2023-01-01 | 2023-01-01 | 2023-01-01 | 2023-01-01 | 2023-01-01 | 2023
     * */
    public static boolean isValidDate(String dateStr) {
        if (dateStr == null || !Pattern.matches(DATE_PATTERN, dateStr)) {
            return false;
        }

        String[] parts = dateStr.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        // 基础日期有效性验证
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }

        // 简单闰年判断（未处理百年规则）
        boolean isLeapYear = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);

        // 各月份最大天数验证
        int[] monthDays = isLeapYear ?
                new int[]{31,29,31,30,31,30,31,31,30,31,30,31} :
                new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

        return day <= monthDays[month - 1];
    }
}
