package cmcc.iot.onenet.javasdk.api.cmds;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.cmds.CmdsList;
import cmcc.iot.onenet.javasdk.utils.Config;
import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description 查询历史命令分页列表
 * @author luowz
 * @date 2018/12/28 15:20
 * @version 1.0
 *
 **/
public class GetCmdsHistoryApi extends AbstractAPI{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private HttpGetMethod HttpMethod;
    /***
     * 设备id
     **/
//    private String devId;
//    /***
//     * master-key
//     **/
//    private String key;
//    /***
//     * 指定开始时间,必选
//     **/
//    private String start;
//    /***
//     * 指定结束时间,可选
//     **/
//    private String end;
//    /***
//     * 时间间隔，可选，默认不传，单位秒，最大一周（duration和end只能选一个）
//     **/
//    private Integer duration;
//    /***
//     * 命令条数，默认10，最大6000
//     **/
//    private Integer limit;
//    /***
//     * 命令条数，默认10，最大6000
//     **/
//    private String cursor;
//
//    /***
//     * 值为DESC|ASC时间排序方式，DESC:降序，ASC升序，默认降序
//     **/
//    private String sort;
//


    /**
     * 根据响应状态查询，可选
     * 1：命令已创建| Command Created
     * 2：命令已发往设备| Command Sent
     * 3：命令发往设备失败| Send Command Failed
     * 4：设备正常响应| Command Response Received
     * 5：命令执行超时| Command Response Timeout
     * 6：设备响应消息过长 | Command Response Too Large
     */
//    private Integer status;

    /**
     * 查询历史命令分页列表请求构造方法
     * @param devId
     * @param key
     * @param start
     * @param end
     * @param duration
     * @param limit
     * @param cursor
     * @param sort
     * @param status
     */
    public GetCmdsHistoryApi(String devId, String key, String start, String end, Integer duration, Integer limit, String cursor, String sort, Integer status) {
//        this.devId = devId;
//        this.key = key;
//        this.start = start;
//        this.end = end;
//        this.duration = duration;
//        this.limit = limit;
//        this.cursor = cursor;
//        this.sort = sort;
//        this.status = status;

        this.method = Method.GET;
        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> urlmap = new HashMap<String, Object>();
        this.HttpMethod = new HttpGetMethod(method);
        Asserts.notBlank(devId,"devId");
        this.url = Config.getString("test.url") + "/cmds/history/"+devId;
        Asserts.notBlank(key,"api-key");
        headmap.put("api-key", key);
        if(start!=null){
            urlmap.put("start",start);
        }
        if(end!=null){
            urlmap.put("end",end);
        }
        if(duration!=null){
            urlmap.put("duration",duration);
        }
        if(limit!=null){
            urlmap.put("limit",limit);
        }
        if(cursor!=null){
            urlmap.put("cursor",cursor);
        }
        if(cursor!=null){
            urlmap.put("cursor",cursor);
        }
        if(sort!=null){
            urlmap.put("sort",sort);
        }
        if(status!=null){
            urlmap.put("status",status);
        }
        this.HttpMethod.setHeader(headmap);
        this.HttpMethod.setcompleteUrl(this.url,urlmap);
    }

    public BasicResponse<CmdsList> executeApi() {
        BasicResponse response;
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            HttpResponse httpResponse = HttpMethod.execute();
            InputStream instreams = httpResponse.getEntity().getContent();
            String str = IOUtils.toString(instreams, "UTF-8");
            response = mapper.readValue(str, BasicResponse.class);
            response.setJson(str);
            Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), CmdsList.class);
            response.setData(newData);
            return response;
        } catch (Exception e) {
            logger.error("error: {}", e.getMessage());
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
