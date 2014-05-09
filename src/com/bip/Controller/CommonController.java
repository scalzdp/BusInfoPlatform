package com.bip.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {
	
	@RequestMapping(value="DiaryManage",method=RequestMethod.GET)
	private String getDiaryManage(){
		return "commonfeature/daily";
	}
}
