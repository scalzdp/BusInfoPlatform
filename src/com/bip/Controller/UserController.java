package com.bip.Controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bip.resource.ResourceFile;
import com.bip.service.UserService;
import com.bip.utils.MailUtil;
import com.bip.vo.SearchUserVO;
import com.bip.vo.UserAndProfileVO;
import com.bip.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="UserManage",method=RequestMethod.GET)
	private String getUserManage(){
		return "usermanage/usermanage";
	}
	
	@RequestMapping(value="ModifyPWD",method=RequestMethod.GET)
	private String getModifyPWD(){
		return "usermanage/modifypassword";
	}
	
	@RequestMapping(value="alluser")
	public @ResponseBody 
	Map<String,Object> getAllUser(Map<String, Object> map,  
            @RequestParam(required = false, defaultValue = "1") Integer page, //get the select page number 
            @RequestParam(required = false, defaultValue = "10") Integer rows //get the row number from the select value
			){
		List<UserAndProfileVO> userAndProfileVos = userService.getAllUser(page,rows);
		int totalRows = userService.getAllUserCount();
		map.put("total", totalRows);
        map.put("rows", userAndProfileVos);
		return map;
	}
	
	@RequestMapping(value="SearchUser")
	public @ResponseBody
	Map<String,Object> SearchUser(Map<String, Object> map,
			 @RequestParam(required = false, defaultValue = "1") Integer page, //get the select page number 
	         @RequestParam(required = false, defaultValue = "10") Integer rows, //get the row number from the select value
	         HttpServletRequest request){
		SearchUserVO searchUserVo=new SearchUserVO();
		searchUserVo.setSearchEmail(request.getParameter("searchEmail"));
		searchUserVo.setSearchAge(request.getParameter("searchAge"));
		searchUserVo.setSearchNickName(request.getParameter("searchNickName"));
		
		List<UserAndProfileVO> searchResults = userService.SearchByInputUser(searchUserVo,page,rows);
		int totalRows = userService.SearchByInputUserCount(searchUserVo);
		map.put("total", totalRows);
		map.put("rows", searchResults);
		
		return map;
	}
	
	@RequestMapping(value="getEmailVerifyCode",method=RequestMethod.POST)
	public String postRegisterEmail(Model model,HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException{
		String email = request.getParameter("email");
		//String captcha = request.getParameter("captcha");
		String jsonData;
//		if(captcha.equals(session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString())){
//			jsonData="captchaError";
//			request.setAttribute("jsonData", jsonData);
//			return "json";
//		}
		UserVO uservo= new UserVO();
		uservo.setUserEmail(email);
		uservo = userService.findUserByEmail(uservo);
		
		if(uservo==null){
			jsonData="EmailError";
		}else{
			session.setAttribute(ResourceFile.USERVO_SESSION_KEY, uservo);
			//Send Email
			String sendMessage ="请将发送过来的验证码: "+uservo.getEmailvfcode()+" :与注册邮箱一并填入并提交既可以获得密码.  ";
			sendMessage+="你的邮箱： "+uservo.getUserEmail()+"";
			MailUtil.sendEmail(uservo.getUserEmail(), "邮箱验证码", sendMessage);
			jsonData="EmailSend";
		}
		request.setAttribute("jsonData", jsonData);
		return "json";
	}
	
	@RequestMapping(value="getPassword",method=RequestMethod.POST)
	public ModelAndView getLoginPassword(Model model,HttpServletRequest request,HttpSession session) throws UnsupportedEncodingException{
		String email = request.getParameter("useremail").trim();
		String verifyCode = request.getParameter("emailverifycode").trim();
		UserVO uservo= (UserVO)session.getAttribute(ResourceFile.USERVO_SESSION_KEY);
		if(uservo.getUserEmail().equals(email)&&uservo.getEmailvfcode().equals(verifyCode)){
			//sendEmail
			String sendMessage ="你的注册邮箱： "+uservo.getUserEmail()+"\n";
			sendMessage += "你的登陆密码： "+uservo.getUserPassword()+"\n";
			sendMessage += "请妥善管理你的密码！";
			MailUtil.sendEmail(uservo.getUserEmail(), "邮箱密码", sendMessage);
		}else{
			ModelAndView mv = new ModelAndView("redirect:/sendEmailWrong");
			return mv;
		}
		ModelAndView mv = new ModelAndView("redirect:/login");
		return mv;
	}
}
