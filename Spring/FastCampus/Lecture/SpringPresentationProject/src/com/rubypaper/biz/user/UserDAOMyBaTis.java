package com.rubypaper.biz.user;



import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

// DAO(Data Access Object) 클래스
//@Repository
public class UserDAOMyBaTis implements UserDAO{
	
	@Autowired
	private SqlSessionTemplate myBatis;

	// USERS 테이블 관련 SQL 명령어
	private static final String USER_INSERT = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
	private static final String USER_GET = "SELECT * FROM USERS WHERE ID=? AND PASSWORD=?";
	
	// USERS 테이블 관련 CRUD 기능의 메소드
	// 회원 등록
	public void insertUser(UserVO vo) {
		System.out.println("===> MyBatis 기반 insertUser메소드 처리");
		myBatis.insert("UserDAO.insertUser",vo);
	}

	// 회원 상세 조회
	public UserVO getUser(UserVO vo) {
		System.out.println("===> MyBatis 기반 getUser메소드 처리");
		
		return (UserVO) myBatis.selectOne("UserDAO.getUser",vo);
	}

}
