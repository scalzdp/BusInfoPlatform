package com.bip.util;

import javax.servlet.http.HttpSession;

import com.bip.resource.ResourceFile;

/* SessionManager ���ڹ����û���Session����
 * 1.�洢Session
 * 2.���Session
 * 3.��ʱˢ��Session
 * */
public class SessionManager {
	
	/* �������û���¼��ϢSession�����á�
	 * 1.����Session����Ȼ�����ָ����Keyֵ���������
	 * */
	public static boolean clearUserSession(HttpSession session){
		try{
			session.removeAttribute(ResourceFile.USER_SESSION_KEY);
		}catch(Exception e){
		}
		return true;
	}
}
