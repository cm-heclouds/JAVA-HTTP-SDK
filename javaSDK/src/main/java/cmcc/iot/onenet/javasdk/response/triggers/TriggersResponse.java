package cmcc.iot.onenet.javasdk.response.triggers;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TriggersResponse {
	@JsonProperty("id")
	private String id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("ds_uuids")
	private List<String> dsuuids;
	@JsonProperty("url")
	private String url;
	@JsonProperty("type")
	private String type;
	@JsonProperty("threshold")
	private int threshold;
	@JsonProperty("invalid")
	private boolean invalid;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("create_time")
	private Date createtime;
	@JsonProperty("target_type")
	private Date targettype;
	@JsonProperty("dev_ids")
	private List<String> devIds;
	@JsonProperty("dev_details")
	private List<TriggerDeviceDetailItem> devDetails;
	@JsonProperty("ds_id")
	private String dsId;
	
	
	public List<String> getDevIds() {
		return devIds;
	}
	public void setDevIds(List<String> devIds) {
		this.devIds = devIds;
	}
	public Date getTargettype() {
		return targettype;
	}
	public void setTargettype(Date targettype) {
		this.targettype = targettype;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
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
	public List<String> getDsuuids() {
		return dsuuids;
	}
	public void setDsuuids(List<String> dsuuids) {
		this.dsuuids = dsuuids;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public boolean isInvalid() {
		return invalid;
	}
	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	public List<TriggerDeviceDetailItem> getDevDetails() {
		return devDetails;
	}

	public void setDevDetails(List<TriggerDeviceDetailItem> devDetails) {
		this.devDetails = devDetails;
	}

	public String getDsId() {
		return dsId;
	}

	public void setDsId(String dsId) {
		this.dsId = dsId;
	}
}
