package com.rubypaper.biz.user;

// service �������̽�
public interface UserService {

	// USERS ���̺� ���� CRUD ����� �޼ҵ�
	// ȸ�� ���
	void insertUser(UserVO vo);

	// ȸ�� �� ��ȸ
	UserVO getUser(UserVO vo);

}