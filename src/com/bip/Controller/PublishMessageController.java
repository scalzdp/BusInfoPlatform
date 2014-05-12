package com.bip.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bip.utils.GetRequestClientUtil;
import com.bip.vo.PublishMessageVO;

@Controller
public class PublishMessageController {

	@RequestMapping(value="PublishedProducts",method=RequestMethod.GET)
	private String getPublishProduct(Model  model, HttpSession session,HttpServletRequest request){
		/* 一、通过登录的IP判断所处的城市，查询该城市的相关信息
		 * 二、将ActionType查询出来，并赋值给model
		 * */
		String ip="61.139.66.76";
		ip =GetRequestClientUtil.getIpAddr(request);
		String lng = "104.06";	
		String lat = "30.67";
		try{
		Map<String,String> address = GetRequestClientUtil.getAddress("ip="+ip, "utf-8");
		if(!address.equals("")){
			Map<String,String> map = GetRequestClientUtil.getGeocoderLatitude(address.get("region")+" "+address.get("city")+" "+address.get("area")+" "+address.get("isp"));
			if(map!=null){
				if(map.size()>0){
					lng = map.get("lng");	
					lat =map.get("lat");
				}
			}
			session.setAttribute("streetName", "");
			session.setAttribute("townName", "");
			session.setAttribute("cityName", address.get("city"));
		}
		
		//将查询出来的经纬度返回到页面
		model.addAttribute("lngcenter", lng) ;
		model.addAttribute("latcenter", lat) ;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "publishmessage/publishproduct";
	}
	
	@RequestMapping(value="PublishedProducts",method=RequestMethod.POST)
	private String postPublishProduct(Model model,@ModelAttribute("form") PublishMessageVO publishVO){
		//
		return "publishmessage/publishproduct";
	}
	
	@RequestMapping(value="ProductsClassManage",method=RequestMethod.GET)
	private String getProductsClassManage(){
		return "publishmessage/productsclassmanage";
	}
	
	@RequestMapping(value="AllPublishedProducts",method=RequestMethod.GET)
	private String getAllPublishedProducts(){
		return "publishmessage/allpublishedproducts";
	}
	
	@RequestMapping(value="SearchProducts",method=RequestMethod.GET)
	private String getSearchProducts(){
		return "publishmessage/searchproduct";
	}
}
