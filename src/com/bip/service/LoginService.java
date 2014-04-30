package com.bip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.bean.User;
import com.bip.dao.IBaseDAO;
import com.bip.vo.LoginVO;

@Service
public class LoginService {
	
	@Autowired
	private IBaseDAO baseDAO;
	
	public LoginVO getUser(LoginVO loginVO){
		List<User> userlist = baseDAO.queryFactory(new User(), "t_user", " and email='"+loginVO.getUserEmail()+"'");
		if(userlist.size()<=0){
			return null;
		}else{
			loginVO.setUserEmail(userlist.get(0).getEmail());
			loginVO.setId(userlist.get(0).getId());
			return loginVO;
		}
	}
}
