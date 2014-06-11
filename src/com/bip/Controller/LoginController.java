package com.bip.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bip.resource.ResourceFile;
import com.bip.service.LoginService;
import com.bip.util.SessionManager;
import com.bip.vo.UserVO;
import com.google.code.kaptcha.Constants;

@Controller

public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	private String getLoginpage(Model model,HttpSession session){
		SessionManager.clearUserSession(session);
		model.addAttribute("pagename", "registerandlogin/login.jsp");
		return "vertical";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	private ModelAndView postLoginpage(Model model,@ModelAttribute("form") UserVO loginVO,HttpSession session) throws Exception{
		try{
			if(!session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString().equals(loginVO.getCaptcha().trim())){
				ModelAndView mv = new ModelAndView("redirect:/captchaError");//redirectģʽ  
				return mv;
			}
			UserVO user=loginService.getUser(loginVO);
			if(user==null){
				//not has this user
				ModelAndView mv = new ModelAndView("redirect:/loginError");//redirectģʽ  
				return mv;
			}
			if(user.getIsActive()==null){
				ModelAndView mv = new ModelAndView("redirect:/sendEmail");//redirectģʽ  
				return mv;
			}
			//this user is right and select form to it and save this user
			session.setAttribute(ResourceFile.USER_SESSION_KEY, user);
			ModelAndView mv = new ModelAndView("redirect:/loginSuccess");//redirectģʽ  
			return mv;
			
		}catch(Exception e){
			throw e;
		}
	}
	
	@RequestMapping(value="loginSuccess",method=RequestMethod.GET)
	private String getLoginSuccess(Model model,HttpSession session){
		model.addAttribute(ResourceFile.USER_SESSION_KEY, session.getAttribute(ResourceFile.USER_SESSION_KEY));
		model.addAttribute("pagename", "template/default.jsp");
		return "index";
	}
}
