package cmcc.iot.onenet.javasdk.api.mqtt;

import java.io.UnsupportedEncodingException;
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
        if (contents instanceof byte[]) {
            try {
                String s = new String((byte[]) contents, "UTF-8");
                ((HttpPostMethod) HttpMethod).setEntity(s);
            } catch (UnsupportedEncodingException e) {
                logger.error("bytes[]  error {}", e.getMessage());
                throw new OnenetApiException(e.getMessage());
            }
        }
        if (contents instanceof String) {
            ((HttpPostMethod) HttpMethod).setEntity((String) contents);
        }
        HttpMethod.setcompleteUrl(url,urlmap);
	}

	public BasicResponse<Void> executeApi() {
		BasicResponse response = null;
		try {
			HttpResponse httpResponse = HttpMethod.execute();
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
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