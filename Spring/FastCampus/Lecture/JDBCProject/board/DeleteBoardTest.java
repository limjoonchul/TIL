package com.rubypapper.biz.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteBoardTest {

	public static void main(String[] args){
		// 1. �� ���� ��� ó��
		BoardDAO boardDAO = new BoardDAO();
		
		BoardVO vo = new BoardVO();
	
		vo.setSeq(1);
		
//	    boardDAO.deleteBoard(1);
						
		// 2. �� ��� ��ȸ ��� ó��
		boardDAO.getBoardList();
		
		/*
		 * Connection conn = null; Statement stmt = null;
		 * 
		 * try { // ��� �������� ���θ� ������ ������ ����. // DriverManager.registerDriver(new
		 * org.h2.Driver()); Class.forName("org.h2.Driver");
		 * 
		 * // ���θ� ������ش�. String url = "jdbc:h2:tcp://localhost/~/test"; conn =
		 * DriverManager.getConnection(url, "sa", ""); //url, id, pw �߿�!
		 * 
		 * // ���ο� �ڵ����� ������ش�. stmt = conn.createStatement();
		 * 
		 * // �ڵ����� ����� �¿��� �������� ������. String sql = "delete board where seq = 2"; int cnt
		 * = stmt.executeUpdate(sql); System.out.println(cnt + "���� ������ ó�� ����!");
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
