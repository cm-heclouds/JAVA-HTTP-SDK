package cmcc.iot.onenet.javasdk.api.device;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpGetMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.device.KeyRelDeviceList;
import cmcc.iot.onenet.javasdk.utils.Config;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description api-key关联/未关联的设备分页列表查询
 * @author luowz
 * @date 2019/1/2 19:12
 * @version 1.0
 *
 **/
public class FindKeyRelDeviceListApi extends AbstractAPI{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Integer page;
    private Integer perPage;
    private String deviceId;
    private String deviceTitle;
    private Boolean isRlated;
    private String apiKey;
    private HttpGetMethod HttpMethod;

    public FindKeyRelDeviceListApi(Integer page, Integer perPage, String deviceId,String deviceTitle, Boolean isRlated, String apiKey, String masterKey) {
        this.page = page;
        this.perPage = perPage;
        this.deviceId = deviceId;
        this.deviceTitle = deviceTitle;
        this.isRlated = isRlated;
        this.apiKey = apiKey;
        this.key = masterKey;
        this.method = RequestInfo.Method.GET;
        this.HttpMethod=new HttpGetMethod(method);
        this.url = Config.getString("test.url") + "/keys/"+apiKey+"/devices" ;
        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> urlmap = new HashMap<String, Object>();
        if(page!=null){
            urlmap.put("page", page);
        }
        if(perPage!=null){
            urlmap.put("per_page", perPage);
        }
        if(StringUtils.isNotBlank(deviceId)){
            urlmap.put("device_id", deviceId);
        }
        if(StringUtils.isNotBlank(deviceTitle)){
            urlmap.put("device_title", deviceTitle);
        }
        if(isRlated!=null){
            urlmap.put("is_related", isRlated);
        }
        headmap.put("api-key", masterKey);
        HttpMethod.setHeader(headmap);
        HttpMethod.setcompleteUrl(url,urlmap);
    }


    public BasicResponse<KeyRelDeviceList> executeApi() {
        BasicResponse response;
//        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            HttpResponse httpResponse=HttpMethod.execute();
            response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(mapper.writeValueAsString(response));
            Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), KeyRelDeviceList.class);
            response.setData(newData);
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
