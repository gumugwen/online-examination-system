package com.hw.common;

/**
 * @author  
 * @date 2020/10/22 9:05 上午
 */
public enum RespCode {

    /**
     * 请求成功
     */
    SUCCESS(200,"请求成功"),
    FAIL(500,"请求失败"),

    LOGIN_FAIL(2001,"用户名或密码错误"),
    LOGIN_SUCCESS(2000,"登录成功"),
    NOT_VALID(400,"参数非法"),
    NOT_LOGIN(4001,"请先登录"),
    FORBIDDENED(403,"权限不足"),
    USER_NOT_EXIST(400,"用户不存在"),
    REPEAT_COMMIT(4002,"请勿重复请求"),
    ;


    private int code;
    private String msg;

    RespCode(int code, String msg) {
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
