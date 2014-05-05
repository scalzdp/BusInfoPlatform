package com.bip.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bip.vo.UserVO;

@Controller
public class TestController {
/*
 *  
 * test return html page
 * */
	@RequestMapping(value="template",method=RequestMethod.GET)
	public String testTemplate(){
		return "template/template1.html";
	}
	
	@RequestMapping(value = "htmlrequest", method = RequestMethod.POST)
	@ResponseBody
	public String createQuestionGroup(@RequestBody UserVO uservo, HttpServletResponse response) throws IOException{

	    // questionGroup - this comes OK.

	    response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
	    return "<div></div>";
	}
}
