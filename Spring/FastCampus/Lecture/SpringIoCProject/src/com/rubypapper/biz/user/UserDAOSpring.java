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
	

	// USERS 테이블 관련 SQL 명령어
	private static final String USER_INSERT = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
	private static final String USER_GET = "SELECT * FROM USERS WHERE ID=? AND PASSWORD=?";
		
	// USERS 테이블 관련 CRUD 기능의 메소드
	// 회원 등록
	public void insertUser(UserVO vo) {
		Object[] args = {vo.getId(), vo.getPassword(), vo.getName(), vo.getRole()};
		System.out.println("---> spring 기반 insertUser() 호출");
		spring.update(USER_INSERT, args);
	}
	
	// 회원 상세 조회
	public UserVO getUser(UserVO vo) {
		Object[] args = {vo.getId(), vo.getPassword()};
		System.out.println("---> spring 기반 getUser() 호출");
		return spring.queryForObject(USER_GET, args, new UserRowMapper());
	}
}
