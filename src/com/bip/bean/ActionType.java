package com.bip.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_actiontype")
public class ActionType implements Serializable {
	
	private int id;
	private String name;
	private int dateMark;
	private Date date;
	private String description;
	private Set<RealActivity> realActivity;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column
	public int getDateMark() {
		return dateMark;
	}
	public void setDateMark(int dateMark) {
		this.dateMark = dateMark;
	}
	
	@Column
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(mappedBy="actionTypeId") 
	public Set<RealActivity> getRealActivity(){
		return realActivity;
	}
	public void setRealActivity(Set<RealActivity> realactivity){
		this.realActivity = realactivity;
	}

}
