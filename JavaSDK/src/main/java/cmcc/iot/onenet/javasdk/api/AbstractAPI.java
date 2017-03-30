package cmcc.iot.onenet.javasdk.api;

import java.util.Map;

import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;

public abstract class AbstractAPI {
	public String key;
	public String url;
	public Method method;
	public Map<String,String> urlparams;
	abstract public void build();
   
}
