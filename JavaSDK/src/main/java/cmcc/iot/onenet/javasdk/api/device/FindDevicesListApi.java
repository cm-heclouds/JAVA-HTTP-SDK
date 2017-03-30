package cmcc.iot.onenet.javasdk.api.device;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.device.DeviceList;
import cmcc.iot.onenet.javasdk.utils.Config;

public class FindDevicesListApi extends AbstractAPI {
	private String keywords;
	private Object authinfo;
	private String devid;
	private Date begin;
	private Date end;
	private String tag;
	private Boolean isPrivate;
	private Boolean isOnline;
	private Integer page;
	private Integer perpage;
	private HttpGetMethod HttpMethod;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 模糊查询设备
	 * 参数顺序与构造函数顺序一致
	 * @param keywords:匹配关键字（可选，从id和title字段中左匹配）,String
	 * @param authinfo:鉴权信息（可选，对应注册时的sn参数，唯一设备编号）,Object
	 * @param devid: 指定设备ID，多个用逗号分隔，最多100个（可选）,String
	 * @param begin:起始时间，包括当天（可选）,Date
	 * @param end:结束时间，包括当天（可选）,Date
	 * @param tags:设备标签（可选，可为一个或多个）,List<String>
	 * @param isPrivate： 设备私密性，Boolean类型
	 * @param page:指定页码，最大页数为10000（可选）,Integer
	 * @param perpage:指定每页输出设备个数，默认30，最多100（可选）,Integer
	 * @param isOnline:在线状态（可选）
	 * @param key:masterkey
	 */
	public FindDevicesListApi(String keywords, Object authinfo, String devid, Date begin, Date end, String tags,
			Boolean isPrivate, Integer page, Integer perpage, Boolean isOnline, String key) {
		this.keywords = keywords;
		this.authinfo = authinfo;
		this.devid = devid;
		this.begin = begin;
		this.end = end;
		this.tag = tags;
		this.isPrivate = isPrivate;
		this.page = page;
		this.perpage = perpage;
		this.method = Method.GET;
		this.key = key;
	}

	@Override
	public void build() {
		// TODO Auto-generated method stub
		Map<String, Object> headmap = new HashMap<String, Object>();
		Map<String, Object> urlmap = new HashMap<String, Object>();
		HttpMethod = new HttpGetMethod(method);
		this.url = Config.getString("test.url") + "/devices";
		// url参数
		if (keywords != null) {
			urlmap.put("key_words", keywords);
		}
		if (authinfo != null) {
			urlmap.put("auth_info", authinfo);
		}
		if (tag != null) {
			urlmap.put("tag", tag);
		}
		if (isOnline != null) {
			urlmap.put("online", isOnline);
		}
		if (isPrivate != null) {
			urlmap.put("private", isPrivate);
		}
		if (page != null) {
			urlmap.put("page", page);
		}
		if (perpage != null) {
			urlmap.put("per_page", perpage);
		}
		if (devid != null) {
			urlmap.put("device_id", devid);
		}
		if (begin != null) {
			urlmap.put("begin", begin);
		}
		if (end != null) {
			urlmap.put("end", end);
		}
		headmap.put("api-key", key);
		HttpMethod.setHeader(headmap);
		HttpMethod.setcompleteUrl(url,urlmap);
	}

	public BasicResponse<DeviceList> executeApi() {
		BasicResponse response = null;
		ObjectMapper mapper = new ObjectMapper();
		HttpResponse httpResponse = HttpMethod.execute();
		try {
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), DeviceList.class);
			response.setData(newData);

		} catch (Exception e) {
			logger.error("error:" + e.getMessage());
			throw new OnenetApiException();
		}
		return response;

	}
	
}
