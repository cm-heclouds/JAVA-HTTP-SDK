package cmcc.iot.onenet.javasdk.response.cmds;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CmdsList {
	@JsonProperty("cursor")
	private String cursor;
	@JsonProperty("count")
	private Integer count;

	@JsonProperty("items")
	private List<CmdsItem> items;

	public static class CmdsItem{
		@JsonProperty("cmd_uuid")
		private String cmdUuid;
		@JsonProperty("dev_id")
		private Integer devId;
		@JsonProperty("expire_time")
		private String expireTime;
		@JsonProperty("status")
		private Integer status;
		@JsonProperty("send_time")
		private String sendTime;
		@JsonProperty("confirm_time")
		private String confirmTime;
		@JsonProperty("confirm_body")
		private String confirm_body;
		@JsonProperty("body")
		private String body;

		public String getCmdUuid() {
			return cmdUuid;
		}

		public void setCmdUuid(String cmdUuid) {
			this.cmdUuid = cmdUuid;
		}

		public Integer getDevId() {
			return devId;
		}

		public void setDevId(Integer devId) {
			this.devId = devId;
		}

		public String getExpireTime() {
			return expireTime;
		}

		public void setExpireTime(String expireTime) {
			this.expireTime = expireTime;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getSendTime() {
			return sendTime;
		}

		public void setSendTime(String sendTime) {
			this.sendTime = sendTime;
		}

		public String getConfirmTime() {
			return confirmTime;
		}

		public void setConfirmTime(String confirmTime) {
			this.confirmTime = confirmTime;
		}

		public String getConfirm_body() {
			return confirm_body;
		}

		public void setConfirm_body(String confirm_body) {
			this.confirm_body = confirm_body;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<CmdsItem> getItems() {
		return items;
	}

	public void setItems(List<CmdsItem> items) {
		this.items = items;
	}
}
