package com.stephenhu.bipennis.util.GlobalExceptionHandler.error;

import lombok.Getter;

/**
 * API异常类（增强HTTP状态码支持）
 * @author Stephen Hu
 */
@Getter
public final class ApiException extends RuntimeException {
    private final String errorCode;
    private final String location;
    private final String httpStatus;

    public ApiException(String errorCode, String message, String location, String httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.location = location;
        this.httpStatus = httpStatus;
    }
}
