package cmcc.iot.onenet.javasdk.response.bindata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewBindataResponse {
	 @JsonProperty(value="index")
	 public String index;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
}
