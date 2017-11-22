package cmcc.iot.onenet.javasdk.http;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cmcc.iot.onenet.javasdk.exception.OnenetApiException;

public class HttpPostMethod extends BasicHttpMethod {

	public HttpPostMethod(Method method) {
		super(method);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void setEntity(Object json) {
		if (json != null) {
			if (json instanceof String) {
				StringEntity entity = new StringEntity(json.toString(), Charset.forName("UTF-8"));
				((HttpPost) httpRequestBase).setEntity(entity);// 向下转型
			}
			if (json instanceof byte[]) {
				((HttpPost) httpRequestBase).setEntity(new ByteArrayEntity((byte[]) json));
			}
		}

	}

	public void setEntity(Map<String, String> stringMap, Map<String, String> fileMap) {
		this.upload = true;
		Set<Entry<String, StringBody>> stringBodySet = null;
		if (stringMap != null) {
			Set<Entry<String, String>> stringSet = stringMap.entrySet();
			Map<String, StringBody> stringEntityMap = new HashMap<String, StringBody>();
			for (Entry<String, String> entry : stringSet) {
				try {
					stringEntityMap.put(entry.getKey(), new StringBody(entry.getValue(), Charset.forName("UTF-8")));
				} catch (UnsupportedEncodingException e) {
					logger.error("error:" + e.getMessage());
					throw new OnenetApiException(e.getMessage());
				}
			}
			stringBodySet = stringEntityMap.entrySet();
		}
		Set<Entry<String, FileBody>> fileBodySet = null;
		if (fileMap != null) {
			Set<Entry<String, String>> fileSet = fileMap.entrySet();

			Map<String, FileBody> fileBodyMap = new HashMap<String, FileBody>();

			for (Entry<String, String> entry : fileSet) {
				fileBodyMap.put(entry.getKey(), new FileBody(new File(entry.getValue())));
			}
			fileBodySet = fileBodyMap.entrySet();
		}

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();

		if (fileBodySet != null) {
			for (Entry<String, FileBody> entry : fileBodySet) {
				builder.addPart(entry.getKey(), entry.getValue());
			}
		}
		if (stringBodySet != null) {
			for (Entry<String, StringBody> entry : stringBodySet) {
				builder.addPart(entry.getKey(), entry.getValue());
			}
		}

		HttpEntity reqEntity = builder.build();
		((HttpPost) httpRequestBase).setEntity(reqEntity);
	}

	public HttpResponse execute() throws Exception {
		HttpResponse httpResponse = null;
		httpClient = HttpClients.createDefault();
		httpResponse = httpClient.execute(httpRequestBase);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK && statusCode != 221) {
				String response =EntityUtils.toString(httpResponse.getEntity());
				logger.error("request failed  status:{}, response::{}",statusCode, response);
				throw new OnenetApiException("request failed: "+response);
			}
		
		return httpResponse;

	}

}
