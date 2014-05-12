package com.bip.vo;

import java.util.Date;

/* middle of publish message value transport object 
 * in this object has input location place and message description and user message
 * */
public class PublishMessageVO {
	private String location;
	private String telephone;
	private String description;
	private Date dateTime;
	
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
}
