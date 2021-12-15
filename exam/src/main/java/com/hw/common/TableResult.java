package com.hw.common;

import java.util.List;

/**
 * 表格数据
 * @author  
 * @date 2020/11/19 10:58 上午
 */
public class TableResult<T> {
    private Integer code;
    private String msg;
    private Integer count;
    private List<T> data;

    public TableResult() {
    }

    public TableResult(Integer code, String msg, Integer count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
