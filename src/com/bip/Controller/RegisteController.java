package com.bip.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bip.service.RegisterService;
import com.bip.vo.RegisterVO;
import com.google.code.kaptcha.Constants;

@Controller
public class RegisteController {
	
	@Autowired
	private RegisterService registerService;
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	private String getRegister(Model model){
		return "register/register";
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	private String postRegister(Model model,@ModelAttribute("form") RegisterVO registervo,
			HttpSession session) throws Exception{
		try{
			//判断验证码是否正确
			if(!session.getAttribute(Constants.KAPTCHA_SESSION_KEY).toString().equals(registervo.getCaptcha().trim())){
				
			}
			registerService.save(registervo);
			return "";
		}catch(Exception ex){
			throw ex;
		}
	}
}
