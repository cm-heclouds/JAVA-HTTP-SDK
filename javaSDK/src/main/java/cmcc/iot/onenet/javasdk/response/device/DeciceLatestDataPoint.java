package cmcc.iot.onenet.javasdk.response.device;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeciceLatestDataPoint {
	@JsonProperty("total_count")
	private int totalcount;
	@JsonProperty("devices")
	private ArrayList<DeviceItem> devices;

	@JsonCreator
	public DeciceLatestDataPoint(@JsonProperty("total_count") int totalcount,
			@JsonProperty("devices") ArrayList<DeviceItem> devices) {
		this.totalcount = totalcount;
		this.devices = devices;
	}

	public static class DeviceItem {
		@JsonProperty("id")
		private String id;
		@JsonProperty("title")
		private String title;
		@JsonProperty("datastreams")
		private ArrayList<DatastreamsItem> datastreams;

		@JsonCreator
		public DeviceItem(@JsonProperty("id") String id, @JsonProperty("title") String title,
				@JsonProperty("datastreams") ArrayList<DatastreamsItem> datastreams) {
			super();
			this.id = id;
			this.title = title;
			this.datastreams = datastreams;
		}

		public static class DatastreamsItem
		{
			@JsonProperty("id")
			private String id;
			@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
			@JsonProperty("at")
			private Date at;
			@JsonProperty("value")
			private Object value;

			@JsonCreator
			public DatastreamsItem(@JsonProperty("id") String id,@JsonProperty("at") Date at,
					@JsonProperty("value") Object value) {
				super();
				this.id = id;
				this.at = at;
				this.value = value;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public Date getAt() {
				return at;
			}

			public void setAt(Date at) {
				this.at = at;
			}

			public Object getValue() {
				return value;
			}

			public void setValue(Object value) {
				this.value = value;
			}

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

		public ArrayList<DatastreamsItem> getDatastreams() {
			return datastreams;
		}

		public void setDatastreams(ArrayList<DatastreamsItem> datastreams) {
			this.datastreams = datastreams;
		}

	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public ArrayList<DeviceItem> getDevices() {
		return devices;
	}

	public void setDevices(ArrayList<DeviceItem> devices) {
		this.devices = devices;
	}

}
