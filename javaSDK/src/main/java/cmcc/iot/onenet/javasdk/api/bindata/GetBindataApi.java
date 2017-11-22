package cmcc.iot.onenet.javasdk.api.bindata;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.utils.Config;

public class GetBindataApi extends AbstractAPI{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private HttpGetMethod HttpMethod;
	private  String index;
	/**
	 * @param index:二进制数据索引号,String
	 * @param key:masterkey 或者 该设备的设备key
	 */
	public GetBindataApi(String index,String key) {
		this.index = index;
		this.key=key;
		this.method= Method.GET;
		this.HttpMethod=new HttpGetMethod(method);
        Map<String, Object> headmap = new HashMap<String, Object>();
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/bindata" + "/" + index;
        HttpMethod.setcompleteUrl(url,null);
	}

	public String executeApi() {
		String response=null;
         try {
        	 HttpResponse httpResponse=HttpMethod.execute();
			response = EntityUtils.toString(httpResponse.getEntity());
			return response;
		} catch (Exception e) {
			logger.error("error: {}" , e.getMessage());
			throw new OnenetApiException(e.getMessage());
		}finally {
			try {
				HttpMethod.httpClient.close();
			} catch (Exception e) {
				logger.error("http close error: {}", e.getMessage());
				throw new OnenetApiException(e.getMessage());
			}
		}
		
	}
}
