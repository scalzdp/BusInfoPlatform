package com.bip.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.constructs.web.ResponseUtil;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bip.service.UserService;
import com.bip.utils.CommonUtils;
import com.bip.vo.UserVO;

@Controller
public class TestController {
/*
 *  
 * test return html page
 * */
	
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping(value="paging")
	public @ResponseBody
     Map<String, Object> getJson( Map<String, Object> map,  
            @RequestParam(required = false, defaultValue = "1") Integer page, //get the select page number 
            @RequestParam(required = false, defaultValue = "10") Integer rows //get the row number from the select value
            ) throws IOException{
		//DataGrid server paging user input page and rows for paging this page
        //get the user list object messages
        List<UserVO> UserVOList = userService.getUser(page,rows);

        //get message row numbers
        int totalRows = userService.getTotalRows();
        map.put("total", totalRows);
        map.put("rows", UserVOList);
        //those message object will convert to json message then return map object
        
        return map;
    }

}
