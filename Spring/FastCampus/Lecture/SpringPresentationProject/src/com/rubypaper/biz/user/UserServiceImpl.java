package com.rubypaper.biz.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 4. service ���� Ŭ����
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	// ���⿡ UserDao�� �Ҵ�ǵ��� @Autowired�� ���ش�.

	@Override
	public void insertUser(UserVO vo) {
		// TODO Auto-generated method stub
		userDAO.insertUser(vo);
	}

	@Override
	public UserVO getUser(UserVO vo) {
		// TODO Auto-generated method stub
		return userDAO.getUser(vo);
	}

}
