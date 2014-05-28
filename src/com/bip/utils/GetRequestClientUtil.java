package com.bip.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class GetRequestClientUtil {
	
	public static final String KEY_1 = "x2MhgDZIHVXhQwWLGhc98Qar";
	
	/**
	 * get the ip location from the user client request
     *
     * @param request
     * @return
     */
	public static String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("X-Real-IP");
		if(!StringUtils.isBlank(ip)&&!"unknown".equalsIgnoreCase(ip)){
			return ip;
		}
		ip=request.getHeader("X-Forwarded-For");
		if(!StringUtils.isBlank(ip)&&!"unknow".equalsIgnoreCase(ip)){
			int index=ip.indexOf(',');
			if(index!=-1){
				return ip.substring(0,index);
			}else{
				return ip;
			}
		}else{
			return request.getRemoteAddr();
		}
	}
	
	 /*@param content name=xx &pwd=xx
    *
    * @param encodingString 
    *
    * @return
    *
    * */
    public static Map<String,String> getAddress(String content,String encodingString) throws UnsupportedEncodingException{
           //get ip location from the http://ip.taobao.com/service/getIpInfo.php
    	Map<String,String> map = new HashMap<String,String>();
          String urlStr= "http://ip.taobao.com/service/getIpInfo.php" ;
           //search ip info from  http://whois.pconline.com.cn
          String returnStr = getResult(urlStr,content,encodingString);
           if(returnStr != null){
                 System. out.println(returnStr);
                 String[] temp = returnStr.split( ",");
                  if(temp.length <3){
                        return map ; 
                 }
                 String region = (temp[5].split( ":"))[1].replaceAll("\"" , "" );
                 region = decodeUnicode(region);
                 
              String country = ""; String area = "" ;  String
              city = ""; String county = "" ; String isp = "";
              for(int i=0;i<temp.length;i++){
                     switch(i){ case 1:country =
                     (temp[i].split( ":"))[2].replaceAll("\"" , "" );
                     country = decodeUnicode(country);//get the country break;
                     case 3:area =
                     (temp[i].split( ":"))[1].replaceAll("\"" , "" ); area =
                     decodeUnicode(area);//get the area break;
                     case 5:region =
                     (temp[i].split( ":"))[1].replaceAll("\"" , "" ); region =
                     decodeUnicode(region);//get the region break;
                     case 7:city =
                     (temp[i].split( ":"))[1].replaceAll("\"" , "" ); city =
                     decodeUnicode(city);//get the city break;
                     case 9:county =
                     (temp[i].split( ":"))[1].replaceAll("\"" , "" ); county =
                     decodeUnicode(county);//get county break;
                     case 11:isp =
                      (temp[i].split( ":"))[1].replaceAll("\"" , "" ); isp =
                      decodeUnicode(isp);//ISP
                      break;
                      }
              }
             // System.out.println(country+"="+area+"="+region+"="+city+"="+county+"="+isp);
              
              map.put("area", area);
              map.put("region", region);
              map.put("city", city);
              map.put("county", county);
              map.put("isp", isp);
             return map;
          }
           return map ;
   }
   
    /*
    * @param urlStr connection String 
    *
    * @param content input the name=xx &pwd=xx;
    *
    * @param encoding encoding type isGBK or UTF-8
    *
    * @return
    * */
    private static String getResult(String urlStr,String content,String encoding){
          URL url = null;
          HttpURLConnection connection = null;
           try{
                 url = new URL(urlStr);
                 connection = (HttpURLConnection)url.openConnection(); //open the connection
                 connection.setConnectTimeout(2000); //set the max connection time
                 connection.setReadTimeout(2000); //set the max read time 
                 connection.setDoOutput( true);//set the can output
                 connection.setDoInput( true);//set the can input 
                 connection.setRequestMethod( "POST");//set the request type is post
                 connection.setUseCaches( false);//not use cache
                 connection.connect(); //connection the url
                 DataOutputStream out = new DataOutputStream(connection.getOutputStream());//read the result 
                 out.writeBytes(content); //name=xx &pwd=xx
                 out.flush(); //clear the open Stream
                 out.close(); //close the Stream
                 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),encoding));//get the result from connection then encoding the result
                 StringBuffer buffer = new StringBuffer();
                 String line = "";
                  while((line=reader.readLine())!=null){
                       buffer.append(line);
                 }
                 reader.close();
                  return buffer.toString();
                 
          } catch(IOException ex){
                 ex.printStackTrace();
          } finally{
                  if(connection!=null){
                       connection.disconnect(); //give up the connection ,and recover the resources
                 }
          }
           return null ;
   }
   
    /*unicode 杞崲鎴�涓枃
    *
    * @param theString 闇�杞崲鎴愪腑鏂囩殑瀛楃涓�
    *
    * @return
    * */
    private static String decodeUnicode(String theString){
           char aChar;
            int len = theString.length();
            StringBuffer outBuffer = new StringBuffer(len);
            for (int x = 0; x < len;) {
             aChar = theString.charAt(x++);
             if (aChar == '\\' ) {
              aChar = theString.charAt(x++);
              if (aChar == 'u' ) {
               int value = 0;
               for (int i = 0; i < 4; i++) {
                aChar = theString.charAt(x++);
                switch (aChar) {
                case '0' :
                case '1' :
                case '2' :
                case '3' :
                case '4' :
                case '5' :
                case '6' :
                case '7' :
                case '8' :
                case '9' :
                 value = (value << 4) + aChar - '0';
                 break;
                case 'a' :
                case 'b' :
                case 'c' :
                case 'd' :
                case 'e' :
                case 'f' :
                 value = (value << 4) + 10 + aChar - 'a';
                 break;
                case 'A' :
                case 'B' :
                case 'C' :
                case 'D' :
                case 'E' :
                case 'F' :
                 value = (value << 4) + 10 + aChar - 'A';
                 break;
                default:
                 throw new IllegalArgumentException(
                   "Malformed      encoding." );
                }
               }
               outBuffer.append(( char) value);
              } else {
               if (aChar == 't' ) {
                aChar = '\t';
               } else if (aChar == 'r') {
                aChar = '\r';
               } else if (aChar == 'n') {
                aChar = '\n';
               } else if (aChar == 'f') {
                aChar = '\f';
               }
               outBuffer.append(aChar);
              }
             } else {
              outBuffer.append(aChar);
             }
            }
            return outBuffer.toString();
   }
   
   
    /*
    * 鏍规嵁鍩庡競鍚嶇О鏌ヨ鍩庡競鐨勭粡绾害
    * @param cityName 鍩庡競鍚嶇О
    * */
    public static String getCoordinatesByCity(String cityName){
          URL url = null;
          HttpURLConnection connection = null;
           try{
                 StringBuffer linkStr = new StringBuffer();
                 linkStr.append( "https://maps.google.com.tw/maps/api/geocode/json?address=" );
                 linkStr.append(URLEncoder. encode(cityName,"UTF-8"));
                 linkStr.append( "&sensor=false");
                 url = new URL(linkStr.toString());
                 connection = (HttpURLConnection)url.openConnection();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                 StringBuffer sb = new StringBuffer("" );
                 String result = reader.readLine();
                  while(result!=null ){
                       sb.append(result);
                       result = reader.readLine();
                 }
                  return sb.toString();
          } catch(Exception ex){
                 ex.printStackTrace();
          } finally{
                  if(connection!=null){
                       connection.disconnect();
                 }
          }
           return "" ;
   }
    
	/**
	 * use address place to get location lng and lat
	 * 获得测试所在的经度和维度
	 * key lng(经度),lat(维度)
	 */
	public static Map<String,String> getGeocoderLatitude(String address){
		BufferedReader in = null;
		try {
			//设置编码格式
			address = URLEncoder.encode(address, "UTF-8");
//      瀹炰緥鍖栦竴涓猆RL杩炴帴
//		System.setProperty("http.proxyHost","192.168.1.188");
//		System.setProperty("http.proxyPort","3128");
			URL tirc = new URL("http://api.map.baidu.com/geocoder?address="+ address +"&output=json&key="+ KEY_1);
			
			in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while((res = in.readLine())!=null){
				sb.append(res.trim());
			}
			String str = sb.toString();
			Map<String,String> map = null;
			if(StringUtils.isNotEmpty(str)){
				int lngStart = str.indexOf("lng\":");
				int lngEnd = str.indexOf(",\"lat");
				int latEnd = str.indexOf("},\"precise");
				if(lngStart > 0 && lngEnd > 0 && latEnd > 0){
					String lng = str.substring(lngStart+5, lngEnd);
					String lat = str.substring(lngEnd+7, latEnd);
					map = new HashMap<String,String>();
					map.put("lng", lng);
					map.put("lat", lat);
					return map;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
