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
	private Integer id;
	private Integer locationId;
	private Integer userId;
	private String name;
	private Integer dataMark;
	private Date dateTime;
	private String discription;
	private String telephone;
	private Integer actiontypeid;
	
	private String originalPicture;
	private String shrinkPictures;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	
	@Column
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column
	public Integer getDataMark() {
		return dataMark;
	}
	public void setDataMark(Integer dataMark) {
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
	
	@Column
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Column
	public Integer getActiontypeid() {
		return actiontypeid;
	}
	public void setActiontypeid(Integer actiontypeid) {
		this.actiontypeid = actiontypeid;
	}
	
	@Column
	public String getOriginalPicture() {
		return originalPicture;
	}
	public void setOriginalPicture(String originalPicture) {
		this.originalPicture = originalPicture;
	}
	
	@Column
	public String getShrinkPictures() {
		return shrinkPictures;
	}
	public void setShrinkPictures(String shrinkPictures) {
		this.shrinkPictures = shrinkPictures;
	}
}
