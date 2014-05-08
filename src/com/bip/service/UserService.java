package com.bip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.dao.IBaseDAO;
import com.bip.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private IBaseDAO baseDAO;
	
	public List<UserVO> getUser(int page,int rows){
		List<UserVO> userVO = new ArrayList<UserVO>();
		for(int i=0;i<10;i++){
			UserVO uservo = new UserVO();
			uservo.setId(i);
			uservo.setUserEmail("275735763@qq.com");
			uservo.setUserNickName("kaka");
			uservo.setUserPassword("aaa");
			userVO.add(uservo);
		}
		return userVO;
	}

	/* get all user rows in database
	 * 
	 * */
	public int getTotalRows() {
		
		return 10;
	}
}
