package com.waterhub.web.model;

import java.io.Serializable;

public class MyResponse<T> implements Serializable {

    private String message;
    private T data;

    public MyResponse() {
        this(null, null);
    }

    public MyResponse(String message) {
        this(message, null);
    }

    public MyResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
