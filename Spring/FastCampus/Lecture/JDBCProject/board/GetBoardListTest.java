package com.rubypapper.biz.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GetBoardListTest {

	public static void main(String[] args) {

		// 1. �� ��� ��ȸ ��� ó��
		BoardDAO boardDAO = new BoardDAO();
		
		// getBoardList() �޼ҵ尡 ������ �� ����� ���ϴ� ���·� ����Ѵ�.
		List<BoardVO> boardList =boardDAO.getBoardList();
		
		// select ����� �޼ҵ�� ���� returnŸ���� void�̸� �ȵȴ� ����� Ŭ���̾�Ʈ���� ������� �Ѵ�.
		// ��� ������ Ŭ���̾�Ʈ�� �����ؾ��� dao�� �����ϸ� �ȵȴ�!!! �߿�!!!!!
		// 1. CASE �� ����� ���
//		System.out.println("[ BOARD LIST ]");
//		for (BoardVO board : boardList) {
//			System.out.println("---->" + board.toString());
//		}
		
		// 2. CASE
		System.out.println("�˻��� �Խñ� �� : " + boardList.size());
		
//		boardDAO.getBoardList();
		/*
		 * Connection conn = null; Statement stmt = null; ResultSet rs = null;
		 * 
		 * try { DriverManager.registerDriver(new org.h2.Driver());
		 * 
		 * String url = "jdbc:h2:tcp://localhost/~/test"; conn =
		 * DriverManager.getConnection(url, "sa", "");
		 * 
		 * stmt = conn.createStatement();
		 * 
		 * String sql = "select * from board"; rs = stmt.executeQuery(sql);
		 * 
		 * System.out.println("[ BOARD LIST ]");
		 * 
		 * while(rs.next()) {
		 * 
		 * System.out.println("SEQ" + " : " + rs.getInt("SEQ"));
		 * System.out.println("TITLE" + " : " + rs.getString("TITLE"));
		 * System.out.println("WRITER" + " : " + rs.getString("WRITER"));
		 * System.out.println("CONTENT" + " : " + rs.getString("CONTENT"));
		 * System.out.println("REGDATE" + " : " + rs.getDate("REGDATE"));
		 * System.out.println("CNT" + " : " + rs.getInt("CNT")); System.out.println(); }
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); }
		 */

	}

}
