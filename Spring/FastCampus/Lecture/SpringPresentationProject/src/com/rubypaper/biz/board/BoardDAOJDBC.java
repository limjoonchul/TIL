package com.rubypaper.biz.board;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rubypaper.biz.common.JDBCUtil;

// 2. DAO(Data Access Object) Ŭ����
// @Repository
public class BoardDAOJDBC implements BoardDAO {
	// JDBC ���� ���� ����
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	// BOARD ���̺� ���� SQL ��ɾ�
	private static final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(MAX(SEQ),0)  + 1 FROM BOARD), ?, ?, ?)";
	private static final String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?, WRITER=?, CONTENT=? WHERE SEQ=?";
	private static final String BOARD_UPDATE_CNT = "UPDATE BOARD SET CNT=CNT+1 WHERE SEQ=?";
	private static final String BOARD_DELETE = "DELETE BOARD WHERE SEQ=?";
	private static final String BOARD_GET = "SELECT * FROM BOARD WHERE SEQ=?";
	private static final String BOARD_LIST_TITLE = "SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY SEQ DESC";
	private static final String BOARD_LIST_CONTENT = "SELECT * FROM BOARD WHERE CONTENT LIKE '%'||?||'%' ORDER BY SEQ DESC";

	/**
	 * board_list�� �ϳ����ؼ� where ? like '%?%' �� �ߴٸ� stmt.setString���� (1,'TITLE') ���ϸ�
	 * ����������  WHERE 'TITLE' �̷������� ����. '' �̰� �߰��Ǽ� ���� �ȵȴ�.
	 * �̷� ���������� ���̹�Ƽ����� �����ӿ�ũ�� �����Ѵ�. ���̳��� SQL�� �������� ������ �����ų �� �ִ�.
	 * @param vo
	 */
	
	
//	public BoardDAO(){
//		System.out.println("===> BoardDAO  ����");
//	}
	// BOARD ���̺� ���� CRUD ����� �޼ҵ�
	// �� ���
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC ������� insertBoard() ó��");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT); // sql�� �ϼ����� �ʾҴ� ?�� �־��� ������.
			
			stmt.setString(1, vo.getTitle()); // sql ���� ���� �־���.
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			
			
//			stmt.setString(1, title); // sql ���� ���� �־���.
//			stmt.setString(2, writer);
//			stmt.setString(3, content);
			
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// �� ����
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC ������� updateBoard() ó��");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			
			stmt.setString(1, vo.getTitle()); // sql ���� ���� �־���.
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.setInt(4, vo.getSeq());
			
//			stmt.setString(1, title); // sql ���� ���� �־���.
//			stmt.setString(2, content);
//			stmt.setInt(3, seq);
			
			
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// �� ����  ���߿� ���������� �� int seq���ۼ��������� ��й�ȣ�� �ʿ��ϰ�׷� �ʿ��� ��Ȳ�� �������� �ϳ��ϳ� �־���� �Ѵ� �׷��� vo�� ��°�� �ִ°� �ϰ��� �鿡���� ����. ���������� �� ������ �ʿ䰡 ��������.
	// ������ �޸𸮿� ���� ������ �������� vo�� ��°�� �־ ���������� ������ ���� �ʴ´�.
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC ������� deleteBoard() ó��");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			
			stmt.setInt(1, vo.getSeq());
			
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// �� �� ��ȸ
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC ������� getBoard() ó��");
		BoardVO board = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			
			stmt.setInt(1, vo.getSeq());
			
			rs= stmt.executeQuery();
			
			if (rs.next()) {
//				System.out.println(rs.getInt("SEQ") + "�� �Խñ� ���� : " + rs.getString("CONTENT"));
				
				// �˻� ��� �Ѱ��� BoardVO ��ü�� �����Ѵ�.
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				// �˻� ����� �����ϴ� ��� ��ȸ���� ������Ų��.
				stmt = conn.prepareStatement(BOARD_UPDATE_CNT);
				stmt.setInt(1, vo.getSeq());
				stmt.executeUpdate();
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return board;
	}
	
	// �� ��� �˻�
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC ������� getBoardList() ó��");
		// ����ִ� ����Ʈ �÷����� �����Ѵ�.
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil.getConnection();
			if (vo.getSearchCondition().equals("TITLE")) {
				stmt = conn.prepareStatement(BOARD_LIST_TITLE);	
			} else if(vo.getSearchCondition().equals("CONTENT")) {
				stmt = conn.prepareStatement(BOARD_LIST_CONTENT);
			}
			
			stmt.setString(1, vo.getSearchKeyword());
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				// �˻� ��� �� row�� �ϳ��� BoardVO ��ü�� �����Ѵ�.
//				System.out.println(rs.getInt("SEQ") + " : " + rs.getString("CONTENT"));
				
				BoardVO  board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				// �˻� ����� ���ε� BoardVO ��ü�� ����Ʈ �÷��ǿ� ����Ѵ�.
				boardList.add(board);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		// �˻� ����� ����� ����Ʈ �÷����� Ŭ���̾�Ʈ�� �����Ѵ�.
		return boardList;
	}
	
}
