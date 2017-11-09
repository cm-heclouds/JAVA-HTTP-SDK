package cmcc.iot.onenet.javasdk.response.dtu;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DtuParserList {
	@JsonProperty("parsers")
	private ArrayList<ParsersItem> parsers;
	
	public static  class ParsersItem {
		@JsonProperty("id")
		private Integer id;
		@JsonProperty("name")
		private String name;
		@JsonProperty("fileName")
		private String fileName;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
		@JsonProperty("time")
		private Date time;
		
		@JsonCreator
		public ParsersItem(@JsonProperty("id")Integer id, @JsonProperty("name")String name, @JsonProperty("fileName")String fileName
				,@JsonProperty("time")Date time){
			this.id = id;
			this.name = name;
			this.fileName = fileName;
			this.time = time;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public Date getTime() {
			return time;
		}

		public void setTime(Date time) {
			this.time = time;
		}
	}

	public ArrayList<ParsersItem> getParsers() {
		return parsers;
	}

	public void setParsers(ArrayList<ParsersItem> parsers) {
		this.parsers = parsers;
	}
}
