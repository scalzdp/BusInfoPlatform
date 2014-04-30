package com.bip.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bip.service.LoginService;
import com.bip.vo.LoginVO;
import com.google.code.kaptcha.Constants;

@Controller

public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	private String getLoginpage(Model model){
		model.addAttribute("pagename", "registerandlogin/login.jsp");
		return "vertical";
	}
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	private ModelAndView postLoginpage(Model model,@ModelAttribute("form") LoginVO loginVO,HttpSession session) throws Exception{
		try{
			if(!session.getAttribute(Constants.KAPTCHA_SESSION_KEY).toString().equals(loginVO.getCaptcha().trim())){
				ModelAndView mv = new ModelAndView("redirect:/captchaError");//redirect模式  
				return mv;
			}
			if(loginService.getUser(loginVO)==null){
				//登录失败
				ModelAndView mv = new ModelAndView("redirect:/loginError");//redirect模式  
				return mv;
			}
			//登录成功
			ModelAndView mv = new ModelAndView("redirect:/loginSuccess");//redirect模式  
			return mv;
			
		}catch(Exception e){
			throw e;
		}
	}
	
	@RequestMapping(value="loginSuccess",method=RequestMethod.GET)
	private String getLoginSuccess(Model model){
		model.addAttribute("pagename", "template/default.jsp");
		return "index";
	}
}
