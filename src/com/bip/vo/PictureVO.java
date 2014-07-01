package com.bip.vo;

public class PictureVO {
	private Integer id;
	private Integer realActivityId;
	private String picMaxPath;
	private String picMinPath;
	private Integer isMain;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRealActivityId() {
		return realActivityId;
	}
	public void setRealActivityId(Integer realActivityId) {
		this.realActivityId = realActivityId;
	}
	public String getPicMaxPath() {
		return picMaxPath;
	}
	public void setPicMaxPath(String picMaxPath) {
		this.picMaxPath = picMaxPath;
	}
	public String getPicMinPath() {
		return picMinPath;
	}
	public void setPicMinPath(String picMinPath) {
		this.picMinPath = picMinPath;
	}
	public Integer getIsMain() {
		return isMain;
	}
	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}
}
