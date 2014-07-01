package com.bip.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_picture")
public class Picture {
	
	private Integer id;
	private Integer realActivityId;
	private String picMaxPath;
	private String picMinPath;
	private Integer isMain;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column
	public Integer getRealActivityId() {
		return realActivityId;
	}
	public void setRealActivityId(Integer realActivityId) {
		this.realActivityId = realActivityId;
	}
	
	@Column
	public String getPicMaxPath() {
		return picMaxPath;
	}
	public void setPicMaxPath(String picMaxPath) {
		this.picMaxPath = picMaxPath;
	}
	
	@Column
	public String getPicMinPath() {
		return picMinPath;
	}
	public void setPicMinPath(String picMinPath) {
		this.picMinPath = picMinPath;
	}
	
	@Column
	public Integer getIsMain() {
		return isMain;
	}
	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}
}
