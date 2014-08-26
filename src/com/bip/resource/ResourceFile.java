package com.bip.resource;

public class ResourceFile {
	/*session attribuilt key ,use this key  you can get user message form session
	 * */
	public static String USER_SESSION_KEY="user_session_key";
	public static String PUBLISH_MESSAGE = "PublishMessage";
	public static String USERVO_SESSION_KEY="uservo_session_key";
	
	/** 	Tomcat虚拟路径工程使用的配置
	 * 		存放这里的路径配置和Tomcat的虚拟路径配置相同
	 * */
	public static String UPLOAD_PICTURE_PATH="G:\\javabackup\\Img";
	
	/** validation the path is the right windows system path		
	 * */
	public static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	
	/** the base path
	 * */
	public static String BASE_PATH = "http://localhost:8080/BusInfoPlatForm";
	
	/** set the url filter characters,
	 *  if the request url has this character it a forbidden url
	 * */
	public static String URL_FILTER_CHARACTERS=("\",',<,>,%,&,(,),;,+,-,[,],{,},%,#,"+
		"select,insert,delete,drop,update,truncate,from,exec").trim();
	
}
