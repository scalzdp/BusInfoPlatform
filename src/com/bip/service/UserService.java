package com.bip.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bip.bean.User;
import com.bip.bean.UserProfile;
import com.bip.dao.IBaseDAO;
import com.bip.vo.ProfileVO;
import com.bip.vo.UserAndProfileVO;
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
		
		return 30;
	}
	
	/* get all users,at the server paging
	 * */
	public List<UserAndProfileVO> getAllUser(int page,int rows){
		List<UserAndProfileVO> userProfileVOs = new ArrayList<UserAndProfileVO>();
		List<User> users = baseDAO.getAllSelf(new User(), "t_user");
		for(User u:users){
			List<UserProfile> ups = baseDAO.queryListPageAndRows(new UserProfile(),page,rows, "t_userprofile", "and userID="+u.getId());
			if(ups.size()>0){
				for(UserProfile up:ups){
					UserAndProfileVO userProfileVO = new UserAndProfileVO();
					userProfileVO.setUserAge(u.getAge());
					userProfileVO.setUserBrithday(u.getBrithday());
					userProfileVO.setUserEmail(u.getEmail());
					userProfileVO.setUserId(u.getId());
					userProfileVO.setUserNickName(u.getNickName());
					userProfileVO.setUserPassword(u.getPassword());
					userProfileVO.setFrequenedLocation(up.getFrequenedLocation());
					userProfileVO.setHeadImg(up.getHeadImg());
					userProfileVO.setHobby(up.getHobby());
					userProfileVO.setUserProfileId(up.getId());
					userProfileVOs.add(userProfileVO);
				}
			}else{
				UserAndProfileVO userProfileVO = new UserAndProfileVO();
				userProfileVO.setUserAge(u.getAge());
				userProfileVO.setUserBrithday(u.getBrithday());
				userProfileVO.setUserEmail(u.getEmail());
				userProfileVO.setUserId(u.getId());
				userProfileVO.setUserNickName(u.getNickName());
				userProfileVO.setUserPassword(u.getPassword());
				userProfileVOs.add(userProfileVO);
			}
		}
		return userProfileVOs;
	}
	
	/* return the number of the users
	 * return the calculate the number of all the users
	 * */
	public int getAllUserCount(){
		return baseDAO.getAllSelf(new User(), "t_user").size();
	}
}
