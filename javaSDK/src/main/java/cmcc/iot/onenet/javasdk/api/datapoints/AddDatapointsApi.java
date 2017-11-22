package cmcc.iot.onenet.javasdk.api.datapoints;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPostMethod;
import cmcc.iot.onenet.javasdk.model.Datapoints;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.utils.Config;

public class AddDatapointsApi extends AbstractAPI{
	private Map<String,List<Datapoints>> map;
	private String data;
	private Integer type;
	private  String devId;
	private HttpPostMethod HttpMethod;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * @param map :数据点内容,Map<String,List<Datapoints>>
	 * @param data:提供简写方式上传数据,String
	 * 示例：
	 * type=4
	 * data="{\"temperature\":{\"2015-03-22T22:31:12\":22.5}}";
	 * type=5
	 * data=",;temperature,2015-03-22T22:31:12,22.5;pm2.5,89";
	 * @param type:上传数据类型（可选，默认为完整JSON型，见HTTP内容示例）
	 * @param devId:设备ID,String
	 * @param key:masterkey 或者 设备apikey
	 */
	public AddDatapointsApi(Map<String,List<Datapoints>> map, String data, Integer type, String devId,String key) {
		super();
		this.map = map;
		this.data = data;
		this.type = type;
		this.devId = devId;
		this.key=key;
		this.method=Method.POST;

        Map<String, Object> headmap = new HashMap<String, Object>();
        HttpMethod=  new HttpPostMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url=Config.getString("test.url")+"/devices/"+devId+"/datapoints";
        Map<String, Object> urlmap = new HashMap<String, Object>();
        if(type!=null){
            urlmap.put("type", type);
        }
        // body参数
        String json=null;
        try {
            if(map!=null){
                json = mapper.writeValueAsString(map);
            }
            else{
                json=data;//支持其他类型
            }
        } catch (Exception e) {
            logger.error("json error", e.getMessage());
            throw new OnenetApiException(e.getMessage());
        }
        ((HttpPostMethod)HttpMethod).setEntity(json);
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
