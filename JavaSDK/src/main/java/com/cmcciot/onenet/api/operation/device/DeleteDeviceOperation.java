package com.cmcciot.onenet.api.operation.device;

import com.cmcciot.onenet.api.RequestInfo;
import com.cmcciot.onenet.api.operation.AbstractOperation;

/**
 * Created by Yuzhou on 2017/3/21.
 */
public class DeleteDeviceOperation extends AbstractOperation<Void> {

    private long deviceId;

    public DeleteDeviceOperation() {
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public RequestInfo getRequestInfo() {
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setPath("/devices/" + deviceId);
        requestInfo.setVerb(RequestInfo.Verb.DELETE);
        if (getKey() != null) {
            requestInfo.setKey(getKey());
        }
        return requestInfo;
    }
}
