package com.bip.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bip.service.RegisterService;
import com.bip.vo.RegisterVO;
import com.google.code.kaptcha.Constants;

@Controller
public class RegisteController {
	
	@Autowired
	private RegisterService registerService;
	
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	private String getRegister(Model model){
		model.addAttribute("pagename", "register/register.jsp");
		Log userregistererror = LogFactory.getLog("registerError"); //注册模块
		Log userloginwarn = LogFactory.getLog("loginInfo");//登录模块
		userregistererror.error("userloginerror 。。。。");
		userloginwarn.info("userloginwarn。。。。");
		userloginwarn.error("userloginwarnerror。。。。");
		//日志记录到数据库里面
		Log logDB = LogFactory.getLog("logInDb");
		logDB.info("hello ,how are you!");
		return "index";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	private ModelAndView postRegister(Model model,@ModelAttribute("form") RegisterVO registervo,
			HttpSession session) throws Exception{
		try{
			//判断验证码是否正确
//			if(!session.getAttribute(Constants.KAPTCHA_SESSION_KEY).toString().equals(registervo.getCaptcha().trim())){
//				return "redirect:/error/captchaError";
//			}
//			registerService.save(registervo);
			ModelAndView mv = new ModelAndView("redirect:captchaError");//redirect模式  
			return mv;
		}catch(Exception ex){
			throw ex;
		}
	}
	
	@RequestMapping(value="/registerSuccess",method=RequestMethod.GET)
	private String registerSuccess(){
		return "message/captcha";
	}
}
