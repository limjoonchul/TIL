package com.rubypapper.biz.board;

public class UpdateBoardTest {

	public static void main(String[] args) {
		
		// 1. 글 수정 기능 처리
		BoardDAO boardDAO = new BoardDAO();
		
		BoardVO vo = new BoardVO();
		vo.setContent("수정내용");
		vo.setTitle("수정 제목");
		vo.setSeq(1);
		
		
		boardDAO.updateBoard(vo);
				
		// 2. 글 상세 조회 기능 처리
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
		 * "update board set title = '제목수정', content = '내용 수정' where seq= 3"; int cnt =
		 * stmt.executeUpdate(sql); System.out.println(cnt + "건의 데이터 처리 성공!");
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
