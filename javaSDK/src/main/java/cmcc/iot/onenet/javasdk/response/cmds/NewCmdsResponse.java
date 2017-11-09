package cmcc.iot.onenet.javasdk.response.cmds;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewCmdsResponse {
	@JsonProperty(value = "cmd_uuid")
	public String cmduuid;

	public String getCmduuid() {
		return cmduuid;
	}

	public void setCmduuid(String cmduuid) {
		this.cmduuid = cmduuid;
	}

}
