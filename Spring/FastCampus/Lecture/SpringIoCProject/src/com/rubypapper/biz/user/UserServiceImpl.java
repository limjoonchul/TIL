package com.rubypapper.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	

	@Override
	public void insertUser(UserVO vo) {
		
		userDAO.insertUser(vo);
		
	}

	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}

}
