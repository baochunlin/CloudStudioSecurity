package com.cloudstudio.security.entity;

import com.alibaba.fastjson.JSON;

/**
 * 统一返回对象
 */
public class Result {

    private Integer code;

    private String message;

    private Object data;

    public Result() {
    }

    public Result(Object data) {
        this.code = 200;
        this.message = "OK";
        this.data = data;
    }

    public Result(String message, Object data) {
        this.code = 200;
        this.message = message;
        this.data = data;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result ok() {
        return new Result(null);
    }

    public static Result ok(String message) {
        return new Result(message, null);
    }

    public static Result ok(Object data) {
        return new Result(data);
    }

    public static Result ok(String message, Object data) {
        return new Result(message, data);
    }

    public static Result build(Integer code, String message) {
        return new Result(code, message, null);
    }

    public static Result build(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }

    public String toJsonString() {
        return JSON.toJSONString(this);
    }

    public static Result format(String json) {
        try {
            return JSON.parseObject(json, Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
