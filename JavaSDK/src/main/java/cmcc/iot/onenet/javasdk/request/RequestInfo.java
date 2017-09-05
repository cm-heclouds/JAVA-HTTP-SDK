package cmcc.iot.onenet.javasdk.request;

import java.util.Map;


public  interface RequestInfo {
	//定义一个枚举 
	public enum Method{
	    POST,GET,DELETE,PUT
	}
	public void setHeader(Map<String,Object> params);
	public void setEntity(Object json);
	public void setEntity(Map<String, String> stringMap,Map<String,String> fileMap);
	public void setcompleteUrl(String completeUrl,Map<String,Object> params);
	public void sethttpMethod(Method method);
	public void setType(boolean upload);

}
