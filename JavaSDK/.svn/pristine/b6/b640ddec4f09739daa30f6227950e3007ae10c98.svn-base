package cmcc.iot.onenet.javasdk.response.triggers;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TriggersList {
	@JsonProperty("total_count")
	private int totalcount;
	@JsonProperty("per_page")
	private int perpage;
	@JsonProperty("page")
	private int page;
	@JsonProperty("triggers")
	private List<TriggersItem>list;
	@JsonCreator
	public TriggersList(@JsonProperty("total_count")int totalcount,@JsonProperty("per_page") int perpage, @JsonProperty("page")int page, @JsonProperty("triggers")List<TriggersItem> list) {
		this.totalcount = totalcount;
		this.perpage = perpage;
		this.page = page;
		this.list = list;
	}
	public static  class TriggersItem{
		@JsonProperty("id")
		private String id;
		@JsonProperty("title")
		private String title;
		@JsonProperty("ds_uuids")
		private List<String> dsuuids;
		@JsonProperty("url")
		private String url;
		@JsonProperty("type")
		private String type;
		@JsonProperty("threshold")
		private int threshold;
		@JsonProperty("invalid")
		private boolean invalid;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonProperty("create_time")
		private Date createtime;
		@JsonProperty("target_type")
		private Date targettype;
		@JsonCreator
		public TriggersItem(@JsonProperty("id")String id,@JsonProperty("title") String title, @JsonProperty("ds_uuids")List<String> dsuuids,@JsonProperty("url") String url, @JsonProperty("type")String type, @JsonProperty("threshold")int threshold,
				@JsonProperty("invalid")	boolean invalid,@JsonProperty("create_time") Date createtime, 	@JsonProperty("target_type")Date targettype) {
			this.id = id;
			this.title = title;
			this.dsuuids = dsuuids;
			this.url = url;
			this.type = type;
			this.threshold = threshold;
			this.invalid = invalid;
			this.createtime = createtime;
			this.targettype = targettype;
		}
		
	}
}
