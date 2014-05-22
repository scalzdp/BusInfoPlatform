package com.bip.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bip.bean.Location;

public class CommonUtils {
	/**
	*org.apache.commons.collections.map.MultiKeyMap
	 * @param jsonStr input json string 
	 * @return
	 */
	public static Map getMapFromJson(String jsonStr){
		//setDataFormat2JAVA();
		Map map = new HashMap();
		/*for(Iterator iter = jsonObject.keys();iter.hasNext()){
			String key = (String)iter.next() ;
			map.put(key, jsonObject.get(key));
		}*/
		return map;
	}
	
	/** 
	* change jsonString to ArrayList
	* [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...] 
	* @param object  
	* @return 
	*/ 
	public static ArrayList getDTOArray(String jsonString){
		ArrayList list = new ArrayList();
		JSONObject jsonObj = JSONObject.fromObject(jsonString);
		list.add(jsonObj.get(""));
		return list; 
	}
	
	/**
	 *change list to JsonString
	 *@param list<T> obj,input list object then change to jsonlist 
	 */
	public static <T> String convertObjectToJson(List<T> obj){
		try{
			JSONArray jo = JSONArray.fromObject(obj);
			System.err.println(jo.toString());
			return jo.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static <T> T convertJsonToObject(T t,String jsonStr){
		JSONArray array = JSONArray.fromObject(jsonStr);//先读取串数组
		Object[] o = array.toArray();                //转成对像数组
		if(o.length==0){
			return null;
		}
		JSONObject obj = JSONObject.fromObject(o[0]);//再使用JsonObject遍历一个个的对像
		T oo = (T)obj.toBean(obj,t.getClass());//指定转换的类型，但仍需要强制转化-成功
		return oo;
	}
}
