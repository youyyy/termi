package com.ue.termi.vo;


import com.ue.termi.exception.IException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author
 */
@Data
@Builder
public class ResponseResult<T> implements Serializable {

    private int code;

    private T data;

    private String msg;
    public ResponseResult(){

    }
    public ResponseResult(int code, T data) {
        this.code = code;
        this.data = data;
    }
    public ResponseResult(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
    public static ResponseResult success(Object data) {
        return ResponseResult.builder().data(data).code(Constants.SUCCESS).msg("SUCCESS").build();
    }
    public static ResponseResult success() {
        return new ResponseResult(Constants.SUCCESS, (Object) null);
    }


    public static ResponseResult sysError() {
        return ResponseResult.builder().code(Constants.ERROR).msg("服务器异常").build();
    }

    public static ResponseResult paramError(String msg) {
        return ResponseResult.builder().code(Constants.PARAM_ERROR).msg(msg).build();
    }

    public static ResponseResult error(int code, String msg) {
        return ResponseResult.builder().code(code).msg(msg).build();
    }

    public static ResponseResult error(IException iException) {
        return ResponseResult.builder().code(iException.getCode()).msg(iException.getMessage()).build();
    }

    public static ResponseResult response(int code, String msg, Object data) {
        return ResponseResult.builder().data(data).code(code).msg(msg).build();
    }

    public boolean isSuccess() {
        return Constants.SUCCESS == code;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Constants {
        /**
         * 成功
         */
        public static final int SUCCESS = 0;

        /**
         * 服务器异常
         */
        public static final int ERROR = 500;

        /**
         * 业务失败(业务阻断而非异常)
         */
        public static final int FAILURE = 5001;

        /**
         * 参数错误
         */
        public static final int PARAM_ERROR = 400;

        /**
         * 未授权
         */
        public static final int UNAUTHORIZED = 401;
    }
}
