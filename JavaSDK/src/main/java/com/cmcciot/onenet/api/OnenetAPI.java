package com.cmcciot.onenet.api;

import com.cmcciot.onenet.api.response.BasicResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OnenetAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ObjectMapper mapper = new ObjectMapper();

    private String masterKey;

    private boolean https = false;

    private String httpAddr = "http://api.heclouds.com";
    private String httpsAddr = "https://api.heclouds.com";

    public void setMasterKey(String key) {
        this.masterKey = key;
    }

    public Object exectute(RequestInfo requestInfo, Class clazz) throws Exception {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpResponse httpResponse = null;
        switch (requestInfo.getVerb()) {
            case POST:
                httpResponse = doPost(client, requestInfo);
                break;
            case GET:
                httpResponse = doGet(client, requestInfo);
                break;
            case DELETE:
                httpResponse = doDelete(client, requestInfo);
                break;
            default:
                logger.error("http verb not supported");
                throw new OnenetApiException();
        }

        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK
            && statusCode != HttpStatus.SC_COMMAND_SENT) {
            logger.error("request failed: {}", statusCode);
            throw new OnenetApiException();
        }

        BasicResponse response = mapper.readValue(httpResponse.getEntity().getContent(), BasicResponse.class);
        if (!clazz.equals(Void.class)) {
            Object newData =  mapper.readValue(mapper.writeValueAsString(response.getDataInternal()), clazz);
            response.setData(newData);
        }
        return response;
    }

    private HttpResponse doPost(HttpClient client, RequestInfo requestInfo) throws Exception {
        HttpPost post = new HttpPost((https ? httpsAddr : httpAddr) + requestInfo.getPath());
        String key = requestInfo.getKey() != null ? requestInfo.getKey() : masterKey;
        post.setHeader(new BasicHeader("api-key", key));
        post.setEntity(new StringEntity(mapper.writeValueAsString(requestInfo.getEntity())));

        return client.execute(post);
    }

    private HttpResponse doGet(HttpClient client, RequestInfo requestInfo) throws Exception {
        HttpGet get = new HttpGet((https ? httpsAddr : httpAddr) + requestInfo.getPath());
        String key = requestInfo.getKey() != null ? requestInfo.getKey() : masterKey;
        get.setHeader(new BasicHeader("api-key", key));

        return client.execute(get);
    }

    private HttpResponse doDelete(HttpClient client, RequestInfo requestInfo) throws Exception {
        HttpDelete get = new HttpDelete((https ? httpsAddr : httpAddr) + requestInfo.getPath());
        String key = requestInfo.getKey() != null ? requestInfo.getKey() : masterKey;
        get.setHeader(new BasicHeader("api-key", key));

        return client.execute(get);
    }

}
