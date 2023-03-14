package com.ue.termi.enums;


public enum ResultCodeMsgEnum {
    SUCCESS(0, "成功"),
    SYS_ERR(1, "服务器错误，请联系管理员！"),
    BIZ_ERR(2, "服务器繁忙，请稍后重试"),
    RPC_ERR(3, "服务器繁忙，请稍后重试"),
    PARAM_ERR(4, "请求参数异常"),
    OPTIMISTIC_ERR(16, "数据版本错误，请刷新页面后操作"),
    UPDATE_ERR(20, "更新失败"),
    NO_DATA_ERR(25, "未查到数据")

    ;
    private int code;
    private String msg;

    ResultCodeMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
