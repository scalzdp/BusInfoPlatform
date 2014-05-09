package com.bip.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@RequestMapping(value="UserManage",method=RequestMethod.GET)
	private String getUserManage(){
		return "usermanage/usermanage";
	}
	
	@RequestMapping(value="ModifyPWD",method=RequestMethod.GET)
	private String getModifyPWD(){
		return "usermanage/modifypassword";
	}
}
