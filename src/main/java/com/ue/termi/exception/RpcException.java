package com.ue.termi.exception;


import com.ue.termi.enums.ResultCodeMsgEnum;

public class RpcException extends RuntimeException {
    private Integer code;

    public RpcException() {
    }

    public RpcException(String message) {
        super(message);
        code = ResultCodeMsgEnum.RPC_ERR.getCode();
    }
    public RpcException(ResultCodeMsgEnum errorCode) {
        super(errorCode.getMsg());
        this.code = errorCode.getCode();
    }

    public RpcException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
