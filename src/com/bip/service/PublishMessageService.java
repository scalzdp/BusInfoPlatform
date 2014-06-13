package com.bip.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.bean.ActionType;
import com.bip.bean.Location;
import com.bip.bean.RealActivity;
import com.bip.dao.IBaseDAO;
import com.bip.utils.GetRequestClientUtil;
import com.bip.vo.PublishMessageVO;
import com.bip.vo.SearchMessageVO;

@Service
public class PublishMessageService {
	
	@Autowired
	private IBaseDAO baseDAO;
	
	private int SearchPageNum=0;
	
	private int ALLPageNum=0;
	
	/* save published message
	 * 1.save the incoming PublishMessageVO
	 * 2.then get the one all the published Message
	 * return the one all the published message
	 * addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
	 * */
	@SuppressWarnings("deprecation")
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
		ra.setDateTime(new Date(vo.getDateTime()));
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
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			publishvo.setDateTime(formatter.format(r.getDateTime()));
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
		ALLPageNum=realActivitys.size();
		for(RealActivity r:realActivitys){
			PublishMessageVO publishvo = new PublishMessageVO();
			Location location = baseDAO.get(new Location(),r.getLocationId());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			publishvo.setRealactivityID(r.getId());
			publishvo.setDateTime(formatter.format(r.getDateTime()));
			publishvo.setDescription(r.getDiscription());
			publishvo.setLatitude(location.getLatitude());
			publishvo.setLongitude(location.getLongitude());
			publishvo.setTelephone(r.getTelephone());
			publishvo.setActiontypeid(r.getActiontypeid());
			publishVOs.add(publishvo);
		}
		return publishVOs;
	}
	
	/* get someone's all  publish messages
	 * */
	public int getPublishMessageTotalRows(int userid){
		if(userid==0){
			return baseDAO.queryFactory(new RealActivity(), "t_realactivity", " ").size();
		}else{
			return baseDAO.queryFactory(new RealActivity(), "t_realactivity", " and userId="+userid).size();
		}
		/*return ALLPageNum;*/
	}
	
	/* modify the activity
	 * this modify the connection telephone and
	 * modify the activity description 
	 * */
	public void modifyRealActivity(PublishMessageVO vo){
		RealActivity ra = baseDAO.get(new RealActivity(), vo.getRealactivityID());
		ra.setTelephone(vo.getTelephone());
		ra.setDiscription(vo.getDescription());
		baseDAO.update(ra);
	}
	
	/* calculate the number of publish messages
	 * define a sentinel to count
	 * */
	public Integer CounterPublishNum(int count){
		return ++count;
	}
	
	/* clear the publish number
	 * return zero
	 * */
	public Integer ClearPublishNum(){
		return 0;
	}
	
	/* search the publish message by input message
	 * input the SearchMessageVO then search the accord the conditions message 
	 * then return the PublishMessageVO
	 * */
	public List<PublishMessageVO> SearchByInput(SearchMessageVO vo) {
		
		/* 1. use the location get the middle latitude and longitude
		 * 2. get the get the range of the latitude and longitude
		 * 3. use actionType to filter the does not meet the conditions
		 * 4. return the list result 
		 * */
		List<PublishMessageVO> vos = new ArrayList<PublishMessageVO>();
		Map<String,String> maplocation = GetRequestClientUtil.getGeocoderLatitude(vo.getProvince()+vo.getCity()+vo.getCounty());
		if(maplocation == null){
			return null;
		}
		if(maplocation.size()<0){
			return null;
		}
		Double lat=new Double(maplocation.get("lat"));
		Double lng=new Double(maplocation.get("lng"));
		List<Location> locations=baseDAO.queryFactory(new Location(), "t_location", " and latitude <= "+(lat+0.1)+" and latitude >="+(lat-0.1)+" and longitude <= "+(lng+0.1)+" and longitude >="+(lng-0.1));
		SearchPageNum=locations.size();
		for(Location l:locations){
			PublishMessageVO publishVO = new PublishMessageVO();
			publishVO.setLatitude(l.getLatitude());
			publishVO.setLongitude(l.getLongitude());
			List<RealActivity> realactivitys = baseDAO.queryFactory(new RealActivity(), "t_realactivity", " and locationId ="+l.getId());
			//TODO:let location message into PublishMessageVO
			if(realactivitys.size()>0){
				//TODO: let realactivity message into list
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ActionType atype = baseDAO.get(new ActionType() , realactivitys.get(0).getActiontypeid());
				publishVO.setActiontypename(atype.getName());
				publishVO.setDescription(realactivitys.get(0).getDiscription());
				publishVO.setTelephone(realactivitys.get(0).getTelephone());
				publishVO.setDateTime(formatter.format(realactivitys.get(0).getDateTime()));
			}
			vos.add(publishVO);
		}
		return vos;
	} 
	
	/* return the number of Search by input message
	 * 
	 * */
	public int GetSearchByInputCount(SearchMessageVO vo){
		return SearchPageNum;
	}
	
	public void deleteActivity(Integer activityid){
		RealActivity ra = baseDAO.get(new RealActivity(), activityid);
		baseDAO.delete(new Location(), ra.getLocationId());
		baseDAO.delete(new RealActivity(), ra.getId());
	}
	
	
}
