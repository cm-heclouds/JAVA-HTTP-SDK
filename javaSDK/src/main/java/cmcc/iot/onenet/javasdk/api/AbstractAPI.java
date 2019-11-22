package cmcc.iot.onenet.javasdk.api;


import cmcc.iot.onenet.javasdk.request.RequestInfo.Method;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public abstract class AbstractAPI<T> {
    public String key;
    public String url;
    public Method method;
    public ObjectMapper mapper = initObjectMapper();

    private ObjectMapper initObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //关闭字段不识别报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //调整默认时区为北京时间
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        dateFormat.setTimeZone(timeZone);
        objectMapper.setDateFormat(dateFormat);
        objectMapper.setTimeZone(timeZone);
        return objectMapper;
    }
}
