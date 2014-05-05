package com.bip.util;

import javax.servlet.http.HttpSession;

import com.bip.resource.ResourceFile;

/* SessionManager 用于管理用户的Session内容
 * 1.存储Session
 * 2.清空Session
 * 3.定时刷新Session
 * */
public class SessionManager {
	
	/* 完成清空用户登录信息Session的作用。
	 * 1.传入Session对象，然后根据指定的Key值进行清除。
	 * */
	public static boolean clearUserSession(HttpSession session){
		try{
			session.removeAttribute(ResourceFile.USER_SESSION_KEY);
		}catch(Exception e){
		}
		return true;
	}
}
