package com.rubypaper.biz.user;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

// DAO(Data Access Object) Ŭ����
//@Repository
public class UserDAOMyBaTis implements UserDAO{
	
	@Autowired
	private SqlSessionTemplate myBatis;

	// USERS ���̺� ���� SQL ��ɾ�
	private static final String USER_INSERT = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
	private static final String USER_GET = "SELECT * FROM USERS WHERE ID=? AND PASSWORD=?";
	
	// USERS ���̺� ���� CRUD ����� �޼ҵ�
	// ȸ�� ���
	public void insertUser(UserVO vo) {
		System.out.println("===> MyBatis ��� insertUser�޼ҵ� ó��");
		myBatis.insert("UserDAO.insertUser",vo);
	}

	// ȸ�� �� ��ȸ
	public UserVO getUser(UserVO vo) {
		System.out.println("===> MyBatis ��� getUser�޼ҵ� ó��");
		
		return (UserVO) myBatis.selectOne("UserDAO.getUser",vo);
	}

}
