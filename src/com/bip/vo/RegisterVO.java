package com.bip.vo;

/* ע����Ϣ��Value Object���˶�������������ݴ���
 * 1.��Jsp�ļ���Controller֮������ݴ��䡣
 * 2.��Controller��Service֮������ݴ������
 * */
public class RegisterVO {
	private String userEmail;
	private String userPassword;
	private String userNickName;
	private String captcha;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
