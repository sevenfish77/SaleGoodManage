package com.yzgs.domain;
import java.util.Date;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtil {

	 
	public static String ObjectToJson(Object object) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor("yyyy-MM-dd HH:mm:ss"));
		JSONObject json = JSONObject.fromObject(object, jsonConfig);
		return json.toString();
	}
	
	
}
