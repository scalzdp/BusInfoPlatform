package com.bip.Controller;

import java.io.UnsupportedEncodingException;

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
import com.bip.utils.CommonUtils;
import com.bip.utils.MailUtil;
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
			uservo.setEmailvfcode(CommonUtils.getRandomString(uservo.getUserEmail().length()));
			registerService.save(uservo);
			UserVO userVO = registerService.getUser(uservo);
			String sendMessage ="请将发送过来的验证码: "+userVO.getEmailvfcode()+" :填写邮箱验证，账号只有在验证之后才能正常的使用！！\n  ";
			sendMessage+="你的邮箱： "+userVO.getUserEmail()+"\n";
			MailUtil.sendEmail(userVO.getUserEmail(), "邮箱验证码", sendMessage);
			session.setAttribute(ResourceFile.USER_SESSION_KEY, userVO);
			//ModelAndView mv = new ModelAndView("redirect:/loginSuccess");//redirectAnother action
			ModelAndView mv = new ModelAndView("redirect:/verification");//redirectAnother action
			return mv;
		}catch(Exception ex){
			throw ex;
		}
	}
	
	@RequestMapping(value="/verification",method=RequestMethod.GET)
	public String getEmailVerification(Model model,HttpSession session){
		try{
		UserVO userVO = (UserVO) session.getAttribute(ResourceFile.USER_SESSION_KEY);
		model.addAttribute("userEmail", userVO.getUserEmail());
		}finally{
			
		}
		model.addAttribute("pagename", "registerandlogin/emailVerification.jsp");
		return "vertical";
	}
	
	@RequestMapping(value="/verification",method=RequestMethod.POST)
	private ModelAndView postEmailVerification(Model model,HttpServletRequest request,HttpSession session){
		UserVO userVO = (UserVO) session.getAttribute(ResourceFile.USER_SESSION_KEY);
		String EVC= request.getParameter("emailVerificationCode");
		String captcha = request.getParameter("captcha");
		if(!session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString().equals(captcha.trim())){
			ModelAndView mv = new ModelAndView("redirect:/captchaError");//redirect captcha error page
			return mv;
		}
		if(!EVC.equals(userVO.getEmailvfcode())){
			ModelAndView mv = new ModelAndView("redirect:/evc");//redirect emailVerificationCode error page
			return mv;
		}
		userVO.setIsActive("1");
		registerService.updateUser(userVO);
		ModelAndView mv = new ModelAndView("redirect:/loginSuccess");//redirectAnother action
		return mv;
	}
	
	@RequestMapping(value="/sendEmail",method=RequestMethod.GET)
	private String getSendEmail(Model model){
		model.addAttribute("pagename", "registerandlogin/sendEmail.jsp");
		return "vertical";
	}
	
	@RequestMapping(value="/sendEmail",method=RequestMethod.POST)
	private ModelAndView postSendEmail(Model model,HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException{
		String email = request.getParameter("userEmail");
		UserVO userVO= new UserVO();
		userVO.setUserEmail(email);
		UserVO userVOResult = registerService.findUserByEmail(userVO);
		if(userVOResult==null){
			//TODO:return email not true
			ModelAndView mv = new ModelAndView("redirect:/sendEmailWrong");//redirectAnother action
			return mv;
		}else{
			//TODO:send email
			String sendMessage ="请将发送过来的验证码: "+userVOResult.getEmailvfcode()+" :填写邮箱验证，账号只有在验证之后才能正常的使用！！\n  ";
			MailUtil.sendEmail(email, "邮箱验证码", sendMessage);
		}
		session.setAttribute(ResourceFile.USER_SESSION_KEY, userVOResult);
		ModelAndView mv = new ModelAndView("redirect:/verification");//redirectAnother action
		return mv;
	}
	
	
	
}
