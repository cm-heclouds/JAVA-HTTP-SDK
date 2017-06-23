package com.cmcciot.onenet.api;

/**
 * Created by Yuzhou on 2017/3/21.
 */
public class RequestInfo {
    public enum Verb {POST, GET, PUT, DELETE}

    private Verb verb;

    private String path;

    private String key;

    private Object entity;

    public Verb getVerb() {
        return verb;
    }

    public void setVerb(Verb verb) {
        this.verb = verb;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
}
