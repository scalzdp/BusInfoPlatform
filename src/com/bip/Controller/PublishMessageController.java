package com.bip.Controller;

import java.io.File;
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

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bip.resource.ResourceFile;
import com.bip.service.ActionTypeService;
import com.bip.service.PublishMessageService;
import com.bip.utils.CacheTools;
import com.bip.utils.CommonUtils;
import com.bip.utils.GetRequestClientUtil;
import com.bip.utils.PicUploadUtil;
import com.bip.vo.CacheKeyVO;
import com.bip.vo.PictureVO;
import com.bip.vo.PublishMessageVO;
import com.bip.vo.SearchMessageVO;
import com.bip.vo.UserVO;


@Controller
public class PublishMessageController {

	@Autowired
	private PublishMessageService publishService;
	
	@Autowired
	private ActionTypeService actionTypeService;
	
	@Autowired
	private CacheTools cachetools;
	
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
		CacheKeyVO cacheKeyVO = new CacheKeyVO();
		UserVO uservo =  (UserVO)session.getAttribute(ResourceFile.USER_SESSION_KEY);
		publishVO.setUserID(uservo.getId());
		PublishMessageVO tmppublishvo =publishService.saveMessage(publishVO);
		countNum = publishService.CounterPublishNum(countNum);
		
		cacheKeyVO.setDataMark(1);
		cacheKeyVO.setF1(tmppublishvo.getId());
		cacheKeyVO.setProperty1(tmppublishvo.getLocation());
		cacheKeyVO.setTypeID(publishVO.getActiontypeid());
		cachetools.StoreCacheKeyToCached(cacheKeyVO);
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
		String postDatas = request.getParameter("postdata1").trim();
		postDatas=URLDecoder.decode(postDatas,"UTF-8");
		System.out.println(postDatas);
		//try convert this json to object that can modify the object
		PublishMessageVO vo = CommonUtils.convertJsonToObject(new PublishMessageVO(),postDatas);
		if(vo!=null){
			CacheKeyVO cachevo = new CacheKeyVO();
			System.out.println(vo.getDescription());
			System.out.println(vo.getTelephone());
			publishService.modifyRealActivity(vo);
			//modify the cache data
			cachevo.setDataMark(1);
			cachevo.setF1(vo.getId());
			cachevo.setProperty1(vo.getLocation());
			cachetools.StoreCacheKeyToCached(cachevo);
		}
		return "json";
	} 
	
	@RequestMapping(value="/published/del")
	public @ResponseBody String acceptDelDg(HttpServletRequest request){
		String activityid = request.getParameter("activityid");
		System.out.println(activityid);
		publishService.deleteActivity(new Integer(activityid));
		return "json";
	}
	
	@RequestMapping(value="allpublishmessagecount")
	public String getAllPublishMessageCount(HttpServletRequest  request) throws InterruptedException{
		request.setAttribute("jsonData", countNum);
		countNum = publishService.ClearPublishNum();
		return "json";
	}
	

	
	@RequestMapping(value="SearchPublishProducts",method=RequestMethod.POST)
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
		List<PublishMessageVO> publishVOs = new ArrayList<PublishMessageVO>();
		if(request.getParameter("province")==null){
			map.put("total", 0);
	        map.put("rows", publishVOs);
	        return map;
		}
		searchvo.setProvince(request.getParameter("province"));
		searchvo.setCity(request.getParameter("city"));
		searchvo.setCounty(request.getParameter("county"));
		searchvo.setActiontypeid(new Integer(request.getParameter("actiontypeid")));
		searchvo.setBeginDateTime(new Date(request.getParameter("beginDateTime").trim()));
		searchvo.setEndDateTime(new Date(request.getParameter("endDateTime").trim()));
		
		publishVOs = publishService.SearchByInput(searchvo);
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
	
	@RequestMapping(value="/fileUpload/{id}",method=RequestMethod.GET)
	public String getUploadPage(Model model,@PathVariable Integer id){
		model.addAttribute("realactivityID", id);
		System.out.println(id);
		List<PictureVO> vos = publishService.getRealActivityPic(id);
		model.addAttribute("pictureVO", vos);
		return "publishmessage/fileUpload";
	}
	
	@RequestMapping(value="/fileUpload",method=RequestMethod.POST)
	public String postUploadPage(Model model,HttpServletRequest request){
		PictureVO vo = new PictureVO();
		int realActivityId = Integer.parseInt(request.getParameter("realactivityID"));
		String basePath = new File(ResourceFile.UPLOAD_PICTURE_PATH).toString()+"\\";
		//转型为MultipartHttpRequest(重点的所在)  
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
        //  获得第1张图片（根据前台的name名称得到上传的文件）   
        MultipartFile imgFile1  =  multipartRequest.getFile("imgFile");  
         
        //定义一个数组，用于保存可上传的文件类型  
        List fileTypes = new ArrayList();  
        fileTypes.add("jpg");  
        fileTypes.add("jpeg");  
        fileTypes.add("bmp");  
        fileTypes.add("gif");  
        fileTypes.add("png"); 
        //保存第一张图片
        if(!(imgFile1.getOriginalFilename() ==null || "".equals(imgFile1.getOriginalFilename()))) {  
		/*下面调用的方法，主要是用来检测上传的文件是否属于允许上传的类型范围内，及根据传入的路径名 
		*自动创建文件夹和文件名，返回的File文件我们可以用来做其它的使用，如得到保存后的文件名路径等 
		*这里我就先不做多的介绍。 
		*/  
		 Date now = new Date();
		 String timespan = now.getTime()+"";
       	 String path1= timespan;
       	 String path2= "Img";
       	 String fileName	 = imgFile1.getOriginalFilename();
       	
       	 fileName=CommonUtils.GenerateMaxPicName(fileName, timespan);
         File file1 = PicUploadUtil.getFile(imgFile1,path1,path2,fileTypes,fileName,basePath);
         vo.setIsMain(0);
         vo.setPicMaxPath("/"+timespan+"/"+path2+"/"+fileName);
         vo.setRealActivityId(realActivityId);
         vo.setPicMinPath(fileName);
         publishService.savePic(vo);
        }     
		return "redirect:/fileUpload/"+realActivityId;
	}
	
	@RequestMapping(value="/setMainPic",method=RequestMethod.POST)
	public void setTheMainPic(HttpServletRequest request){
		int pictureID = Integer.parseInt(request.getParameter("ID"));
		int realactivityID = Integer.parseInt(request.getParameter("RID"));
		publishService.SetMainPicture(pictureID, realactivityID);
	}
	
	
	@RequestMapping(value="/deletePic",method=RequestMethod.POST)
	public @ResponseBody String deletePic(Model model,HttpServletRequest request){
		int pictureID = Integer.parseInt(request.getParameter("pid"));
		System.out.println(pictureID);
		publishService.deletePic(pictureID);
		return "success";
	}
	

}
