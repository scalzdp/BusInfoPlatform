package com.bip.vo;

import java.util.List;

public class UserAndProfileVO {
	private Integer userId;
	private String userAge;
	private String userBrithday;
	private String userEmail;
	private String userNickName;
	private String userPassword;
	
	private Integer userProfileId;
	private String frequenedLocation;
	private String headImg;
	private String hobby;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserAge() {
		return userAge;
	}
	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}
	public String getUserBrithday() {
		return userBrithday;
	}
	public void setUserBrithday(String userBrithday) {
		this.userBrithday = userBrithday;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	public Integer getUserProfileId() {
		return userProfileId;
	}
	public void setUserProfileId(Integer userProfileId) {
		this.userProfileId = userProfileId;
	}
	public String getFrequenedLocation() {
		return frequenedLocation;
	}
	public void setFrequenedLocation(String frequenedLocation) {
		this.frequenedLocation = frequenedLocation;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	
}
