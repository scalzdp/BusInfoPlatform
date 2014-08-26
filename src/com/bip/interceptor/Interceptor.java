package com.bip.interceptor;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bip.resource.ResourceFile;
import com.bip.utils.DataUtil;

/**
 * @author dp
 * @date 2014-08-26
 * define a interceptor it filter the request not login user 
 * */
@Repository
public class Interceptor extends HandlerInterceptorAdapter {

	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		//set request and response character encoding
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//set not filter pages
		String[] noFilters = new String[]{"",""};
		String uri = request.getRequestURI();
		//the request uri has the filter reqeust??
		if(uri.indexOf("loginSuccess")!=-1){
			boolean beFilter = true;
			for(String s : noFilters){
				if(uri.indexOf(s)==-1){
					beFilter = false;
					break;
				}
			}
			if(beFilter){
				Object obj = request.getSession().getAttribute(ResourceFile.USER_SESSION_KEY);
				if(null == obj){
					//not login the website
					PrintWriter out = response.getWriter();
					StringBuilder builder = new StringBuilder();
					builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");  
                    builder.append("alert(\"页面过期，请登录\");");  
                    builder.append("window.top.location.href=\"");  
                    builder.append(ResourceFile.BASE_PATH);  
                    builder.append("/login\";</script>");  
                    out.print(builder.toString());  
                    out.close();  
                    return false;
				}else{
					//login in maybe i can add the login dairy
				}
			}
		}
		
		Map paramsMap = request.getParameterMap();
		
		for(Iterator<Map.Entry> it = paramsMap.entrySet().iterator();it.hasNext();){
			Map.Entry entry = it.next();
			Object[] values = (Object[])entry.getValue();
			for (Object obj : values) {  
                if (!DataUtil.isUrlValueSuccessed(obj)) {  
                    throw new RuntimeException("有非法字符：" + obj);  
                }  
            }
		}
		return super.preHandle(request, response, handler);
	}
}
