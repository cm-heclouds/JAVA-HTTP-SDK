package cmcc.iot.onenet.javasdk.model;

import java.util.Date;
import java.util.List;

public class Datapoints {
	//必须
	public String id;
	public List<Data>datapoints;
	public List<Data> getDatapoints() {
		return datapoints;
	}

	public void setDatapoints(List<Data> datapoints) {
		this.datapoints = datapoints;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Datapoints(String id, List<Data> datapoints) {
		super();
		this.id = id;
		this.datapoints = datapoints;
	}





	
	
}
