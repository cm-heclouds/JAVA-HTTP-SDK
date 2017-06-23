package com.cmcciot.onenet.api.operation;

import com.cmcciot.onenet.api.OnenetAPI;
import com.cmcciot.onenet.api.OnenetApiException;
import com.cmcciot.onenet.api.RequestInfo;
import com.cmcciot.onenet.api.response.BasicResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Yuzhou on 2017/3/21.
 */
public abstract class AbstractOperation<T> implements Operation<T> {
    private String key;

    public abstract RequestInfo getRequestInfo();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Class getResponseClass() {
        Type type = this.getClass().getGenericSuperclass();
        if (!(type instanceof ParameterizedType)) {
            return Object.class;
        }

        Type param = ((ParameterizedType)type).getActualTypeArguments()[0];

        if (!(param instanceof Class)) {
            return Object.class;
        }

        return (Class) param;
    }

    public BasicResponse<T> execute(OnenetAPI api)  {
        try {
            Object o = api.exectute(getRequestInfo(), getResponseClass());
            return (BasicResponse<T>) o;
        } catch (Exception e) {
            throw new OnenetApiException();
        }
    }
}
