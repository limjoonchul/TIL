package com.rubypapper.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.util.JdbcUtils;

import com.rubypapper.biz.common.JDBCUtil;

public class GetUserTest {
	public static void main(String[] args) {
		// JDBC API 선언
		Connection conn = null; // 고속도로
//		Statement stmt = null; // 자동차 
		PreparedStatement stmt = null; // 자동차(페라리) statement보다 sql처리속도가 5배 이상 빠르다. 무조건 써라.
		ResultSet rs = null; // 검색 결과 저장
		
		
		try {
			// 2.Connection 객체를 획득한다.
			conn = JDBCUtil.getConnection(); // static 메소드니깐 바로 접근이 가능하다.
			
			// 3. Statement 객체를 획득한다.
			String sql = "select * from users where id = ? and password = ?";
			stmt = conn.prepareStatement(sql);// 프리페얼드는 여기에 sql이 들어감 그리고 파라미터가 ?로 바뀐다. exe여기에 sql이 빠짐
			
			// ? 파라미터에 값  설정
			stmt.setString(1, "admin");
			stmt.setString(2, "admin");  // 결과가 달라지는게 아니라 성능이 더 좋아진다.
			
			// 4. SQL 구문을 DB에 전송한다.
			
			rs = stmt.executeQuery();
			
			// 5. 검색 결과 처리
			if(rs.next()) {
				System.out.println("아이디 : " + rs.getString("ID"));
				System.out.println("비번 : " + rs.getString("PASSWORD"));
				System.out.println("이름 : " + rs.getString("NAME"));
				System.out.println("권한 : " + rs.getString("ROLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}
}
