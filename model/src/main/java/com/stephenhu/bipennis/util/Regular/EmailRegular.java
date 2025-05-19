package com.stephenhu.bipennis.util.Regular;

/**
 * @author Stephen Hu
 */
public final class EmailRegular {
    public static final String EMAIL_REGULAR = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    /**
     * 防止私有化
     * */
    private EmailRegular() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean isEmail(String email) {
        return email.matches(EMAIL_REGULAR);
    }
}
