package org.example.infrastructure.common;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = -3826891916021780628L;
    private String code;
    private String info;
    private T data;

    public Result(String code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}

