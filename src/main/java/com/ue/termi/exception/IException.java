package com.ue.termi.exception;


import com.ue.termi.enums.ResultCodeMsgEnum;

public class IException extends RuntimeException {

    private Integer code;
    public IException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public IException(String message) {
        super(message);
        code = ResultCodeMsgEnum.BIZ_ERR.getCode();
    }
    public IException(ResultCodeMsgEnum enumValue) {
        super(enumValue.getMsg());
        this.code = enumValue.getCode();
    }

    public IException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public Integer getCode() {
        return code;
    }
}
