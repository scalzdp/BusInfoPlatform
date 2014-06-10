package com.bip.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bip.resource.ResourceFile;
import com.bip.service.RegisterService;
import com.bip.vo.UserVO;
import com.google.code.kaptcha.Constants;

@Controller
public class RegisteController {
	
	@Autowired
	private RegisterService registerService;
	
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	private String getRegister(Model model){
		model.addAttribute("pagename", "registerandlogin/register.jsp");
		return "vertical";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	private ModelAndView postRegister(Model model,@ModelAttribute("form") UserVO uservo,
			HttpSession session) throws Exception{
		try{
			if(!session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString().equals(uservo.getCaptcha().trim())){
				ModelAndView mv = new ModelAndView("redirect:/captchaError");//redirect captcha error page
				return mv;
			}if(!registerService.findEmailHasRegister(uservo)){
				//this email has register
				ModelAndView mv = new ModelAndView("redirect:/emailRepeat");//redirectAnother action
				return mv;
			}
			registerService.save(uservo);
			UserVO userVO = registerService.getUser(uservo);
			session.setAttribute(ResourceFile.USER_SESSION_KEY, userVO);
			ModelAndView mv = new ModelAndView("redirect:/loginSuccess");//redirectAnother action
			return mv;
		}catch(Exception ex){
			throw ex;
		}
	}
	
	@RequestMapping(value="/verification",method=RequestMethod.GET)
	public String getEmailVerification(Model model){
		model.addAttribute("pagename", "registerandlogin/emailVerification.jsp");
		return "vertical";
	}
	
	@RequestMapping(value="/verification",method=RequestMethod.POST)
	public ModelAndView postEmailVerification(Model model){
		ModelAndView mv = new ModelAndView("redirect:/loginSuccess");//redirectAnother action
		return mv;
	}
	
}
