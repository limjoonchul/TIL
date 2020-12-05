package com.rubypapper.biz.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<UserVO> {

	@Override // RowMapper<UserVO>로 바꾸면 메소드의 반환타입도 UserVO로 바꿔야 함.
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserVO user = new UserVO();
		user.setId(rs.getString("ID"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setName(rs.getString("NAME"));
		user.setRole(rs.getString("ROLE"));
		return user;
	}

}
