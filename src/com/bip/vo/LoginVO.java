package com.bip.vo;

/*
 * �����¼��Value object,���ڴ��ݵ�¼ʱ��д�ĵ�¼��Ϣ
 * */
public class LoginVO {
	private Integer id;
	private String userEmail;
	private String userPassword;
	private String captcha;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
}
