package com.stephenhu.bipennis.util.Regular;

/**
 * 国际手机号验证工具类
 * @author Stephen Hu
 */
public final class PhoneRegular {

    /**
     * 国际手机号正则表达式
     */
    public static final String PHONE_REGULAR = "^\\+([1-9]\\d{0,2})\\s\\d{7,14}$";

    private PhoneRegular() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * 验证国际手机号格式
     * @param phone 待验证手机号（需包含+前缀）
     * @return 验证结果
     */
    public static boolean isInternationalPhone(String phone) {
        return phone != null && phone.matches(PHONE_REGULAR);
    }
}
