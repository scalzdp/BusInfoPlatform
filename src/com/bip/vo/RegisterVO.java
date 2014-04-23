package com.bip.vo;

/* 注册信息的Value Object，此对象可以用于数据传输
 * 1.在Jsp文件和Controller之间的数据传输。
 * 2.在Controller与Service之间的数据传输对象
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
