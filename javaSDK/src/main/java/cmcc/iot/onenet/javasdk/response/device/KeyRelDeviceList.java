package cmcc.iot.onenet.javasdk.response.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * 
 * @Description api-key关联设备查询接口返回结果实体
 * @author luowz
 * @date 2019/1/2 19:45
 * @version 1.0
 *
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyRelDeviceList {

    @JsonProperty("total_count")
    private int totalcount;
    @JsonProperty("per_page")
    private int perpage;
    @JsonProperty("page")
    private int page;
    @JsonProperty("devices")
    private ArrayList<KeyRelDeviceDetail> devices;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KeyRelDeviceDetail{
        @JsonProperty("create_time")
        private String createTime;
        @JsonProperty("name")
        private String name;
        @JsonProperty("id")
        private String id;

        @JsonProperty("desc")
        private String desc;
        @JsonProperty("act_time")
        private String actTime;
        @JsonProperty("last_ct")
        private String lastCt;


        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public int getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(int totalcount) {
        this.totalcount = totalcount;
    }

    public int getPerpage() {
        return perpage;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<KeyRelDeviceDetail> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<KeyRelDeviceDetail> devices) {
        this.devices = devices;
    }
}
