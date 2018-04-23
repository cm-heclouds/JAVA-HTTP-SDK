package cmcc.iot.onenet.javasdk.response.device;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceResponse {
	@JsonProperty("id")
	private String id;
	@JsonProperty("title")
	private String title;
	@JsonProperty("protocol")
	private String protocol;
	@JsonProperty("desc")
	private String desc;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("create_time")
	private Date createTime;
	@JsonProperty("tags")
	private List<String> tags;
	@JsonProperty("location")
	private Location location;
	@JsonProperty("private")
	private Boolean isPrivate;
	private String routeTo;
	@JsonProperty("auth_info")
	private Object authInfo;
	@JsonProperty("other")
	private Map<String, Object> other;
	@JsonProperty("interval")
	private Integer interval;
	@JsonProperty("online")
	private Boolean isonline;
	@JsonProperty("keys")
	private List<KeyItems> keys;
	@JsonProperty("datastreams")
	private List<DataStreamItem> dataStreams;

	@JsonCreator
	public DeviceResponse(@JsonProperty("id") String id, @JsonProperty("title") String title,
			@JsonProperty("protocol") String protocol, @JsonProperty("desc") String desc,
			@JsonProperty("create_time") Date createTime, @JsonProperty("tags") List<String> tags,
			@JsonProperty("location") Location location, @JsonProperty("private") Boolean isPrivate,
			@JsonProperty("auth_info") Object authInfo, @JsonProperty("other") Map<String, Object> other,
			@JsonProperty("interval") Integer interval, @JsonProperty("online") Boolean isonline,
			@JsonProperty("keys") List<KeyItems> keys, @JsonProperty("datastreams") List<DataStreamItem> dataStreams) {
		this.id = id;
		this.title = title;
		this.protocol = protocol;
		this.desc = desc;
		this.createTime = createTime;
		this.tags = tags;
		this.location = location;
		this.isPrivate = isPrivate;
		this.authInfo = authInfo;
		this.other = other;
		this.interval = interval;
		this.isonline = isonline;
		this.keys = keys;
		this.dataStreams = dataStreams;
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

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public String getRouteTo() {
		return routeTo;
	}

	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}

	public Object getAuthInfo() {
		return authInfo;
	}

	public void setAuthInfo(Object authInfo) {
		this.authInfo = authInfo;
	}

	public Map<String, Object> getOther() {
		return other;
	}

	public void setOther(Map<String, Object> other) {
		this.other = other;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Boolean getIsonline() {
		return isonline;
	}

	public void setIsonline(Boolean isonline) {
		this.isonline = isonline;
	}

	public List<KeyItems> getKeys() {
		return keys;
	}

	public void setKeys(List<KeyItems> keys) {
		this.keys = keys;
	}

	public List<DataStreamItem> getDataStreams() {
		return dataStreams;
	}

	public void setDataStreams(List<DataStreamItem> dataStreams) {
		this.dataStreams = dataStreams;
	}

	public static class KeyItems {
		@JsonProperty("title")
		private String title;
		@JsonProperty("key")
		private String keystr;

		@JsonCreator
		public KeyItems(@JsonProperty("title") String title, @JsonProperty("key") String keystr) {
			super();
			this.title = title;
			this.keystr = keystr;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class DataStreamItem {
		@JsonProperty("id")
		private String id;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonProperty("create_time")
		private Date createTime;
		@JsonProperty("tags")
		private List<String> tags;
		@JsonProperty("unit")
		private String unit;
		@JsonProperty("unit_symbol")
		private String unitSymbol;
		@JsonProperty("current_value")
		private Object currentValue;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonProperty("update_at")
		private Date updateAt;
		@JsonProperty("formula")
		private String formula;
		@JsonProperty("interval")
		private int interval;
		@JsonProperty("cmd")
		private String cmd;
		@JsonProperty("uuid")
		private String uuid;

		public DataStreamItem(@JsonProperty("id") String id, @JsonProperty("create_time") Date createTime,
				@JsonProperty("tags") List<String> tags, @JsonProperty("unit") String unit,
				@JsonProperty("unit_symbol") String unitSymbol, @JsonProperty("current_value") Object currentValue,
				@JsonProperty("update_at") Date updateAt, @JsonProperty("formula") String formula,
				@JsonProperty("interval") int interval, @JsonProperty("cmd") String cmd,
				@JsonProperty("uuid") String uuid) {
			this.id = id;
			this.createTime = createTime;
			this.tags = tags;
			this.unit = unit;
			this.unitSymbol = unitSymbol;
			this.currentValue = currentValue;
			this.updateAt = updateAt;
			this.formula = formula;
			this.interval = interval;
			this.cmd = cmd;
			this.uuid = uuid;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public List<String> getTags() {
			return tags;
		}

		public void setTags(List<String> tags) {
			this.tags = tags;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public String getUnitSymbol() {
			return unitSymbol;
		}

		public void setUnitSymbol(String unitSymbol) {
			this.unitSymbol = unitSymbol;
		}

		public Object getCurrentValue() {
			return currentValue;
		}

		public void setCurrentValue(Object currentValue) {
			this.currentValue = currentValue;
		}

		public Date getUpdateAt() {
			return updateAt;
		}

		public String getFormula() {
			return formula;
		}

		public void setFormula(String formula) {
			this.formula = formula;
		}

		public int getInterval() {
			return interval;
		}

		public void setInterval(int interval) {
			this.interval = interval;
		}

		public String getCmd() {
			return cmd;
		}

		public void setCmd(String cmd) {
			this.cmd = cmd;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public void setUpdateAt(Date updateAt) {
			this.updateAt = updateAt;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Location {

		@JsonProperty("ele")
		private double ele;
		@JsonProperty("lat")
		private double lat;// 纬度
		@JsonProperty("lon")
		private double lon;// 经度

		public Location(@JsonProperty("ele") double ele, @JsonProperty("lat") double lat,
				@JsonProperty("lon") double lon) {
			this.ele = ele;
			this.lat = lat;
			this.lon = lon;
		}

		public double getEle() {
			return ele;
		}

		public void setEle(double ele) {
			this.ele = ele;
		}

		public double getLat() {
			return lat;
		}

		public void setLat(double lat) {
			this.lat = lat;
		}

		public double getLon() {
			return lon;
		}

		public void setLon(double lon) {
			this.lon = lon;
		}

	}
}
