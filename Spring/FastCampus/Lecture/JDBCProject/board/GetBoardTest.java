package com.rubypapper.biz.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetBoardTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1. �� �� ��ȸ ��� ó��
		BoardDAO boardDAO = new BoardDAO();
//        boardDAO.getBoard(1);
		BoardVO vo = new BoardVO();
		
		vo.setSeq(1);
		
		BoardVO board = boardDAO.getBoard(vo);
		
		System.out.println(board.getSeq() + "�� �Խñ��� �� ����");
		System.out.println("���� : " + board.getTitle());
		System.out.println("�ۼ��� : " + board.getWriter());
		System.out.println("���� : " + board.getContent());
		System.out.println("����� : " + board.getRegDate());
		System.out.println("��ȸ�� : " + board.getCnt());
		
		
/*		   Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;

	        // �� �ص� 1,2,3�� �Ȱ���.
	        try {
	            // 1. ����̹� ��ü�� �޸𸮿� �ε��Ѵ�.
//	            DriverManager.registerDriver(new org.h2.Driver()); // �ָ��̿��ϸ� �� Ŭ������ ��Ű���� ������ �ƿ� �����Ϻ��� �ȵȴ�.

	            Class.forName("org.h2.Driver"); // ���ڿ��� ����ϴ� ��� ����� ����. ����������Ʈ���� �̰� �� ���� �� �����ؼ�

	            // 2. Ŀ�ؼ��� ȹ���Ѵ�.
	            String url = "jdbc:h2:tcp://localhost/~/test";
	            conn = DriverManager.getConnection(url, "sa", "");// ���� ��ü�� ��ȯ�޴´�.


	            // 3. SQL���� ��ü(Statement)�� �����Ѵ�.
	            stmt = conn.createStatement();

	            // 4. SQL�� �����Ѵ�.
	            String sql = "select * from board where seq = 1";
	            rs = stmt.executeQuery(sql); // select ���� �޼ҵ� rs���� ��ȯ��. ��� ����

	            // 5. �˻� ��� ó��(SELECT ������ ���Ͽ� �ش�ȴ�.)
	            System.out.println("[ BOARD LIST ]");
	            if (rs.next()){ // ���ǽ����� �ϳ��� �������� ���༭ if������ �ٲ۴�.
	                System.out.println("�Խñ� �� ����");
	                System.out.println("��ȣ : " + rs.getInt("SEQ") );
	                System.out.println("���� : " + rs.getString("TITLE"));
	                System.out.println("�ۼ��� : " + rs.getString("WRITER"));
	                System.out.println("���� : " + rs.getString("CONTENT"));
	                System.out.println("����� : " + rs.getDate("REGDATE"));
	                System.out.println("��ȸ�� : " + rs.getInt("CNT"));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {

	            try{
	                if(rs != null)
	                    rs.close();
	            }catch (SQLException e){
	                e.printStackTrace();
	            }finally {
	                rs = null;
	            }

	            try{

	                if(stmt != null)
	                    stmt.close();
	            }catch (SQLException e){
	                e.printStackTrace();
	            }finally {
	                stmt = null;
	            }

	            try{

	                if(!conn.isClosed() && conn != null) // Ŀ�ؼ��� ������ �ƴ϶�� �������� �����ض�.
	                    conn.close();
	            }catch (SQLException e){
	                e.printStackTrace();
	            }finally {
	                conn = null;
	            }
              }
	        */
        
	}

}
