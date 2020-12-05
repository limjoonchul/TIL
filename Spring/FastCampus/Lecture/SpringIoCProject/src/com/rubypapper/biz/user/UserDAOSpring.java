package com.rubypapper.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.rubypapper.biz.common.JDBCUtil;

public class UserDAOSpring implements UserDAO{
	
	@Autowired
	private JdbcTemplate spring;
	

	// USERS ���̺� ���� SQL ��ɾ�
	private static final String USER_INSERT = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
	private static final String USER_GET = "SELECT * FROM USERS WHERE ID=? AND PASSWORD=?";
		
	// USERS ���̺� ���� CRUD ����� �޼ҵ�
	// ȸ�� ���
	public void insertUser(UserVO vo) {
		Object[] args = {vo.getId(), vo.getPassword(), vo.getName(), vo.getRole()};
		System.out.println("---> spring ��� insertUser() ȣ��");
		spring.update(USER_INSERT, args);
	}
	
	// ȸ�� �� ��ȸ
	public UserVO getUser(UserVO vo) {
		Object[] args = {vo.getId(), vo.getPassword()};
		System.out.println("---> spring ��� getUser() ȣ��");
		return spring.queryForObject(USER_GET, args, new UserRowMapper());
	}
}
