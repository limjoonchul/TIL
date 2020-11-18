package com.rubypapper.biz.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteBoardTest {

	public static void main(String[] args){
		// 1. 글 삭제 기능 처리
		BoardDAO boardDAO = new BoardDAO();
		
		BoardVO vo = new BoardVO();
	
		vo.setSeq(1);
		
//	    boardDAO.deleteBoard(1);
						
		// 2. 글 목록 조회 기능 처리
		boardDAO.getBoardList();
		
		/*
		 * Connection conn = null; Statement stmt = null;
		 * 
		 * try { // 어느 지역으로 도로를 만들지 지역을 정함. // DriverManager.registerDriver(new
		 * org.h2.Driver()); Class.forName("org.h2.Driver");
		 * 
		 * // 도로를 만들어준다. String url = "jdbc:h2:tcp://localhost/~/test"; conn =
		 * DriverManager.getConnection(url, "sa", ""); //url, id, pw 중요!
		 * 
		 * // 도로에 자동차를 만들어준다. stmt = conn.createStatement();
		 * 
		 * // 자동차에 사람을 태워서 지역으로 보낸다. String sql = "delete board where seq = 2"; int cnt
		 * = stmt.executeUpdate(sql); System.out.println(cnt + "건의 데이터 처리 성공!");
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } finally { try { if (stmt !=
		 * null) { stmt.close(); } } catch (Exception e) { e.printStackTrace(); }
		 * finally { stmt = null; }
		 * 
		 * try { if (!conn.isClosed() && conn != null) { conn.close(); } } catch
		 * (Exception e) { e.printStackTrace(); } finally { conn = null; } }
		 */
		

	}

}
