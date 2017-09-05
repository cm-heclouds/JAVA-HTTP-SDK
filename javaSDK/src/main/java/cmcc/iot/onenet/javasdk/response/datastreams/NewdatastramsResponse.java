package cmcc.iot.onenet.javasdk.response.datastreams;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewdatastramsResponse {
	@JsonProperty(value = "ds_uuid")
	public String id;

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}
}
