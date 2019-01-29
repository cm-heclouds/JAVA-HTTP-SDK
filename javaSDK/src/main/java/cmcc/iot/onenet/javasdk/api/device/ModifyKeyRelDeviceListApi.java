package cmcc.iot.onenet.javasdk.api.device;

import cmcc.iot.onenet.javasdk.api.AbstractAPI;
import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.http.HttpPutMethod;
import cmcc.iot.onenet.javasdk.request.RequestInfo;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.device.ModifyKeyRelDeviceResponse;
import cmcc.iot.onenet.javasdk.utils.Config;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description 4.7.6修改apikey关联设备列表
 * @author luowz
 * @date 2019/1/2 19:52
 * @version 1.0
 *
 **/
public class ModifyKeyRelDeviceListApi extends AbstractAPI{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private HttpPutMethod HttpMethod;

    private String apiKey;
    private List<String> addDevIds;
    private List<String> delDevIds;

    public ModifyKeyRelDeviceListApi(List<String> addDevIds, List<String> delDevIds, String apiKey, String masterKey) {
        this.apiKey = apiKey;
        this.addDevIds = addDevIds;
        this.delDevIds = delDevIds;
        this.key = masterKey;
        this.method = RequestInfo.Method.PUT;
        this.HttpMethod = new HttpPutMethod(method);
        this.url = Config.getString("test.url") + "/keys/"+apiKey+"/devices" ;
        Map<String, Object> headmap = new HashMap<String, Object>();
        Map<String, Object> body = new HashMap<String, Object>();
        if(addDevIds!=null&&!addDevIds.isEmpty()){
            body.put("add_dev_ids",addDevIds);
        }
        if(delDevIds!=null&&!delDevIds.isEmpty()){
            body.put("del_dev_ids",delDevIds);
        }
        String json = null;
        try {
            json = mapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            logger.error("json error {}", e.getMessage());
            throw new OnenetApiException(e.getMessage());
        }
        headmap.put("api-key", masterKey);
        HttpMethod.setHeader(headmap);
        HttpMethod.setcompleteUrl(url,null);
        HttpMethod.setEntity(json);
    }


    public BasicResponse executeApi(){
        BasicResponse response;
        try {
            HttpResponse httpResponse = HttpMethod.execute();
            InputStream instreams = httpResponse.getEntity().getContent();
            String str = IOUtils.toString(instreams, "UTF-8");
            response = mapper.readValue(str, BasicResponse.class);
            response.setJson(str);
            Object newData = mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), ModifyKeyRelDeviceResponse.class);
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
