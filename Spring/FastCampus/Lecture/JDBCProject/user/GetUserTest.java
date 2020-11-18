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
		// JDBC API ����
		Connection conn = null; // ��ӵ���
//		Statement stmt = null; // �ڵ��� 
		PreparedStatement stmt = null; // �ڵ���(���) statement���� sqló���ӵ��� 5�� �̻� ������. ������ ���.
		ResultSet rs = null; // �˻� ��� ����
		
		
		try {
			// 2.Connection ��ü�� ȹ���Ѵ�.
			conn = JDBCUtil.getConnection(); // static �޼ҵ�ϱ� �ٷ� ������ �����ϴ�.
			
			// 3. Statement ��ü�� ȹ���Ѵ�.
			String sql = "select * from users where id = ? and password = ?";
			stmt = conn.prepareStatement(sql);// ��������� ���⿡ sql�� �� �׸��� �Ķ���Ͱ� ?�� �ٲ��. exe���⿡ sql�� ����
			
			// ? �Ķ���Ϳ� ��  ����
			stmt.setString(1, "admin");
			stmt.setString(2, "admin");  // ����� �޶����°� �ƴ϶� ������ �� ��������.
			
			// 4. SQL ������ DB�� �����Ѵ�.
			
			rs = stmt.executeQuery();
			
			// 5. �˻� ��� ó��
			if(rs.next()) {
				System.out.println("���̵� : " + rs.getString("ID"));
				System.out.println("��� : " + rs.getString("PASSWORD"));
				System.out.println("�̸� : " + rs.getString("NAME"));
				System.out.println("���� : " + rs.getString("ROLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}
}
