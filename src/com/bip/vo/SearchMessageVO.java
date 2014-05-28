package com.bip.vo;

import java.util.Date;

/* search publish message value object ,this object transform to 
 * background then search the result late return the result to this page
 * */
public class SearchMessageVO {
	private String province;
	private String city;
	private String county;
	private Integer actiontypeid;
	private Date beginDateTime;
	private Date endDateTime;
	private Double longitudeRange;
	private Double latitudeRange;
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public Integer getActiontypeid() {
		return actiontypeid;
	}
	public void setActiontypeid(Integer actiontypeid) {
		this.actiontypeid = actiontypeid;
	}
	public Date getBeginDateTime() {
		return beginDateTime;
	}
	public void setBeginDateTime(Date beginDateTime) {
		this.beginDateTime = beginDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public Double getLongitudeRange() {
		return longitudeRange;
	}
	public void setLongitudeRange(Double longitudeRange) {
		this.longitudeRange = longitudeRange;
	}
	public Double getLatitudeRange() {
		return latitudeRange;
	}
	public void setLatitudeRange(Double latitudeRange) {
		this.latitudeRange = latitudeRange;
	}
	
	
	
}
