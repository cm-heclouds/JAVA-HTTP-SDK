package cmcc.iot.onenet.javasdk.api.datastreams;

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
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.datastreams.NewdatastramsResponse;
import cmcc.iot.onenet.javasdk.utils.Config;

public class AddDatastreamsApi extends AbstractAPI{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private  String id;
	private  String devId;
	private  List<String> tags;
	private  String unit;
	private  String unitSymbol;
	private  String cmd;
	private  Integer interval;
	private  String formula;	
	private HttpPostMethod HttpMethod;
	/**
	 * 数据流新增
	 * @param id：数据流名称 ，String
	 * @param devId:设备ID,String
	 * @param tags:数据流标签（可选，可以为一个或多个）,List<Stirng>
	 * @param unit:单位（可选）,String
	 * @param unitSymbol:单位符号（可选）,String
	 * @param cmd:MODBUS设备填写，周期下发命令，16进制字节字符串
	 * @param interval:MODBUS设备填写，采集间隔，秒,Integer
	 * @param formula:MODBUS设备填写，寄存器计算公式（可选）,String
	 * @param key:masterkey 或者 设备apikey
	 */
	public AddDatastreamsApi(String id, String devId, List<String> tags, String unit, String unitSymbol, String cmd,
			Integer interval, String formula, String key) {
		super();
		this.id = id;
		this.devId = devId;
		this.tags = tags;
		this.unit = unit;
		this.unitSymbol = unitSymbol;
		this.cmd = cmd;
		this.interval = interval;
		this.formula = formula;
		this.key=key;
		this.method=Method.POST;
        Map<String, Object> headmap = new HashMap<String, Object>();
        HttpMethod=  new HttpPostMethod(method);
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url=Config.getString("test.url")+"/devices/"+devId+"/datastreams";
        // body参数
        Map<String, Object> bodymap = new HashMap<String, Object>();
        if(this.id!=null){
            bodymap.put("id", id);
        }
        if(this.tags!=null){
            bodymap.put("tags", tags);
        }
        if(this.unit!=null){
            bodymap.put("unit", unit);
        }
        if(this.unitSymbol!=null){
            bodymap.put("unit_symbol", unitSymbol);
        }
        if(this.cmd!=null){
            bodymap.put("cmd", cmd);
        }
        if(this.interval!=null){
            bodymap.put("interval", interval);
        }
        if(this.formula!=null){
            bodymap.put("formula", formula);
        }
        String json=null;
        ObjectMapper remapper = new ObjectMapper();
        try {
            json = remapper.writeValueAsString(bodymap);
        } catch (Exception e) {
            logger.error("json error {}", e.getMessage());
            throw new OnenetApiException(e.getMessage());
        }
        ((HttpPostMethod)HttpMethod).setEntity(json);
        HttpMethod.setcompleteUrl(url,null);
	}



	public BasicResponse<NewdatastramsResponse> executeApi(){
		BasicResponse response=null;
		try {
			HttpResponse httpResponse=HttpMethod.execute();
			response = mapper.readValue(httpResponse.getEntity().getContent(),BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), NewdatastramsResponse.class);
			response.setData(newData);
			return response;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
