package com.bip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.bean.User;
import com.bip.dao.IBaseDAO;
import com.bip.vo.RegisterVO;

@Service
public class RegisterService {

	@Autowired
	private IBaseDAO baseDAO;

	public void save(RegisterVO registervo) {
		User user = new User();
		if("".equals(registervo.getUserNickName().trim())){
			user.setNickName(registervo.getUserEmail());
		}else{
			user.setNickName(registervo.getUserNickName());
		}
		user.setEmail(registervo.getUserEmail());
		user.setPassword(registervo.getUserPassword());
		baseDAO.save(user);
	}
	
	
}
