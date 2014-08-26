package com.bip.utils;

import com.bip.resource.ResourceFile;

public class DataUtil {
	
	/** check the url is right 
	 * 	if the url contain the url filter characters ,return false,show the url is forbidden
	 * 	else return true ,show the url is right;
	 * */
	public static boolean isUrlValueSuccessed(Object obj){
		if(obj.toString().length()!=0){
			if(ResourceFile.URL_FILTER_CHARACTERS.contains(obj.toString().trim())){
				return false;
			}
		}
		return true;
	}
}
