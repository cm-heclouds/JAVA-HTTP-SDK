package cmcc.iot.onenet.javasdk.response.device;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceList {
	@JsonProperty("total_count")
	private int totalcount;
	@JsonProperty("per_page")
	private int perpage;
	@JsonProperty("page")
	private int page;
	@JsonProperty("devices")
	private ArrayList<DeviceItem> devices;
    @JsonCreator
    public DeviceList(@JsonProperty("total_count") int totalcount, @JsonProperty("per_page") int perpage, @JsonProperty("page") int page,@JsonProperty("devices")ArrayList<DeviceItem> devices ) {
        this.totalcount = totalcount;
        this.perpage = perpage;
        this.page = page;
        this.devices=devices;
    } 
	public static  class DeviceItem{
		@JsonProperty("id")
		private String id;
		@JsonProperty("title")
		private String title;
		@JsonProperty("protocol")
		private String protocol;
		@JsonProperty("desc")
		private String desc;
		@JsonProperty("private")
		private Boolean isPrivate;
		@JsonProperty("auth_info")
		private Object authInfo;
		@JsonProperty("online")
		private Boolean isonline;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonProperty("create_time")
		private Date createTime;
		@JsonProperty("location")
		private Location location;
		
		@JsonCreator
		public DeviceItem(@JsonProperty("id")String id, @JsonProperty("title")String title,@JsonProperty("protocol") String protocol, @JsonProperty("desc")String desc, @JsonProperty("private")Boolean isPrivate, @JsonProperty("auth_info")Object authInfo,
				@JsonProperty("online")Boolean isonline, @JsonProperty("create_time")Date createTime,@JsonProperty("location")Location location) {
			super();
			this.id = id;
			this.title = title;
			this.protocol = protocol;
			this.desc = desc;
			this.isPrivate = isPrivate;
			this.authInfo = authInfo;
			this.isonline = isonline;
			this.createTime = createTime;
			this.location=location;
		}

		public Location getLocation() {
			return location;
		}

		public void setLocation(Location location) {
			this.location = location;
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

		public Boolean getIsPrivate() {
			return isPrivate;
		}

		public void setIsPrivate(Boolean isPrivate) {
			this.isPrivate = isPrivate;
		}

		public Object getAuthInfo() {
			return authInfo;
		}

		public void setAuthInfo(Object authInfo) {
			this.authInfo = authInfo;
		}

		public Boolean getIsonline() {
			return isonline;
		}

		public void setIsonline(Boolean isonline) {
			this.isonline = isonline;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public static class Location{
			@JsonProperty("ele")
			private  double ele;
			@JsonProperty("lat")
			private  double lat;//纬度
			@JsonProperty("lon")
			private  double lon;//经度
			 
			@JsonCreator
			public Location(@JsonProperty("ele")double ele, @JsonProperty("lat")double lat, @JsonProperty("lon")double lon) {
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

	public List<DeviceItem> getDevices() {
		return devices;
	}

	public void setDevices(ArrayList<DeviceItem> devices) {
		this.devices = devices;
	}

}
