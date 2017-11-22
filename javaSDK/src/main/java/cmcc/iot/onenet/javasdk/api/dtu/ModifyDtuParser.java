package cmcc.iot.onenet.javasdk.api.dtu;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.http.HttpPutMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;

public class ModifyDtuParser extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String name;
	private String filepath;
	private HttpPutMethod HttpMethod;
	private Integer id;

   /**
    * TCP透传更新
    * @param id :parserId ,Integer
    * @param name:名字, String
    * @param filepath:路径，String
    * @param key:masterkey 或者 设备apikey
    */
	public ModifyDtuParser(Integer id, String name, String filepath, String key) {
		this.name = name;
		this.filepath = filepath;
		this.key = key;
		this.method = Method.PUT;
		Map<String, Object> headmap = new HashMap<String, Object>();
		HttpMethod = new HttpPutMethod(method);
		headmap.put("api-key", key);
		HttpMethod.setHeader(headmap);
		if (id == null) {
			throw new OnenetApiException("parser id is required ");
		}
		this.url = Config.getString("test.url") + "/dtu/parser/" + id;
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("parser", filepath);
		Map<String, String> stringMap = new HashMap<String, String>();
		stringMap.put("name", name);
		((HttpPutMethod) HttpMethod).setEntity(stringMap, fileMap);
		HttpMethod.setcompleteUrl(url, null);
	}

	public BasicResponse executeApi() {
		BasicResponse response = null;
		try {
			HttpResponse httpResponse = HttpMethod.execute();
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			return response;
		} catch (Exception e) {
			logger.error("json error {}", e.getMessage());
			throw new OnenetApiException(e.getMessage());
		} finally {
			try {
				HttpMethod.httpClient.close();
			} catch (Exception e) {
				logger.error("http close error: {}", e.getMessage());
				throw new OnenetApiException(e.getMessage());
			}
		}

	}

}
