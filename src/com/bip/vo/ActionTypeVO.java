package com.bip.vo;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class ActionTypeVO {
	private Integer id;
	private String name;
	private Integer dateMark;
	private Date date;
	private String description;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDateMark() {
		return dateMark;
	}
	public void setDateMark(Integer dateMark) {
		this.dateMark = dateMark;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
