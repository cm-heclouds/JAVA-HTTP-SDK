package cmcc.iot.onenet.javasdk.response.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @Description 4.7.6修改apikey关联设备列表接口 的返回结果对象
 * @author luowz
 * @date 2019/1/2 19:45
 * @version 1.0
 *
 **/

@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifyKeyRelDeviceResponse {
    @JsonProperty("add")
    private Integer add;
    @JsonProperty("del")
    private Integer del;
    public Integer getAdd() {
        return add;
    }

    public void setAdd(Integer add) {
        this.add = add;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}
