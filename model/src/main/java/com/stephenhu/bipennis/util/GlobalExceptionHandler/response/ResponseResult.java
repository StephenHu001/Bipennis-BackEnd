package com.stephenhu.bipennis.util.GlobalExceptionHandler.response;

import lombok.Data;

/**
 * @author Stephen Hu
 */
@Data
public final class ResponseResult<T> {
    /**
     * 编码
     * */
    private String code;
    /**
     * 提示
     * */
    private String msg;
    /**
     * 数据
     * */
    private T data;

    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseResult() {

    }
}
