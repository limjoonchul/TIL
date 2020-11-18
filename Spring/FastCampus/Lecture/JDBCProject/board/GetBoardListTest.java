package com.rubypapper.biz.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GetBoardListTest {

	public static void main(String[] args) {

		// 1. 글 목록 조회 기능 처리
		BoardDAO boardDAO = new BoardDAO();
		
		// getBoardList() 메소드가 리턴한 글 목록을 원하는 형태로 사용한다.
		List<BoardVO> boardList =boardDAO.getBoardList();
		
		// select 기능의 메소드는 절대 return타입이 void이면 안된다 결과를 클라이언트에게 보내줘야 한다.
		// 어떻게 쓸지는 클라이언트가 결정해야지 dao가 결정하면 안된다!!! 중요!!!!!
		// 1. CASE 글 목록을 출력
//		System.out.println("[ BOARD LIST ]");
//		for (BoardVO board : boardList) {
//			System.out.println("---->" + board.toString());
//		}
		
		// 2. CASE
		System.out.println("검색된 게시글 수 : " + boardList.size());
		
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
