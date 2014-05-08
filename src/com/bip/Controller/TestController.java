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
            @RequestParam(required = false, defaultValue = "1") Integer page, //第几页  
            @RequestParam(required = false, defaultValue = "10") Integer rows //页数大小 
            ) throws IOException{
		//DataGrid 会向 请求Json 的地址以POST方法发送2个参数：page（当前页码）和rows（每页显示记录数）
        //获取分页数据
        List<UserVO> UserVOList = userService.getUser(page,rows);

        //获取总记录数
        int totalRows = userService.getTotalRows();

        map.put("total", totalRows);
        map.put("rows", UserVOList);
        //返回指定格式的Map，Jackson会把Map转换未Json
        return map;
    }

}
