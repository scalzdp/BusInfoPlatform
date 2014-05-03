package com.bip.Controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bip.bean.User;
import com.bip.utils.CommonUtils;

@Controller
public class MessageController {

	@RequestMapping(value="/captchaError",method=RequestMethod.GET)
	private String getCaptchaError(Model model){
		model.addAttribute("pagename", "message/captcha.jsp");
		return "vertical";
	}
	
	@RequestMapping(value="/loginError",method=RequestMethod.GET)
	private String getUserEmailOrPasswordError(Model model){
		model.addAttribute("pagename", "message/userpassworderror.jsp");
		return "vertical";
	}
	
	@RequestMapping(value="getnews")
	private String getNews(HttpServletRequest request)throws Exception{
		try{
			List<User> users = new ArrayList<User>();
			//search the news ,then convert those list to json data.
			String jsonData = CommonUtils.convertObjectToJson(users);
			request.setAttribute("jsonData", jsonData);
			return "json";
		}catch(Exception e){
			throw e;
		}
	}
}
