package cmcc.iot.onenet.javasdk.api.datastreams;

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

public class DeleteDatastreamsApi extends AbstractAPI{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String devId;
	private HttpDeleteMethod HttpMethod;
	private String datastreamId;
	/**
	 * @param devId
	 * @param datastreamId
	 * @param key
	 */
	public DeleteDatastreamsApi(String devId, String datastreamId,String key) {
		this.devId = devId;
		this.datastreamId = datastreamId;
		this.key=key;
		this.method = Method.DELETE;
        Map<String, Object> headmap = new HashMap<String, Object>();
        HttpMethod = new HttpDeleteMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices/" + devId+"/datastreams/"+datastreamId;
        HttpMethod.setcompleteUrl(url,null);
	}


	public BasicResponse<Void> executeApi() {
		BasicResponse response = null;
		HttpResponse httpResponse = HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
		} catch (Exception e) {
			logger.error("json error {}", e.getMessage());
			throw new OnenetApiException();
		}
		try {
			HttpMethod.httpClient.close();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("http close error: {}",e.getMessage());
			throw new OnenetApiException();
		}
		return response;
	}
}
