package com.bip.Controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bip.resource.ResourceFile;
import com.bip.service.ActionTypeService;
import com.bip.service.PublishMessageService;
import com.bip.utils.CommonUtils;
import com.bip.utils.GetRequestClientUtil;
import com.bip.vo.PublishMessageVO;
import com.bip.vo.SearchMessageVO;
import com.bip.vo.UserVO;


@Controller
public class PublishMessageController {

	@Autowired
	private PublishMessageService publishService;
	
	@Autowired
	private ActionTypeService actionTypeService;
	
	private static int countNum;
	
	@RequestMapping(value="PublishedProducts",method=RequestMethod.GET)
	private String getPublishProduct(Model model, HttpSession session,
									 HttpServletRequest request){
		/* default place is ChengDu ,Default Ip is 61.139.66.76.If system not get someones 
		 * place or ip it will display ChengDu city.
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
		//add Longitude and Latitude to model,JSP page will get it by this type
		model.addAttribute("lngcenter", lng) ;
		model.addAttribute("latcenter", lat) ;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "publishmessage/publishproduct";
	}
	
	@RequestMapping(value="PublishedProducts",method=RequestMethod.POST)
	private String postPublishProduct(Model model,@ModelAttribute("form") PublishMessageVO publishVO,HttpSession session){
		//
		UserVO uservo =  (UserVO)session.getAttribute(ResourceFile.USER_SESSION_KEY);
		publishVO.setUserID(uservo.getId());
		publishService.saveMessage(publishVO);
		countNum = publishService.CounterPublishNum(countNum);
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
	private String getSearchProducts(Model model){
		return "publishmessage/searchproduct";
	}
	
	@RequestMapping(value="someonepublishmessage")
	public @ResponseBody
     Map<String, Object> getSomeOnePublishMessage( Map<String, Object> map,  
            @RequestParam(required = false, defaultValue = "1") Integer page, //get the select page number 
            @RequestParam(required = false, defaultValue = "10") Integer rows, //get the row number from the select value
            HttpSession session) throws IOException{
		//DataGrid server paging user input page and rows for paging this page
        //get the user list object messages
		UserVO uservo =  (UserVO)session.getAttribute(ResourceFile.USER_SESSION_KEY);
        List<PublishMessageVO> publishMessageVO = publishService.getPublishMessagePaging(uservo.getId(),page,rows);

        //get message row numbers
        int totalRows = publishService.getPublishMessageTotalRows(uservo.getId());
        map.put("total", totalRows);
        map.put("rows", publishMessageVO);
        //those message object will convert to json message then return map object
        
        return map;
    }
	
	@RequestMapping(value="allpublishmessage")
	public @ResponseBody
		Map<String, Object> getAllPublishMessage( Map<String, Object> map,  
	            @RequestParam(required = false, defaultValue = "1") Integer page, //get the select page number 
	            @RequestParam(required = false, defaultValue = "10") Integer rows //get the row number from the select value
	            ){
		List<PublishMessageVO> publishMessageVO = publishService.getPublishMessagePaging(0,page,rows);

        //get message row numbers
        int totalRows = publishService.getPublishMessageTotalRows(0);
        map.put("total", totalRows);
        map.put("rows", publishMessageVO);
        //those message object will convert to json message then return map object
        
        return map;
	}
	
	@RequestMapping(value="/published/saveedit")
	public @ResponseBody  String acceptEditDg(HttpServletRequest request) throws IOException{
		String postDatas = request.getParameter("postdata1");
		postDatas=URLDecoder.decode(postDatas,"UTF-8");
		System.out.println(postDatas);
		//try convert this json to object that can modify the object
		PublishMessageVO vo = CommonUtils.convertJsonToObject(new PublishMessageVO(),postDatas);
		if(vo!=null){
			System.out.println(vo.getDescription());
			System.out.println(vo.getTelephone());
			publishService.modifyRealActivity(vo);
		}
		return "json";
	} 
	
	@RequestMapping(value="allpublishmessagecount")
	public String getAllPublishMessageCount(HttpServletRequest  request) throws InterruptedException{
		request.setAttribute("jsonData", countNum);
		countNum = publishService.ClearPublishNum();
		return "json";
	}
	

	
	@RequestMapping(value="SearchPublishProducts")
	public @ResponseBody Map<String, Object> postSearchProducts(
			 Map<String, Object> map,

			 @RequestParam(required = false, defaultValue = "1") Integer page, //get the select page number 
	         @RequestParam(required = false, defaultValue = "10") Integer rows, //get the row number from the select value
	         HttpServletRequest request
	            ){
		/* 1.get the input message from the page
		 * 2.search the message by the input value object
		 * 3.return the message back to the JSP page like JSON String
		 * */
		SearchMessageVO searchvo = new SearchMessageVO();
		searchvo.setProvince(request.getParameter("province"));
		searchvo.setCity(request.getParameter("city"));
		searchvo.setCounty(request.getParameter("county"));
		searchvo.setActiontypeid(new Integer(request.getParameter("actiontypeid")));
		searchvo.setBeginDateTime(new Date(request.getParameter("beginDateTime")));
		searchvo.setEndDateTime(new Date(request.getParameter("endDateTime")));
		
		List<PublishMessageVO> publishVOs = publishService.SearchByInput(searchvo);
		if(publishVOs==null){
			publishVOs = new ArrayList<PublishMessageVO>();
		}
		int totalRows = publishService.GetSearchByInputCount(searchvo);
        //those message object will convert to json message then return map object
		map.put("total", totalRows);
        map.put("rows", publishVOs);
        return map;
        /*
		System.out.println(request.getParameter("province"));
		List<PublishMessageVO> publishMessageVO = publishService.getPublishMessagePaging(2,page,rows);

        //get message row numbers
        int totalRows = publishService.getPublishMessageTotalRows(2);
        map.put("total", totalRows);
        map.put("rows", publishMessageVO);
        //those message object will convert to json message then return map object
        
        return map;
        */
	}
	

}
