package cmcc.iot.onenet.javasdk.response.datastreams;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DatastreamsResponse {
	@JsonProperty("id")
	private  String id;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("create_time")
	private  Date createTime;
	@JsonProperty("tags")
	private List<String> tags;
	@JsonProperty("unit")
	private String unit;
	@JsonProperty("unit_symbol")
	private  String unitSymbol;
	@JsonProperty("current_value")
	private Object currentValue;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty("update_at")
	private Date updateAt;
	@JsonProperty("formula")
	private String formula;
	@JsonProperty("interval")
	private int interval;
	@JsonProperty("cmd")
	private String cmd;
	@JsonProperty("uuid")
	private String uuid;
	@JsonCreator
	public DatastreamsResponse(@JsonProperty("id")String id, @JsonProperty("create_time")Date createTime, @JsonProperty("tags")List<String> tags, @JsonProperty("unit")String unit, @JsonProperty("unit_symbol")String unitSymbol,
			@JsonProperty("current_value") Object currentValue,@JsonProperty("update_at")Date updateAt,@JsonProperty("formula")String formula,@JsonProperty("interval")int interval,@JsonProperty("cmd")String cmd,@JsonProperty("uuid")String uuid) {
		super();
		this.id = id;
		this.createTime = createTime;
		this.tags = tags;
		this.unit = unit;
		this.unitSymbol = unitSymbol;
		this.currentValue = currentValue;
		this.updateAt = updateAt;
		this.formula=formula;
		this.interval=interval;
		this.cmd=cmd;
		this.uuid=uuid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitSymbol() {
		return unitSymbol;
	}
	public void setUnitSymbol(String unitSymbol) {
		this.unitSymbol = unitSymbol;
	}
	public Object getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(Object currentValue) {
		this.currentValue = currentValue;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public int getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	/*public static  class CurrentValue{
		@JsonProperty("index")
		private String index;

	
		public String getIndex() {
			return index;
		}


		public void setIndex(String index) {
			this.index = index;
		}


		@JsonCreator
		public CurrentValue(@JsonProperty("index")String index) {
			super();
			this.index = index;
		}
	}*/
	
}
