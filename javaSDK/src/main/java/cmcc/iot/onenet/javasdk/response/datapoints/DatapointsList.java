package cmcc.iot.onenet.javasdk.response.datapoints;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class DatapointsList {
	@JsonProperty("count")
	private String count;
	@JsonProperty("cursor")
	private String cursor;
	
	public String getCursor() {
		return cursor;
	}
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}

	public ArrayList<DatastreamsItem> getDevices() {
		return devices;
	}




	public void setDevices(ArrayList<DatastreamsItem> devices) {
		this.devices = devices;
	}




	@JsonProperty("datastreams")
	private ArrayList<DatastreamsItem> devices;
	
	
	
	@JsonCreator
	public DatapointsList(@JsonProperty("count")String count, @JsonProperty("datastreams")ArrayList<DatastreamsItem> devices) {
		super();
		this.count = count;
		this.devices = devices;
	}




	public static  class DatastreamsItem{
		@JsonProperty("id")
		public String id;
		public String getId() {
			return id;
		}



		public void setId(String id) {
			this.id = id;
		}



		public List<DatapointsItem> getDatapoints() {
			return datapoints;
		}



		public void setDatapoints(List<DatapointsItem> datapoints) {
			this.datapoints = datapoints;
		}



		@JsonProperty("datapoints")
		public List<DatapointsItem>datapoints;
		
		
		@JsonCreator
		public DatastreamsItem(@JsonProperty("id")String id, @JsonProperty("datapoints")List<DatapointsItem> datapoints) {
			super();
			this.id = id;
			this.datapoints = datapoints;
		}



		public static class DatapointsItem{
			@JsonProperty("at")
			private String at;
			public String getAt() {
				return at;
			}

			public void setAt(String at) {
				this.at = at;
			}

			public Object getValue() {
				return value;
			}

			public void setValue(Object value) {
				this.value = value;
			}

			@JsonProperty("value")
			private Object  value;
		
			@JsonCreator
			public DatapointsItem(@JsonProperty("at")String at, @JsonProperty("value")Object value) {
				super();
				this.at = at;
				this.value = value;
			}
			
		}
	}
		
}
