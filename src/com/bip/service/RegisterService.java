package com.bip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.bean.User;
import com.bip.dao.IBaseDAO;
import com.bip.vo.UserVO;

@Service
public class RegisterService {

	@Autowired
	private IBaseDAO baseDAO;

	/*before register look up all of the DataBase find if there has the same email
	 * save the register user message 
	 * */
	public void save(UserVO registervo) {
		User user = new User();
		if("".equals(registervo.getUserNickName().trim())){
			user.setNickName(registervo.getUserEmail());
		}else{
			user.setNickName(registervo.getUserNickName());
		}
		user.setEmail(registervo.getUserEmail());
		user.setPassword(registervo.getUserPassword());
		user.setEmailvfcode(registervo.getEmailvfcode());
		baseDAO.save(user);
	}
	
	/*look up all the database
	 * if the email has register return false;
	 * if the email have not register return ture.
	 * */
	public boolean findEmailHasRegister(UserVO uservo){
		List<User> userlist = baseDAO.queryFactory(new User(), "t_user", " and email='"+uservo.getUserEmail()+"'");
		if(userlist.size()<=0){
			return true;
		}else{
			return false;
		}
	}
	
	/* query user by email
	 * */
	public UserVO findUserByEmail(UserVO uservo){
		UserVO vo = new UserVO();
		List<User> userlist = baseDAO.queryFactory(new User(), "t_user", " and email='"+uservo.getUserEmail()+"'");
		if(userlist.size()<=0){
			vo=null;
		}else{
			vo.setEmailvfcode(userlist.get(0).getEmailvfcode());
			vo.setUserEmail(userlist.get(0).getEmail());
			vo.setUserNickName(userlist.get(0).getNickName());
			vo.setId(userlist.get(0).getId());
		}
		return vo;
		
	}
	
	/*if register successful then query the database ,query this uservo
	 * then return this uservo to controller
	 * */
	public UserVO getUser(UserVO userVO){
		List<User> userlist = baseDAO.queryFactory(new User(), "t_user", " and email='"+userVO.getUserEmail()+"'");
		if(userlist.size()<=0){
			return null;
		}else{
			userVO.setUserNickName(userlist.get(0).getNickName());
			userVO.setUserEmail(userlist.get(0).getEmail());
			userVO.setId(userlist.get(0).getId());
			userVO.setEmailvfcode(userlist.get(0).getEmailvfcode());
			return userVO;
		}
	}
	
	/* modify user message
	 * this modify user IsActive status
	 * */
	public void updateUser(UserVO userVO){
		String whereClause="update t_user set isActive= '"+userVO.getIsActive()+"' where id="+userVO.getId();
		baseDAO.updateUserStatus(whereClause);
	}
	
	
}
