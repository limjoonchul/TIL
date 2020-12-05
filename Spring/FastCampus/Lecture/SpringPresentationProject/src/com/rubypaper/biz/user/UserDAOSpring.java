package com.rubypaper.biz.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

// DAO(Data Access Object) 클래스
//@Repository
public class UserDAOSpring implements UserDAO {
	
	@Autowired
	private JdbcTemplate spring;

	// USERS 테이블 관련 SQL 명령어
	private static final String USER_INSERT = "INSERT INTO USERS VALUES(?, ?, ?, ?)";
	private static final String USER_GET = "SELECT * FROM USERS WHERE ID=? AND PASSWORD=?";
	
	
	// USERS 테이블 관련 CRUD 기능의 메소드
	// 회원 등록
	public void insertUser(UserVO vo) {
		System.out.println("===> Spring 기반 insertUser메소드 처리");
		Object[] args = {vo.getId(), vo.getPassword(), vo.getName(), vo.getRole()}; // ?의 파라미터를 채울 값들
		spring.update(USER_INSERT, args);
	}

	// 회원 상세 조회
	public UserVO getUser(UserVO vo) {
		System.out.println("===> Spring 기반 getUser메소드 처리");
		Object[] args = {vo.getId(), vo.getPassword()};
		UserVO user = spring.queryForObject(USER_GET, args, new UserRowMapper());
		// 맵컬렉션을 리턴하기 싫으면 uservo로 리턴하고싶으면 uservo로 매핑해주는 UserRowMapper를 만들어야한다.
		// USER_GET, args를 통해서 select된 row를 UserRowMapper로 매핑해준다.queryForObject이 메소드가
		
		return user;
	}

}
