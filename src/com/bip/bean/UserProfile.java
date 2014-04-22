package com.bip.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserProfile implements Serializable {
	private Integer id;
	private Integer userID;
	private String headImg;
	private String frequenedLocation;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	@Column
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	@Column
	public String getFrequenedLocation() {
		return frequenedLocation;
	}
	public void setFrequenedLocation(String frequenedLocation) {
		this.frequenedLocation = frequenedLocation;
	}
}
