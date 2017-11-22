package cmcc.iot.onenet.javasdk.api.triggers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.http.HttpPutMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;

public class ModifyTriggersApi extends AbstractAPI {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private String tirggerId;
	private String title;
	private String dsid;
	private List<String> devids;
	private List<String>dsuuids;
	private String desturl;
	private String type;
	private Integer threshold;
	private HttpPutMethod HttpMethod;

	/**
	 * 触发器更新
	 * @param tirggerId:触发器ID,String
	 * @param title:设备名（可选）,String
	 * @param dsid 数据流名称（id）（可选）,String
	 * @param devids:设备ID（可选）,List<String>
	 * @param dsuuids:数据流uuid（可选）,List<String>
	 * @param desturl:url,String
	 * @param type:触发类型，String
	 * @param threshold:阙值，根据type不同，见以下说明,Integer
	 * @param key:masterkey 或者 设备apikey
	 */
	public ModifyTriggersApi(String tirggerId, String title, String dsid, List<String> devids, List<String> dsuuids,
			String desturl, String type, Integer threshold,String key) {
		super();
		this.tirggerId = tirggerId;
		this.title = title;
		this.dsid = dsid;
		this.devids = devids;
		this.dsuuids = dsuuids;
		this.desturl = desturl;
		this.type = type;
		this.threshold = threshold;
		this.key = key;
		this.method = Method.PUT;
        Map<String, Object> headmap = new HashMap<String, Object>();
        HttpMethod=  new HttpPutMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url=Config.getString("test.url")+"/triggers/"+tirggerId;
        // body参数
        Map<String, Object> bodymap = new HashMap<String, Object>();
        if (title != null) {
            bodymap.put("title", title);
        }
        if (dsid != null) {
            bodymap.put("ds_id", dsid);
        }
        if (devids != null) {
            bodymap.put("dev_ids", devids);
        }
        if (dsuuids != null) {
            bodymap.put("ds_uuids", dsuuids);
        }
        if (desturl != null) {
            bodymap.put("url", desturl);
        }
        if (type != null) {
            bodymap.put("type", type);
        }
        if (threshold != null) {
            bodymap.put("threshold", threshold);
        }
        String json=null;
        try {
            json = mapper.writeValueAsString(bodymap);
        } catch (Exception e) {

            logger.error("json error {}", e.getMessage());
            throw new OnenetApiException(e.getMessage());
        }
        ((HttpPutMethod)HttpMethod).setEntity(json);
        HttpMethod.setcompleteUrl(url,null);
	}

	public BasicResponse<Void> executeApi() {
		ObjectMapper mapper = new ObjectMapper();
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