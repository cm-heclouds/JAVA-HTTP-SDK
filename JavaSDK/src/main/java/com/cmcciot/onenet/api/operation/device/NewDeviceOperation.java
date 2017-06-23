package com.cmcciot.onenet.api.operation.device;

import com.cmcciot.onenet.api.RequestInfo;
import com.cmcciot.onenet.api.operation.AbstractOperation;
import com.cmcciot.onenet.api.response.NewDeviceResponse;

/**
 * Created by Yuzhou on 2017/3/21.
 */
public class NewDeviceOperation extends AbstractOperation<NewDeviceResponse> {

    private Request request;

    public NewDeviceOperation() {
        this.request = new Request();
    }

    public void setTitle(String title) {
        this.request.setTitle(title);
    }

    @Override
    public RequestInfo getRequestInfo() {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setEntity(this.request);
        requestInfo.setPath("/devices");
        requestInfo.setVerb(RequestInfo.Verb.POST);
        if (getKey() != null) {
            requestInfo.setKey(getKey());
        }
        return requestInfo;
    }

    public static class Request {

        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
