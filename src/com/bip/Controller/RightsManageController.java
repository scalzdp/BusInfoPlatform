package com.bip.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RightsManageController {
	
	@RequestMapping(value="RoleManage",method=RequestMethod.GET)
	private String getRoleManage(){
		return "rightsmanage/rolemanage";
	}
	
	@RequestMapping(value="RoleAuthority",method=RequestMethod.GET)
	private String getRoleAuthority(){
		return "rightsmanage/rightsmanage";
	}
}
