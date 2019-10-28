package cmcc.iot.onenet.javasdk.api;


import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractAPI <T>{
	public String key;
	public String url;
	public Method method;
    public ObjectMapper mapper = initObjectMapper();
    private ObjectMapper initObjectMapper(){
    	ObjectMapper objectMapper = new ObjectMapper();
    	//关闭字段不识别报错
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		return objectMapper;
	}
}
