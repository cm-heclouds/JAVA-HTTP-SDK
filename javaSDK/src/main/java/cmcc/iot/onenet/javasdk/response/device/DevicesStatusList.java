package cmcc.iot.onenet.javasdk.response.device;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DevicesStatusList {
	@JsonProperty("total_count")
	private int totalcount;
	@JsonProperty("devices")
	private ArrayList<DeviceItem> devices;

	@JsonCreator
	public DevicesStatusList(@JsonProperty("total_count") int totalcount,
			@JsonProperty("devices") ArrayList<DeviceItem> devices) {
		this.totalcount = totalcount;
		this.devices = devices;
	}
	public static  class DeviceItem{
	

		@JsonProperty("id")
		private String id;
		@JsonProperty("title")
		private String title;
		@JsonProperty("online")
		private Boolean isonline;
		
		@JsonCreator
		public DeviceItem(@JsonProperty("id") String id, @JsonProperty("title") String title,@JsonProperty("online") Boolean isonline) {
			super();
			this.id = id;
			this.title = title;
			this.isonline = isonline;
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

		public Boolean getIsonline() {
			return isonline;
		}

		public void setIsonline(Boolean isonline) {
			this.isonline = isonline;
		}
		
	}
	public ArrayList<DeviceItem> getDevices() {
		return devices;
	}
	public void setDevices(ArrayList<DeviceItem> devices) {
		this.devices = devices;
	}
}
