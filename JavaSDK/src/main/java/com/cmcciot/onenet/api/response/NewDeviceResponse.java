package com.cmcciot.onenet.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Yuzhou on 2017/3/21.
 */
public class NewDeviceResponse {

    @JsonProperty("device_id")
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
