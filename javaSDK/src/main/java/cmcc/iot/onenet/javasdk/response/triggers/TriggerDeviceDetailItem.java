package cmcc.iot.onenet.javasdk.response.triggers;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @Description triger绑定的设备列表的设备详情
 * @author luowz
 * @date 2019/1/2 11:06
 * @version 1.0
 *
 **/
public class TriggerDeviceDetailItem {
    @JsonProperty(value="id")
    private String id;

    @JsonProperty(value="title")
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
