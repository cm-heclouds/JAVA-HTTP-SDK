package cmcc.iot.onenet.javasdk.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cmcc.iot.onenet.javasdk.exception.OnenetApiException;
import cmcc.iot.onenet.javasdk.request.RequestInfo;

public class BasicHttpMethod implements RequestInfo{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public CloseableHttpClient httpClient;
	public HttpRequestBase httpRequestBase;
	public String url;
	public Method method;
	boolean upload;
	public ObjectMapper mapper = new ObjectMapper();
	public BasicHttpMethod(Method method) {
		this.method = method;
		   switch (method) {
		     case POST:
		    	 httpRequestBase= new HttpPost();
		    	 break;
		     case PUT:
		    	 httpRequestBase= new HttpPut();
		    	 break;
		     case DELETE:
		    	 httpRequestBase= new HttpDelete();
		    	 break;
		     default: 
		    	 httpRequestBase= new HttpGet();
		    	 break;
		     }
	}
	public  HttpRequestBase setHttpRequestBase() {
		return null;
	}
	public void setHttpRequestBase(HttpRequestBase httpRequestBase) {
		this.httpRequestBase = httpRequestBase;
	}

	public void setcompleteUrl(String url,Map<String, Object> params) {
		// TODO Auto-generated method stub
		if(params!=null)
		{
			url+="?";
			Set<Entry<String,Object>> entrys=params.entrySet();
			int size=entrys.size();
			int index=0;
			for(Entry<String,Object> entry:entrys)
			{
				url+=entry.getKey()+"="+entry.getValue();
				if(++index<size)
					url+="&";
			}
		}
		try {
			httpRequestBase.setURI(new URI(url));
		} catch (URISyntaxException e) {
			logger.error("url error: {}",e.getMessage());
			throw new OnenetApiException(e.getMessage());
		}
	}
	public void setType(boolean upload) {
		// TODO Auto-generated method stub
		if(!upload)
		{
			httpRequestBase.addHeader("Content-type","application/json; charset=utf-8");  
			httpRequestBase.addHeader("Accept", "application/json"); 
		}
	}

	public void setEntity(Object json) {
		// TODO Auto-generated method stub

	}

	public void setEntity(Map<String, String> stringMap, Map<String, String> fileMap) {
		// TODO Auto-generated method stub

	}

	public void sethttpMethod(Method method) {
		// TODO Auto-generated method stub

	}

	public void setHeader(Map<String, Object> params) {
		if (params != null) {
			Set<Entry<String, Object>> entrys = params.entrySet();
			for (Entry<String, Object> entry : entrys)
				httpRequestBase.setHeader(entry.getKey(),(String) entry.getValue());
		}

	}
}
