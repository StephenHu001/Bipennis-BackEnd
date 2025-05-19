package com.stephenhu.bipennis.util.Regular;

import java.util.regex.Pattern;

/**
 * 数字验证工具类 - 提供多种数字格式校验
 * 支持：整数、正整数、负整数、浮点数、正浮点数、负浮点数等
 * @author Stephen Hu
 */
public final class NumberRegular {

    /**
     * 防止私有化
     * */
    private NumberRegular() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 判断是否为任意格式的整数（支持正负号）
     * 包括：-123 | 456 | +789 | 0
     */
    public static boolean isInteger(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return Pattern.matches("^[+-]?\\d+$", input);
    }

    /**
     * 判断是否为正整数（不包含0）
     * 格式：+123 | 456 | 789
     */
    public static boolean isPositiveInteger(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return Pattern.matches("^[+]?\\d+$", input);
    }

    /**
     * 判断是否为无符号的正整数（不包含0）
     * 格式：123 | 4567 | 9
     * @param input 待验证的字符串
     * @return 如果输入是无符号正整数，返回 true；否则返回 false
     */
    public static boolean isUnsignedPositiveInteger(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return Pattern.matches("^[1-9]\\d*$", input);
    }

    /**
     * 判断是否为负整数
     * 格式：-123 | -456
     */
    public static boolean isNegativeInteger(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return Pattern.matches("^-\\d+$", input);
    }

    /**
     * 判断是否为零
     * 包括：0 | +0 | -0
     */
    public static boolean isZero(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return Pattern.matches("^[+-]?0+$", input);
    }

    /**
     * 判断是否为任意格式的浮点数
     * 支持：-123.45 | +45.67 | 0.89 | .5 | 123.
     */
    public static boolean isDecimal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        String regex = "^[+-]?(\\d+(\\.\\d*)|\\.\\d+)([eE][+-]?\\d+)?$";
        return Pattern.matches(regex, input);
    }

    /**
     * 判断是否为正浮点数
     * 支持：+45.67 | 0.89 | 123. | .5
     */
    public static boolean isPositiveDecimal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        String regex = "^([+]?(\\d+(\\.\\d*)|\\.\\d+))([eE][+-]?\\d+)?$";
        return Pattern.matches(regex, input);
    }

    /**
     * 判断是否为负浮点数
     * 支持：-123.45 | -0.5
     */
    public static boolean isNegativeDecimal(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        String regex = "^-(\\d+(\\.\\d*)|\\.\\d+)([eE][+-]?\\d+)?$";
        return Pattern.matches(regex, input);
    }

    /**
     * 判断是否为科学计数法表示的数字
     * 支持：1.23e4 | 3E5 | -4.56E7
     */
    public static boolean isScientificNotation(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        String regex = "^[+-]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][+-]?\\d+)$";
        return Pattern.matches(regex, input);
    }

    /**
     * 判断是否为有效的百分数（0-100）
     * 支持：0% | 50% | 100%
     */
    public static boolean isPercentage(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return Pattern.matches("^(100|[1-9][0-9]|[0-9])%$", input);
    }

    /**
     * 判断是否为指定精度的数字
     * @param input 输入字符串
     * @param decimalPlaces 接受的小数位数（>0）
     */
    public static boolean isPrecisionNumber(String input, int decimalPlaces) {
        if (input == null || input.isEmpty() || decimalPlaces < 0) {
            return false;
        }

        // 构建指定精度的正则表达式
        String regex = String.format("^[+-]?(\\d+(\\.\\d{1,%d})?|\\.\\d{1,%d})([eE][+-]?\\d+)?$",
                decimalPlaces, decimalPlaces);
        return Pattern.matches(regex, input);
    }

    /**
     * 判断是否为货币格式
     * 支持：$123.45 | $1,234.56 | -$567.89
     * @param input 输入字符串
     * */
    public static boolean isCurrency(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        // 支持格式：$123.45 | $1,234.56 | -$567.89
        String regex = "^\\$[-+]?([1-9]\\d{0,2}(,\\d{3})*(\\.\\d{2})?|0\\.\\d{2})$";
        return Pattern.matches(regex, input);
    }

}
