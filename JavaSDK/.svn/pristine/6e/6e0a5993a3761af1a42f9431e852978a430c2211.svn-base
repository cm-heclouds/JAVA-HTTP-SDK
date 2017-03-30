package cmcc.iot.onenet.javasdk.response.mqtt;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TopicDeviceList {
	@JsonProperty("total_count")
	private int totalcount;
	@JsonProperty("per_page")
	private int perpage;
	@JsonProperty("page")
	private int page;
	@JsonProperty("devices")
	private ArrayList<String> devices;
	 
	@JsonCreator
	public TopicDeviceList(@JsonProperty("total_count")int totalcount,@JsonProperty("per_page") int perpage, @JsonProperty("page")int page,	@JsonProperty("devices") ArrayList<String> devices) {
		this.totalcount = totalcount;
		this.perpage = perpage;
		this.page = page;
		this.devices = devices;
	}
	
}
