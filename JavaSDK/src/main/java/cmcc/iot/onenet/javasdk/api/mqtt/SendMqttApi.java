package cmcc.iot.onenet.javasdk.api.mqtt;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;

public class SendMqttApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String topic;
	private Object contents;// 用户自定义数据
	private HttpPostMethod HttpMethod;

	/**
	 * @param topic：设备订阅的主题（必选）,String
	 * @param contents:用户自定义数据：json、string、二进制数据（小于64K）
	 * @param key：masterkey
	 */
	public SendMqttApi(String topic, Object contents,String key) {
		this.topic = topic;
		this.contents = contents;
		this.key = key;
		this.method = Method.POST;
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		Map<String, Object> headmap = new HashMap<String, Object>();
		Map<String, Object> urlmap = new HashMap<String, Object>();
		HttpMethod = new HttpPostMethod(method);
		headmap.put("api-key", key);
		HttpMethod.setHeader(headmap);
		this.url = Config.getString("test.url") + "/mqtt";
		if (topic != null) {
			urlmap.put("topic", topic);
		}
		// body参数处理
		if (contents instanceof JSONObject) {
			((HttpPostMethod) HttpMethod).setEntity(contents.toString());
		}
		if (contents instanceof byte[]) {
			try {
				String s = new String((byte[]) contents, "UTF-8");
				((HttpPostMethod) HttpMethod).setEntity(s);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				logger.error("bytes[]  error", e.getMessage());
				throw new OnenetApiException();
			}
		}
		if (contents instanceof String) {
			((HttpPostMethod) HttpMethod).setEntity((String) contents);
		}
		HttpMethod.setcompleteUrl(url,urlmap);
	}
	public BasicResponse<Void> executeApi() {

		ObjectMapper mapper = new ObjectMapper();
		BasicResponse response = null;
		HttpResponse httpResponse = HttpMethod.execute();
		try {

			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			logger.error("json error", e.getMessage());
			throw new OnenetApiException();
		}
		try {
			HttpMethod.httpClient.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("http close error:" + e.getMessage());
			throw new OnenetApiException();
		}
		return response;
	}
	
}
