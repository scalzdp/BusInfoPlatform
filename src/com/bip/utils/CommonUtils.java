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
}
