package com.bip.utils;

import java.util.ArrayList;
import java.util.List;

import com.bip.bean.CacheKey;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonHandler {
	/**
	 *将一个JavaList对象序列化为Json对象
	 *@param o 传递的JavaList对象 
	 */
	public static <T> String convertObjectToJson(List<T> objects){
		try{
			if(objects==null){
				return "";
			}
			JSONArray jo = JSONArray.fromObject(objects);
			System.err.println(jo.toString());
			return jo.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	/** convert object to jsonString  
	 * */
	public static <T> String convertObjectToJson(T object){
		try{
			if(object==null){
				return "";
			}
			JSONArray jo = JSONArray.fromObject(object);
			System.err.println(jo.toString());
			return jo.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static List<CacheKey> convertJsonToCacheKeyObjects(String jsonString){
		List<CacheKey> cacheKeys = new ArrayList<CacheKey>();
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		JSONObject jsonObj = JSONObject.fromObject(jsonArray.get(0));
		
		return cacheKeys;
	}
	
}
