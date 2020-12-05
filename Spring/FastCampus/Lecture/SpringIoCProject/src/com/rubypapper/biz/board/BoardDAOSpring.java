package com.rubypapper.biz.board;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

// DAO(Data Access Object) Ŭ����
public class BoardDAOSpring implements BoardDAO {
	@Autowired
	private JdbcTemplate spring;
	
	// BOARD ���̺� ���� SQL ��ɾ�
	private static final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(MAX(SEQ),0)  + 1 FROM BOARD), ?, ?, ?)";
	private static final String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
	private static final String BOARD_UPDATE_CNT = "UPDATE BOARD SET CNT=CNT+1 WHERE SEQ=?";
	private static final String BOARD_DELETE = "DELETE BOARD WHERE SEQ=?";
	private static final String BOARD_GET = "SELECT * FROM BOARD WHERE SEQ=?";
	private static final String BOARD_LIST_TITLE = "SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY SEQ DESC";
	private static final String BOARD_LIST_CONTENT = "SELECT * FROM BOARD WHERE CONTENT LIKE '%'||?||'%' ORDER BY SEQ DESC";

	// BOARD ���̺� ���� CRUD ����� �޼ҵ�
	// �� ���
	public void insertBoard(BoardVO vo) {
		spring.update(BOARD_INSERT, vo.getTitle(),vo.getWriter(), vo.getContent());
	}
	
	// �� ����
	public void updateBoard(BoardVO vo) {
		spring.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	// �� ����  ���߿� ���������� �� int seq���ۼ��������� ��й�ȣ�� �ʿ��ϰ�׷� �ʿ��� ��Ȳ�� �������� �ϳ��ϳ� �־���� �Ѵ� �׷��� vo�� ��°�� �ִ°� �ϰ��� �鿡���� ����. ���������� �� ������ �ʿ䰡 ��������.
	// ������ �޸𸮿� ���� ������ �������� vo�� ��°�� �־ ���������� ������ ���� �ʴ´�.
	public void deleteBoard(BoardVO vo) {
		spring.update(BOARD_DELETE, vo.getSeq());
	}
	
	// �� �� ��ȸ
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring ������� getBoard() ó��");
		Object[] args = {vo.getSeq()};
		return spring.queryForObject(BOARD_GET, args, new BoardRowMapper());	
	}
	
	// �� ��� �˻�
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring ������� getBoardList() ó��");
		Object[] args = {vo.getSearchKeyword()};
		return spring.query(BOARD_LIST_TITLE, args, new BoardRowMapper());
	}
	
}
