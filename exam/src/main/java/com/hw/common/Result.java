package com.hw.common;

import java.io.Serializable;

public class Result implements Serializable {

    private static final long serialVersionUID = 8961788437022562205L;

    private int code;
    private Object data;
    private boolean flag;
    private String message;

    public static  Result ok (){
        return new Result(RespCode.SUCCESS.getCode(),true,RespCode.SUCCESS.getMsg());
    }

    public static Result fail(){
        return new Result(RespCode.FAIL.getCode(),false,RespCode.FAIL.getMsg());
    }

    public static Result ok(Object data,String msg){
        return new Result(RespCode.SUCCESS.getCode(),data,true,msg);
    }
    public static Result ok(String msg){
        return new Result(RespCode.SUCCESS.getCode(),null,true,msg);
    }
    public static Result ok(RespCode respCode){
        return new Result(respCode.getCode(),null,true,respCode.getMsg());
    }
    public static Result ok(RespCode respCode,String message){
        return new Result(respCode.getCode(),null,true,message);
    }
    public static Result ok(RespCode respCode,String message,Object data){
        return new Result(respCode.getCode(),data,true,message);
    }
    public static Result ok(RespCode respCode,Object data){
        return new Result(respCode.getCode(),data,true,respCode.getMsg());
    }

    public static Result fail(Object data,String msg){
        return new Result(RespCode.FAIL.getCode(),data,false,msg);
    }
    public static Result fail(String msg){
        return new Result(RespCode.FAIL.getCode(),null,false,msg);
    }
    public static Result fail(RespCode respCode){
        return new Result(respCode.getCode(),null,false,respCode.getMsg());
    }
    public static Result fail(RespCode respCode,String message){
        return new Result(respCode.getCode(),null,false,message);
    }
    public static Result fail(RespCode respCode,Object data){
        return new Result(respCode.getCode(),data,false,respCode.getMsg());
    }


    public Result(int code, Object data, boolean flag,String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.flag = flag;
    }

    public Result(int code, boolean flag, String message) {
        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", data=" + data +
                ", flag=" + flag +
                ", message='" + message + '\'' +
                '}';
    }
}
