package com.rubypapper.biz.board;

public class InertBoardTest {

	public static void main(String[] args) {
		
		// 1. �� ��� ��� ó��
		BoardDAO boardDAO = new BoardDAO();
		
		BoardVO vo = new BoardVO();
		vo.setTitle("VO �׽�Ʈ");
		vo.setTitle("�׽���");
		vo.setTitle("VO ����.....");
		
		boardDAO.insertBoard(vo); 
		// boardDao�� �ۼ� ���� �� ������
		// ����1. ó���� �� ���� ���� �������� ����Ʈ���� �𸥴�. �� �޼ҵ带 ������ �ǹ̸� �ľ��� �� �ִ�.
		// ����2. �Ķ������ Ÿ�԰� ������ ������ �����Ͱ� �߸����� ���� �ȴ�. 
		// �������߰� ������ ������ �߸� �����ε� �ȶ߰� �߸� ���� ������ �ȴ�.
		// 
		
		// vo�� ���� �� �������� ������ �ڵ尡 �þ�� ����� ����.
		
		// vo�� �ص� ������ �����. ������ Ÿ�԰� �������� �ٲ���� ��, ���ο� ������ �߰��� �� �� getter/setter�� �������Ѵ�.
		// ����������. �̰� �ڵ����� ó���� �� �ִ�.
		
		
		// 2. �� ��� �˻� ��� ó��
		boardDAO.getBoardList();
		
		
		
		
		
		
		/*
		 * Connection conn = null; PreparedStatement stmt = null;
		 * 
		 * try { conn = JDBCUtil.getConnection();
		 * 
		 * // 3. SQL ���� ��ü�� �����Ѵ�. String sql =
		 * "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0) + 1 from board), ?, ?, ?)"
		 * ; stmt = conn.prepareStatement(sql);
		 * 
		 * // �Ķ���� ���� stmt.setString(1, "JDBC ���� "); stmt.setString(2, "�׽���");
		 * stmt.setString(3, "JDBC ����...");
		 * 
		 * // 4. SQL ����
		 * 
		 * int cnt = stmt.executeUpdate(); System.out.println(cnt + "���� ������ ó�� ����!");
		 * 
		 * } catch (Exception e) { e.printStackTrace();
		 * 
		 * } finally { JDBCUtil.close(stmt, conn); }
		 */
		
		
	}

}
