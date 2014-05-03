package com.bip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.bean.User;
import com.bip.dao.IBaseDAO;
import com.bip.vo.UserVO;

@Service
public class LoginService {
	
	@Autowired
	private IBaseDAO baseDAO;
	
	/*
	 * use email to search the user,
	 * if the search result is null return null
	 * if search the result and return the result;
	 * */
	public UserVO getUser(UserVO userVO){
		List<User> userlist = baseDAO.queryFactory(new User(), "t_user", " and email='"+userVO.getUserEmail()+"'");
		if(userlist.size()<=0){
			return null;
		}else{
			userVO.setUserEmail(userlist.get(0).getEmail());
			userVO.setId(userlist.get(0).getId());
			return userVO;
		}
	}
}
