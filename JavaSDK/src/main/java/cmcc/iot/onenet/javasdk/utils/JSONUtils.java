package cmcc.iot.onenet.javasdk.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cmcc.iot.onenet.javasdk.exception.OnenetApiException;

public class JSONUtils {

	/*
	 * 将JSONObject转换成Map
	 * @param json 要转换的JSON
	 *
	 * @return 生成的Map
	 *
	 * @throws JSONException 转换错误时抛出此异常
	 */
	public static Map<String, Object> jsonObjectToMap(JSONObject json, String name)  {
		Map<String, Object> result=null;
		try {
			result = new HashMap<String, Object>();
			if (json != JSONObject.NULL) {
				result = jsonObjectToMapImpl(json);
			}
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, name);
		}
		return result;
	}

	public static List<Object> jsonArrayToList(JSONArray json, String name) {
		try {
			List<Object> result = new ArrayList<Object>();
			if (json != JSONObject.NULL) {
				result = jsonArrayToListImpl(json);
			}
			return result;
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, name);
		}
		return null;
	}


	private static Map<String, Object> jsonObjectToMapImpl(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if (value == JSONObject.NULL) {
				value = null;
			}

			if (value instanceof JSONArray) {
				value = jsonArrayToListImpl((JSONArray) value);
			} else if (value instanceof JSONObject) {
				value = jsonObjectToMapImpl((JSONObject) value);
			}

			map.put(key, value);
		}
		return map;
	}

	private static List<Object> jsonArrayToListImpl(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if (value == JSONObject.NULL) {
				value = null;
			}

			if (value instanceof JSONArray) {
				value = jsonArrayToListImpl((JSONArray) value);
			} else if (value instanceof JSONObject) {
				value = jsonObjectToMapImpl((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}

	/*
	 * 将字符串解析为JSONObject
	 * @param data 要转换的字符串
	 * @return 生成的JSONObject，若转换失败则返回null
	 */
	public static JSONObject decode(String data) {
		JSONObject json = null;
		try {
			json = new JSONObject(data);
			
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_JSON);
		}
		return json;
	}

	public static String requestHeaderToJSON(HttpHeaders hd) {
		JSONArray json = new JSONArray();
		MultivaluedMap<String, String> map = hd.getRequestHeaders();
		for (String key : map.keySet()) {
			StringBuilder sb = new StringBuilder();
			for (String i : map.get(key)) {
				sb.append(i);
			}
			sb.insert(0, ':');
			sb.insert(0, key);
			json.put(sb.toString());
		}
		return json.toString();
	}

	public static void has(JSONObject jsonObject, String key) {
		if (!jsonObject.has(key)) {
			//throw new OnenetException(OnenetStatus.PARAMETER_REQUIRED, key);
		}
	}

	/**
	 * 从JSONObject中取出字符串列表
	 *
	 * @param jsonObject JSONObject
	 * @param key        字符串列表的key
	 * @return 取出的字符串列表
	 * @throws OnenetException
	 */
	public static List<String> getStringList(JSONObject jsonObject, String key) {
		has(jsonObject, key);
		List<String> result=null;
		try {
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			result = new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
				result.add((String) jsonArray.get(i));
			}
			
		} catch (Exception e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return result;
	}
	public static List<String> getLongListAsStringList(JSONObject jsonObject, String key)  {
		has(jsonObject, key);
		List<String> result=null;
		try {
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			result= new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
				result.add(String.valueOf(jsonArray.get(i)));
			}
			
		} catch (Exception e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return result;
	}


	/**
	 * 获取JSON的字符串value
	 *
	 * @param jsonObject
	 * @param key
	 * @return
	 * @throws OnenetException
	 */
	public static String getString(JSONObject jsonObject, String key) {
		has(jsonObject, key);
		String result=null;
		try {
			result= jsonObject.getString(key);
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		//额外检查字符串是否为空
		//为兼容前端的空字符串，故注释掉
		/*
		if (!result.isEmpty()) {
			return result;
		} else {
			throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		*/
		return result;
	}
	/**
	 * 获取JSON的字符串value
	 *
	 * @param jsonObject
	 * @param key
	 * @return
	 * @throws OnenetException
	 */
	public static String getNotEmptyString(JSONObject jsonObject, String key) {
		has(jsonObject, key);
		String result=null;
		try {
			result = jsonObject.getString(key);
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		//额外检查字符串是否为空
		if (result.isEmpty()) {
			throw new OnenetApiException();
		} else {
			return result;
		}
	}
	public static String getString(JSONArray jsonArray, int i) {
		String result=null;
		try {
			 result=jsonArray.getString(i);
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER);
		}
		return result;
	}
	/**
	 * 获取JSON的int
	 *
	 * @param jsonObject
	 * @param key
	 * @return
	 * @throws OnenetException
	 */
	public static int getInt(JSONObject jsonObject, String key) {
		has(jsonObject, key);
		Integer result = null;
		try {
			result=jsonObject.getInt(key);
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return result;
	}
	/**
	 * 获取JSON的long
	 *
	 * @param jsonObject
	 * @param key
	 * @return
	 * @throws OnenetException
	 */
	public static long getLong(JSONObject jsonObject, String key)  {
		has(jsonObject, key);
		Long result=null;
		try {
			result=jsonObject.getLong(key);
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return result;
	}

	/**
	 * 获取JSON的boolean
	 *
	 * @param jsonObject
	 * @param key
	 * @return
	 * @throws OnenetException
	 */
	public static boolean getBoolean(JSONObject jsonObject, String key)  {
		has(jsonObject, key);
		Boolean result=null;
		try {
			result= jsonObject.getBoolean(key);
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return result;
	}

	/**
	 * 获取JSONObject的一个key
	 *
	 * @param jsonObject
	 * @param key
	 * @return
	 * @throws OnenetException
	 */
	public static JSONObject getJsonObject(JSONObject jsonObject, String key)  {
		has(jsonObject, key);
		JSONObject result=null;
		try {
			result= jsonObject.getJSONObject(key);
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return result;
	}

	public static JSONObject getJsonObject(JSONArray jsonArray, int i) {
		JSONObject result=null;
		try {
			result= jsonArray.getJSONObject(i);
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER);
		}
		return result;
	}

	public static JSONArray getJSONArray(JSONObject jsonObject, String key)  {
		has(jsonObject, key);
		JSONArray result=null;
		try {
			result= jsonObject.getJSONArray(key);
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return result;
	}

	public static Object getObject(JSONObject jsonObject, String key)  {
		has(jsonObject, key);
		Object result=null;
		try {
			result= jsonObject.get(key);
		} catch (JSONException e) {
		//	throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return result;
	}

	public static double getDouble(JSONObject jsonObject, String key) {
		has(jsonObject, key);
		Double result=null;
		try {
			result= jsonObject.getDouble(key);
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return result;
	}
	public static Number getNumber(JSONObject jsonObject, String key)  {
		has(jsonObject, key);
		Object object=null;
		try {
			object =  jsonObject.get(key);
			if (object instanceof Number) {
			} else {
				//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
			}
		} catch (JSONException e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return (Number)object;
	}
	public static Date getDate(JSONObject jsonObject, String key) {
		has(jsonObject, key);
		Object object=null;
		try {
			object =  jsonObject.get(key);
			if (object instanceof String) {
				
			} else {
				//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
			}
		} catch (Exception e) {
			//throw new OnenetException(OnenetStatus.INVALID_PARAMETER, key);
		}
		return Date.from(LocalDate.parse((String)object).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 将List转换为JSONArray
	 *
	 * @param list
	 * @return
	 */
	public static JSONArray listToJsonArray(List list) {
		JSONArray result = new JSONArray();
		for (Object object : list) {
			if (object instanceof Map) {
				result.put(mapToJsonObject((Map<String, Object>) object));
			} else {
				result.put(object);
			}
		}
		return result;
	}

	public static JSONObject mapToJsonObject(Map<String, Object> map) {
		JSONObject result = new JSONObject();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() instanceof List) {
				result.put(entry.getKey(), listToJsonArray((List) entry.getValue()));
			} else {
				result.put(entry.getKey(), entry.getValue());
			}
		}
		return result;
	}
}
