package cmcc.iot.onenet.javasdk.api.datapoints;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.datapoints.DatapointsList;
import cmcc.iot.onenet.javasdk.response.datastreams.DatastreamsResponse;
import cmcc.iot.onenet.javasdk.utils.Config;

public class GetDatapointsListApi extends AbstractAPI{
	private HttpGetMethod HttpMethod;
	private String datastreamIds;
	private String start;
	private String end;
	private String devId;
	private Integer duration;
	private Integer limit;
	private String cursor;
	private Integer interval;
	private String metd;
	private Integer first;
	private String sort;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 数据点查询
	 * @param datastreamIds:查询的数据流，多个数据流之间用逗号分隔（可选）,String
	 * @param start:提取数据点的开始时间（可选）,String
	 * @param end:提取数据点的结束时间（可选）,String
	 * @param devId:设备ID,String
	 * @param duration:查询时间区间（可选，单位为秒）,Integer
	 *  start+duration：按时间顺序返回从start开始一段时间内的数据点
	 *  end+duration：按时间倒序返回从end回溯一段时间内的数据点
	 * @param limit:限定本次请求最多返回的数据点数，0<n<=6000（可选，默认1440）,Integer
	 * @param cursor:指定本次请求继续从cursor位置开始提取数据（可选）,String
	 * @param interval:通过采样方式返回数据点，interval值指定采样的时间间隔（可选）,Integer，参数已废弃
	 * @param metd:指定在返回数据点时，同时返回统计结果，可能的值为（可选）,String
	 * @param first:返回结果中最值的时间点。1-最早时间，0-最近时间，默认为1（可选）,Integer
	 * @param sort:值为DESC|ASC时间排序方式，DESC:倒序，ASC升序，默认升序,String
	 * @param key:masterkey 或者 设备apikey
	 */
	public GetDatapointsListApi(String datastreamIds, String start, String end, String devId, Integer duration,
			Integer limit, String cursor, @Deprecated Integer interval, String metd, Integer first, String sort,String key) {
		super();
		this.datastreamIds = datastreamIds;
		this.start = start;
		this.end = end;
		this.devId = devId;
		this.duration = duration;
		this.limit = limit;
		this.cursor = cursor;
		this.interval = interval;
		this.metd = metd;
		this.first = first;
		this.sort = sort;
		this.key=key;
		this.method= Method.GET;
		this.HttpMethod = new HttpGetMethod(method);

        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> urlmap = new HashMap<String, Object>();
        headmap.put("api-key", key);
        HttpMethod.setHeader(headmap);
        this.url = Config.getString("test.url") + "/devices/" + devId + "/datapoints";
        // url参数
        if (datastreamIds != null) {
            urlmap.put("datastream_id", datastreamIds);
        }
        if (start != null) {
            urlmap.put("start", start);
        }
        if (end != null) {
            urlmap.put("end", end);
        }
        if (duration != null) {
            urlmap.put("duration", duration);
        }
        if (limit != null) {
            urlmap.put("limit", limit);
        }
        if (cursor != null) {
            urlmap.put("cursor", cursor);
        }
        if (interval != null) {
            urlmap.put("interval", interval);
        }
        if (metd != null) {
            urlmap.put("method", metd);
        }
        if (first != null) {
            urlmap.put("first", first);
        }
        if (sort != null) {
            urlmap.put("sort", sort);
        }
        HttpMethod.setcompleteUrl(url, urlmap);
	}

	public BasicResponse<DatapointsList> executeApi() {
		BasicResponse response;
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try {
			HttpResponse httpResponse = HttpMethod.execute();
			response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
			response.setJson(mapper.writeValueAsString(response));
			Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()),DatapointsList.class);
			response.setData(newData);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("error: {}", e.getMessage());
			throw new OnenetApiException(e.getMessage());
		}
		return response;

	}
}
