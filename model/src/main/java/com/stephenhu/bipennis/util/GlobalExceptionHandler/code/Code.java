package com.stephenhu.bipennis.util.GlobalExceptionHandler.code;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码
 *
 * @author Stephen Hu
 */
public final class Code {
    public static final String OK = String.valueOf(0);
    public static final String CANCELLED = String.valueOf(1);
    public static final String UNKNOWN = String.valueOf(2);
    public static final String INVALID_ARGUMENT = String.valueOf(3);
    public static final String DEADLINE_EXCEEDED = String.valueOf(4);
    public static final String NOT_FOUND = String.valueOf(5);
    public static final String ALREADY_EXISTS = String.valueOf(6);
    public static final String PERMISSION_DENIED = String.valueOf(7);
    public static final String RESOURCE_EXHAUSTED = String.valueOf(8);
    public static final String FAILED_PRECONDITION = String.valueOf(9);
    public static final String ABORTED = String.valueOf(10);
    public static final String OUT_OF_RANGE = String.valueOf(11);
    public static final String UNIMPLEMENTED = String.valueOf(12);
    public static final String INTERNAL = String.valueOf(13);
    public static final String UNAVAILABLE = String.valueOf(14);
    public static final String DATA_LOSS = String.valueOf(15);
    public static final String UNAUTHENTICATED = String.valueOf(16);
    public static final String DATABASE_CONNECTION_ERROR = String.valueOf(17);
    public static final String DATABASE_QUERY_ERROR = String.valueOf(18);
    public static final String REDIS_CONNECTION_ERROR = String.valueOf(19);
    public static final String REDIS_OPERATION_ERROR = String.valueOf(20);
    public static final String FILE_READ_ERROR = String.valueOf(21);
    public static final String FILE_WRITE_ERROR = String.valueOf(22);

    /**
     * 错误码到HTTP状态码的映射
     */
    public static final Map<String, String> ERROR_CODE_TO_HTTP_STATUS = new HashMap<>();

    /**
     * 错误码到错误信息的映射
     * */
    public static final Map<String, String> ERROR_CODE_TO_MESSAGE = new HashMap<>();

    static {
        // 初始化错误码到HTTP状态码的映射
        ERROR_CODE_TO_HTTP_STATUS.put(Code.OK, String.valueOf(200));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.CANCELLED, String.valueOf(499));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.UNKNOWN, String.valueOf(500));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.INVALID_ARGUMENT, String.valueOf(400));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.DEADLINE_EXCEEDED, String.valueOf(504));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.NOT_FOUND, String.valueOf(404));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.ALREADY_EXISTS, String.valueOf(409));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.PERMISSION_DENIED, String.valueOf(403));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.RESOURCE_EXHAUSTED, String.valueOf(429));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.FAILED_PRECONDITION, String.valueOf(400));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.ABORTED, String.valueOf(409));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.OUT_OF_RANGE, String.valueOf(400));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.UNIMPLEMENTED, String.valueOf(501));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.INTERNAL, String.valueOf(500));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.UNAVAILABLE, String.valueOf(503));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.DATA_LOSS, String.valueOf(500));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.UNAUTHENTICATED, String.valueOf(401));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.DATABASE_CONNECTION_ERROR, String.valueOf(503));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.DATABASE_QUERY_ERROR, String.valueOf(500));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.REDIS_CONNECTION_ERROR, String.valueOf(503));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.REDIS_OPERATION_ERROR, String.valueOf(500));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.FILE_READ_ERROR, String.valueOf(500));
        ERROR_CODE_TO_HTTP_STATUS.put(Code.FILE_WRITE_ERROR, String.valueOf(500));

        // 初始化错误码到错误信息的映射
        ERROR_CODE_TO_MESSAGE.put(Code.OK, "Success");
        ERROR_CODE_TO_MESSAGE.put(Code.CANCELLED, "Request cancelled by client");
        ERROR_CODE_TO_MESSAGE.put(Code.UNKNOWN, "Unknown error");
        ERROR_CODE_TO_MESSAGE.put(Code.INVALID_ARGUMENT, "Invalid argument");
        ERROR_CODE_TO_MESSAGE.put(Code.DEADLINE_EXCEEDED, "Request timeout");
        ERROR_CODE_TO_MESSAGE.put(Code.NOT_FOUND, "Resource not found");
        ERROR_CODE_TO_MESSAGE.put(Code.ALREADY_EXISTS, "Resource already exists");
        ERROR_CODE_TO_MESSAGE.put(Code.PERMISSION_DENIED, "Permission denied");
        ERROR_CODE_TO_MESSAGE.put(Code.RESOURCE_EXHAUSTED, "Resource exhausted");
        ERROR_CODE_TO_MESSAGE.put(Code.FAILED_PRECONDITION, "Precondition failed");
        ERROR_CODE_TO_MESSAGE.put(Code.ABORTED, "Operation aborted");
        ERROR_CODE_TO_MESSAGE.put(Code.OUT_OF_RANGE, "Value out of range");
        ERROR_CODE_TO_MESSAGE.put(Code.UNIMPLEMENTED, "Function not implemented");
        ERROR_CODE_TO_MESSAGE.put(Code.INTERNAL, "Internal server error");
        ERROR_CODE_TO_MESSAGE.put(Code.UNAVAILABLE, "Service unavailable");
        ERROR_CODE_TO_MESSAGE.put(Code.DATA_LOSS, "Data loss");
        ERROR_CODE_TO_MESSAGE.put(Code.UNAUTHENTICATED, "Unauthenticated");
        ERROR_CODE_TO_MESSAGE.put(Code.DATABASE_CONNECTION_ERROR, "Database connection error");
        ERROR_CODE_TO_MESSAGE.put(Code.DATABASE_QUERY_ERROR, "Database query error");
        ERROR_CODE_TO_MESSAGE.put(Code.REDIS_CONNECTION_ERROR, "Redis connection error");
        ERROR_CODE_TO_MESSAGE.put(Code.REDIS_OPERATION_ERROR, "Redis operation error");
        ERROR_CODE_TO_MESSAGE.put(Code.FILE_READ_ERROR, "File read error");
        ERROR_CODE_TO_MESSAGE.put(Code.FILE_WRITE_ERROR, "File write error");
    }

}
