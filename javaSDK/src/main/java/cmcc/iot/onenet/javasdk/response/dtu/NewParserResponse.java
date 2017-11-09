package cmcc.iot.onenet.javasdk.response.dtu;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewParserResponse {
	@JsonProperty(value = "id")
	public String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
