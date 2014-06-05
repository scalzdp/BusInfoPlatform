package com.bip.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bip.service.UserService;
import com.bip.vo.UserAndProfileVO;

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
}
