package cmcc.iot.onenet.javasdk.api.device;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.device.NewDeviceResponse;
import cmcc.iot.onenet.javasdk.response.device.RegDeviceResponse;
import cmcc.iot.onenet.javasdk.utils.Config;

public class RegisterDeviceApi extends AbstractAPI{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String code;
	private HttpPostMethod HttpMethod;
	private String sn;
	private String mac;
	private String title;
	
	/**
	 * 
	 * @param code：设备注册码（必填）,String
	 * @param mac：设备唯一mac标识，最长32字符
	 * @param sn：设备唯一标识String类型，最长512字符
	 * @param title:设备名（可选） 最长32个字符
	 * @param key:设备注册码（必填）
	 */
	public RegisterDeviceApi(String code,String mac, String sn, String title,String key) {
		this.code = code;
		this.key = mac;
		this.sn=sn;
		this.title=title;
		this.key=key;
		this.mac=mac;
		this.method = Method.POST;
        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> urlmap = new HashMap<String, Object>();
        HttpMethod=  new HttpPostMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        url=Config.getString("test.url")+"/register_de";

        Map<String, Object> bodymap = new HashMap<String, Object>();
        if(sn!=null){
            bodymap.put("sn", sn);
        }
        if(mac!=null){
            bodymap.put("mac", mac);
        }
        if(title!=null){
            bodymap.put("title", title);
        }
        String json=null;
        ObjectMapper remapper = new ObjectMapper();
        try {
            json = remapper.writeValueAsString(bodymap);
        } catch (Exception e) {
            logger.error("json error:{}", e.getMessage());
            throw new OnenetApiException(e.getMessage());
        }
        if(code!=null){
            urlmap.put("register_code", code);
        }
        ((HttpPostMethod)HttpMethod).setEntity(json);
        HttpMethod.setcompleteUrl(url,urlmap);
	}


	public BasicResponse<RegDeviceResponse> executeApi(){
		BasicResponse response=null;
		try {
			HttpResponse httpResponse=HttpMethod.execute();
			response = mapper.readValue(httpResponse.getEntity().getContent(),BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), RegDeviceResponse.class);
			response.setData(newData);
			return response;
		} catch (Exception e) {
			logger.error("json error {}", e.getMessage());
			throw new OnenetApiException(e.getMessage());
		}
		finally {
			try {
				HttpMethod.httpClient.close();
			} catch (Exception e) {
				logger.error("http close error: {}", e.getMessage());
				throw new OnenetApiException(e.getMessage());
			}
		}
	}
}
