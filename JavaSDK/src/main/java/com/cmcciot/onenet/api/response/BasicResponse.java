package com.cmcciot.onenet.api.response;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Created by Yuzhou on 2017/3/21.
 */
public class BasicResponse<T> {

    private int errno;
    private String error;
    @JsonProperty("data")
    private Object dataInternal;
    private T data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @JsonGetter("data")
    public Object getDataInternal() {
        return dataInternal;
    }
    @JsonSetter("data")
    public void setDataInternal(Object dataInternal) {
        this.dataInternal = dataInternal;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
