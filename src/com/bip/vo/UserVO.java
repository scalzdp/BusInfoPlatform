package com.bip.vo;

/*
 * login this site value Object ,transform input message
 * */
public class UserVO implements java.io.Serializable {
	private Integer id;
	private String userEmail;
	private String userPassword;
	private String userNickName;
	private String captcha;
	private String emailvfcode; 
	private String isActive;
	
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
	public String getEmailvfcode() {
		return emailvfcode;
	}
	public void setEmailvfcode(String emailvfcode) {
		this.emailvfcode = emailvfcode;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
