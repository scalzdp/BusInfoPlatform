package com.bip.vo;

public class ProfileVO {
	private Integer userProfileId;
	private String frequenedLocation;
	private String headImg;
	private Integer userID;
	private String hobby;
	
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
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
}
