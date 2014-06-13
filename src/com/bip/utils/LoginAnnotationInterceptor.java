package com.bip.utils;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.support.HandlerMethodResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bip.resource.ResourceFile;
import com.bip.vo.UserVO;

public class LoginAnnotationInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
		/*//HandlerMethod handler2 = (HandlerMethod) handler;
	    Login login = handler2.getMethodAnnotation(Login.class);

	    if (null == login) {
	        // 没有声明权限,放行
	        return true;
	    }

	    HttpSession session = request.getSession();
	            //取得session中的用户信息, 以便判断是否登录了系统
	    UserVO uservo = (UserVO) session.getAttribute(ResourceFile.USER_SESSION_KEY);

	    if (null == uservo) {
	        // 需要登录
	        if (login.value() == ResultTypeEnum.page) {
	            //传统页面的登录
	            request.getRequestDispatcher("/login.jsp?oprst=false&opmsg=请登录!").forward(request, response);
	        } else if (login.value() == ResultTypeEnum.json) {
	            //ajax页面的登录
	            response.setCharacterEncoding("utf-8");
	            response.setContentType("text/html;charset=UTF-8");
	            OutputStream out = response.getOutputStream();
	            PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,"utf-8"));
	            //返回json格式的提示
	            pw.println("{\"result\":false,\"code\":11,\"errorMessage\":\"您未登录,请先登录\"}");
	            pw.flush();
	            pw.close();
	        }
	        return false;
	    }
	       return true;
	       */
		return false;
	}
}
