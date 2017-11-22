package cmcc.iot.onenet.javasdk.api.cmds;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.cmds.CmdsResponse;
import cmcc.iot.onenet.javasdk.utils.Config;

public class QueryCmdsRespApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HttpGetMethod HttpMethod;
	private String cmdUuid;

	/**
	 * @param cmdUuid:命令id,String
	 * @param key:masterkey或者设备apikey
	 */
	public QueryCmdsRespApi(String cmdUuid, String key) {
		this.cmdUuid = cmdUuid;
		this.key = key;
		this.method = Method.GET;
		this.HttpMethod = new HttpGetMethod(method);

		Map<String, Object> headmap = new HashMap<String, Object>();
		headmap.put("api-key", key);
		HttpMethod.setHeader(headmap);
		this.url = Config.getString("test.url") + "/cmds/" + cmdUuid + "/resp";
		HttpMethod.setcompleteUrl(url, null);
	}

	public String executeApi() {
		String resp = null;
		try {
			HttpResponse httpResponse = HttpMethod.execute();
			resp = EntityUtils.toString(httpResponse.getEntity());
			return resp;
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
