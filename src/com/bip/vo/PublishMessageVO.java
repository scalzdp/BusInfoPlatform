package com.bip.vo;

import java.util.Date;

/* middle of publish message value transport object 
 * in this object has input location place and message description and user message
 * */
public class PublishMessageVO {
	private Integer realactivityID;
	private String location;
	private String telephone;
	private String description;
	private Date dateTime;
	private Integer userID;
	private Double latitude;
	private Double longitude;
	private Integer actiontypeid;
	private String actiontypename;
	
	public Integer getRealactivityID() {
		return realactivityID;
	}
	public void setRealactivityID(Integer realactivityID) {
		this.realactivityID = realactivityID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Integer getActiontypeid() {
		return actiontypeid;
	}
	public void setActiontypeid(Integer actiontypeid) {
		this.actiontypeid = actiontypeid;
	}
	public String getActiontypename() {
		return actiontypename;
	}
	public void setActiontypename(String actiontypename) {
		this.actiontypename = actiontypename;
	}
}
