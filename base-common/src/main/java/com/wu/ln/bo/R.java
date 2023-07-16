package com.wu.ln.bo;

import java.util.Date;

public class R<T> {

    private int code;

    private String msg;

    private T data;

    private Date timestamp;

    public R() {
    }

    public R(int code, String msg, T data, Date timestamp) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
