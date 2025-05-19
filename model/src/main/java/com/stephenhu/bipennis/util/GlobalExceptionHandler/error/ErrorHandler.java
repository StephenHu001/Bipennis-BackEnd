package com.stephenhu.bipennis.util.GlobalExceptionHandler.error;

import com.stephenhu.bipennis.util.GlobalExceptionHandler.code.Code;

/**
 * 错误处理工具类
 *
 * @author Stephen Hu
 */
public final class ErrorHandler {

    /**
     * 私有构造函数，防止实例化
     */
    private ErrorHandler() {
    }

    /**
     * 根据错误代码获取对应的HTTP状态码
     *
     * @param errorCode 错误代码
     * @return 对应的HTTP状态码
     */
    public static String getHttpStatus(String errorCode) {
        return Code.ERROR_CODE_TO_HTTP_STATUS.getOrDefault(errorCode, String.valueOf(500));
    }

    /**
     * 抛出API异常
     *
     * @param errorCode 错误代码
     * @param location  错误发生的位置
     */
    public static void throwApiException(String errorCode, String location) {
        String httpStatus = getHttpStatus(errorCode);
        String errorMessage = getErrorMessage(errorCode);

        throw new ApiException(errorCode, errorMessage, location, httpStatus);
    }


    /**
     * 根据错误代码获取错误信息
     *
     * @param errorCode 错误代码
     * @return 错误信息
     */
    public static String getErrorMessage(String errorCode) {
        return Code.ERROR_CODE_TO_MESSAGE.getOrDefault(errorCode, "Internal server error");
    }
}
