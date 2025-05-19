package com.stephenhu.bipennis.util.Regular;

import java.util.regex.Pattern;

/**
 * @author Stephen Hu
 */
public final class PasswordRegular {

    // 密码规则：
    // 1. 长度8-32位
    // 2. 必须包含大小写字母和数字
    // 3. 必须包含特殊字符（!@#$%^&*()-_=+[]{}|;:,.<>?）
    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+\\[\\]{}|;:,.<>?]).{8,32}$";

    // 禁止实例化
    private PasswordRegular() {}

    /**
     * 验证密码是否符合基本安全要求
     * @param password 待验证的密码字符串
     * @return boolean 验证结果
     */
    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        return Pattern.matches(PASSWORD_PATTERN, password);
    }
}
