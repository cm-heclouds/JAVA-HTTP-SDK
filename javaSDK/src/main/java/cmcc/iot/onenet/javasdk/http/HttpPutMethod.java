package cmcc.iot.onenet.javasdk.http;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.response.BasicResponse;

public class HttpPutMethod extends BasicHttpMethod {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public HttpPutMethod(Method method) {
		super(method);
		// TODO Auto-generated constructor stub
	}

	public void setEntity(String json) {
		// TODO Auto-generated method stub
		if (json != null) {
			StringEntity entity = new StringEntity(json, Charset.forName("UTF-8"));
			((HttpPut) httpRequestBase).setEntity(entity);// 向下转型
		}
	}

	public void setEntity(Map<String, String> stringMap, Map<String, String> fileMap) {
		// TODO Auto-generated method stub
		this.upload = true;
		Set<Entry<String, String>> stringSet = stringMap.entrySet();
		Set<Entry<String, String>> fileSet = fileMap.entrySet();
		Map<String, StringBody> stringEntityMap = new HashMap<String, StringBody>();
		Map<String, FileBody> fileBodyMap = new HashMap<String, FileBody>();
		for (Entry<String, String> entry : stringSet) {
			try {
				stringEntityMap.put(entry.getKey(), new StringBody(entry.getValue(), Charset.forName("UTF-8")));
			} catch (UnsupportedEncodingException e) {
				logger.error("error:" + e.getMessage());
				throw new OnenetApiException(e.getMessage());
			}
		}
		for (Entry<String, String> entry : fileSet) {
			fileBodyMap.put(entry.getKey(), new FileBody(new File(entry.getValue())));
		}
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		Set<Entry<String, FileBody>> fileBodySet = fileBodyMap.entrySet();
		Set<Entry<String, StringBody>> stringBodySet = stringEntityMap.entrySet();
		for (Entry<String, FileBody> entry : fileBodySet) {
			builder.addPart(entry.getKey(), entry.getValue());
		}
		for (Entry<String, StringBody> entry : stringBodySet) {
			builder.addPart(entry.getKey(), entry.getValue());
		}
		HttpEntity reqEntity = builder.build();
		((HttpPut) httpRequestBase).setEntity(reqEntity);
	}

	public void sethttpMethod(Method method) {
		// TODO Auto-generated method stub

	}

	public HttpResponse execute() throws Exception {
		HttpResponse httpResponse = null;
		httpClient = HttpClients.createDefault();
		httpResponse = httpClient.execute(httpRequestBase);
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK && statusCode != 221) {
			String response = EntityUtils.toString(httpResponse.getEntity());
			logger.error("request failed  status:{}, response::{}",statusCode, response);
			throw new OnenetApiException("request failed: " + response);
		}
		return httpResponse;

	}
}
