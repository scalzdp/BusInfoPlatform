package com.bip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.bean.Location;
import com.bip.bean.RealActivity;
import com.bip.dao.IBaseDAO;
import com.bip.vo.PublishMessageVO;

@Service
public class PublishMessageService {
	
	@Autowired
	private IBaseDAO baseDAO;
	
	/* save published message
	 * 1.save the incoming PublishMessageVO
	 * 2.then get the one all the published Message
	 * return the one all the published message
	 * addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
	 * */
	public void saveMessage(PublishMessageVO vo){
		Location location = new Location();
		String[] locations = vo.getLocation().split(",");
		if(locations.length<4){
			do{
				locations[locations.length]="";
			}while(locations.length==4);
		}
		location.setProvince(locations[0]);
		location.setCity(locations[1]);
		location.setDistrict(locations[2]);
		location.setStreet(locations[3]);
		location.setStreetNumber(locations[4]);
		location.setLatitude(vo.getLatitude());
		location.setLongitude(vo.getLongitude());
		baseDAO.save(location);
		RealActivity ra = new RealActivity();
		ra.setDataMark(1);
		ra.setDateTime(vo.getDateTime());
		ra.setDiscription(vo.getDescription());
		ra.setLocationId(location.getId());
		ra.setName("");
		ra.setTelephone(vo.getTelephone());
		ra.setUserId(vo.getUserID());
		ra.setActiontypeid(vo.getActiontypeid());
		baseDAO.save(ra);
	}
	
	/* through User's id get the one all the published message
	 * input user's id
	 * return the one all the published message 
	 * */
	public List<PublishMessageVO> getSomeOneAllPublishedMessage(int userid){
		List<PublishMessageVO> publishVOs = new ArrayList<PublishMessageVO>();
		List<RealActivity> realActivitys = baseDAO.queryFactory(new RealActivity(), "SELECT * from t_realactivity WHERE userId="+userid);
		for(RealActivity r:realActivitys){
			PublishMessageVO publishvo = new PublishMessageVO();
			Location location = baseDAO.get(new Location(),r.getLocationId());
			publishvo.setDateTime(r.getDateTime());
			publishvo.setDescription(r.getDiscription());
			publishvo.setLatitude(location.getLatitude());
			publishvo.setLongitude(location.getLongitude());
			publishvo.setTelephone(r.getTelephone());
			publishVOs.add(publishvo);
		}
		return publishVOs;
	}
	/* base on paging Query the Database and return the message
	 * @page incoming the select page in the views
	 * @rows incoming the select rows in the views
	 * */
	public List<PublishMessageVO> getPublishMessagePaging(int userid,int page,int rows){
		List<PublishMessageVO> publishVOs = new ArrayList<PublishMessageVO>();
		List<RealActivity> realActivitys = new ArrayList<RealActivity>();
		if(userid==0){
			realActivitys= baseDAO.queryListPageAndRows(new RealActivity(), page, rows, "t_realactivity", " ");
		}else{
			realActivitys= baseDAO.queryListPageAndRows(new RealActivity(), page, rows, "t_realactivity", " and userId = "+userid);
		}
		for(RealActivity r:realActivitys){
			PublishMessageVO publishvo = new PublishMessageVO();
			Location location = baseDAO.get(new Location(),r.getLocationId());
			publishvo.setRealactivityID(r.getId());
			publishvo.setDateTime(r.getDateTime());
			publishvo.setDescription(r.getDiscription());
			publishvo.setLatitude(location.getLatitude());
			publishvo.setLongitude(location.getLongitude());
			publishvo.setTelephone(r.getTelephone());
			publishvo.setActiontypeid(r.getActiontypeid());
			publishVOs.add(publishvo);
		}
		return publishVOs;
	}
	
	/* get someones all 
	 * */
	public int getPublishMessageTotalRows(int userid){
		if(userid==0){
			return baseDAO.queryFactory(new RealActivity(), "t_realactivity", " ").size();
		}else{
			return baseDAO.queryFactory(new RealActivity(), "t_realactivity", " and userId="+userid).size();
		}
	}
}
