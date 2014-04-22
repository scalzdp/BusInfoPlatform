package com.bip.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_realactivity")
public class RealActivity implements Serializable {
	private int id;
	private int actionTypeId;
	private int locationId;
	private String name;
	private int dataMark;
	private Date dateTime;
	private String discription;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column
	public int getActionTypeId() {
		return actionTypeId;
	}
	public void setActionTypeId(int actionTypeId) {
		this.actionTypeId = actionTypeId;
	}
	
	@Column
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column
	public int getDataMark() {
		return dataMark;
	}
	public void setDataMark(int dataMark) {
		this.dataMark = dataMark;
	}
	
	@Column
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
	@Column
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
}
