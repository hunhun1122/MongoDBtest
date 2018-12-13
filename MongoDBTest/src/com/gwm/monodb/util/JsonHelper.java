package com.gwm.monodb.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class JsonHelper {

	private static Log log = LogFactory.getLog(JsonHelper.class);

	/**
	 * 将不含日期时间格式的Java对象系列化为Json资料格式
	 * 
	 * @param pObject
	 *            传入的Java对象
	 * @return
	 */
	public static final String encodeObject2Json(Object pObject) {
		String jsonString = "[]";
		if (G4Utils.isEmpty(pObject)) {
			// log.warn("传入的Java对象为空,不能将其序列化为Json资料格式.请检查!");
		} else {
			if (pObject instanceof ArrayList) {
				JSONArray jsonArray = JSONArray.fromObject(pObject);
				jsonString = jsonArray.toString();
			} else {
				JSONObject jsonObject = JSONObject.fromObject(pObject);
				jsonString = jsonObject.toString();
			}
		}
		
		return jsonString;
	}

	/**
	 * 将含有日期时间格式的Java对象系列化为Json资料格式<br>
	 * Json-Lib在处理日期时间格式是需要实现其JsonValueProcessor接口,所以在这里提供一个重载的方法对含有<br>
	 * 日期时间格式的Java对象进行序列化
	 * 
	 * @param pObject
	 *            传入的Java对象
	 * @return
	 */
	public static final String encodeObject2Json(Object pObject, String pFormatString) {
		String jsonString = "[]";
		if (G4Utils.isEmpty(pObject)) {
			// log.warn("传入的Java对象为空,不能将其序列化为Json资料格式.请检查!");
		} else {
			JsonConfig cfg = new JsonConfig();
			cfg.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonValueProcessorImpl(pFormatString));
			cfg.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessorImpl(pFormatString));
			cfg.registerJsonValueProcessor(java.sql.Date.class, new JsonValueProcessorImpl(pFormatString));
			if (pObject instanceof ArrayList) {
				JSONArray jsonArray = JSONArray.fromObject(pObject, cfg);
				jsonString = jsonArray.toString();
			} else {
				JSONObject jsonObject = JSONObject.fromObject(pObject, cfg);
				jsonString = jsonObject.toString();
			}
		}
		if (log.isInfoEnabled()) {
			log.info("序列化后的JSON资料输出:\n" + jsonString);
		}
		return jsonString;
	}




	public static final JSONObject JSON2Object(String jsonStr)
	{
	    JSONObject obj = new JSONObject().fromObject(jsonStr);
	    return obj;
	}

	
	public static final String encodeList2Json(List list, String dataFormat) {
		String subJsonString = "";
		if (G4Utils.isEmpty(dataFormat)) {
			subJsonString = encodeObject2Json(list);
		} else {
			subJsonString = encodeObject2Json(list, dataFormat);
		}
		String jsonString = "{ ROOT:" + subJsonString + "}";
		if (log.isInfoEnabled()) {
			log.info("序列化后的JSON资料输出:\n" + jsonString);
		}
		return jsonString;
	}
	
	
	


	
	
	/**
	 * 将Json字符串转换成匹配的对象
	 * @param jsonString 
	 * @param clazz 指定要转换成的目标对象class
	 * @return 目标类
	 */
	public static Object parseJson2Object(String jsonString,Class clazz)
	{
		JSONObject jb = JSONObject.fromObject(jsonString);
		return JSONObject.toBean(jb,clazz);
		
	}
}
