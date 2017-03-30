package cmcc.iot.onenet.javasdk.api.triggers;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpDeleteMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;

public class DeleteTriggersApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String tirggerId;
	private HttpDeleteMethod HttpMethod;
	
	/**
	 * @param tirggerid
	 * @param key
	 */
	public DeleteTriggersApi(String tirggerId,String key) {
		this.tirggerId = tirggerId;
		this.key=key;
		this.method = Method.DELETE;
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		Map<String, Object> headmap = new HashMap<String, Object>();
		HttpMethod = new HttpDeleteMethod(method);
		headmap.put("api-key", key);
		HttpMethod.setHeader(headmap);
		this.url = Config.getString("test.url") + "/triggers" + "/" + tirggerId;
		HttpMethod.setcompleteUrl(url,null);
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
			//e.printStackTrace();
			logger.error("http close error:" + e.getMessage());
			throw new OnenetApiException();
		}
		return response;
	}
}
