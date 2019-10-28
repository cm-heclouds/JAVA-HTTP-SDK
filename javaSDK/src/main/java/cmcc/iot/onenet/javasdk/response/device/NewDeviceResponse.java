package cmcc.iot.onenet.javasdk.response.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewDeviceResponse {

    @JsonProperty(value="device_id")
    private String deviceId;


    @JsonProperty(value="psk")
    private String psk;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String DeviceId) {
        this.deviceId = DeviceId;
    }

    public String getPsk() {
        return psk;
    }

    public void setPsk(String psk) {
        this.psk = psk;
    }
}
