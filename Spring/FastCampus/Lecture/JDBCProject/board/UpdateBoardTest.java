package com.rubypapper.biz.board;

public class UpdateBoardTest {

	public static void main(String[] args) {
		
		// 1. �� ���� ��� ó��
		BoardDAO boardDAO = new BoardDAO();
		
		BoardVO vo = new BoardVO();
		vo.setContent("��������");
		vo.setTitle("���� ����");
		vo.setSeq(1);
		
		
		boardDAO.updateBoard(vo);
				
		// 2. �� �� ��ȸ ��� ó��
		boardDAO.getBoard(vo);
		
		/*
		 * Connection conn = null; Statement stmt = null;
		 * 
		 * try { // DriverManager.deregisterDriver(new org.h2.Driver());
		 * Class.forName("org.h2.Driver");
		 * 
		 * String url = "jdbc:h2:tcp://localhost/~/test"; conn =
		 * DriverManager.getConnection(url, "sa", "");
		 * 
		 * stmt = conn.createStatement();
		 * 
		 * String sql =
		 * "update board set title = '�������', content = '���� ����' where seq= 3"; int cnt =
		 * stmt.executeUpdate(sql); System.out.println(cnt + "���� ������ ó�� ����!");
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
