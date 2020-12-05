package com.rubypaper.biz.board;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

// 2. DAO(Data Access Object) Ŭ����
//@Repository
public class BoardDAOSpring implements BoardDAO {
	
	// ���� �ȸ��� Ŭ������ �޸𸮿� �ø� �� �ִ� ����� ������ ����ؾ� ��ü ������ �����ϴ�. ���� Ŭ������ ���� JdbcTemplate �̰� ����ؼ� �ֳ����̼��� �޾Ƶ� ������ �̰� ������
	@Autowired // �̰� �ٿ����� �� Ÿ���� ��ü�� ���⿡ �ڵ����� �Ҵ��̵ȴ� , �� Ÿ���� ��ü�� ������ ������ ����. ��� �ȵǰ� �ΰ��־ �ȵ� ����ũ�ؾߵ�
	private JdbcTemplate spring; // �̰� �ٽ� �����������ӿ�ũ�� jdbctemplate��ü�� �������ش�.

	
	// BOARD ���̺� ���� SQL ��ɾ�
	private static final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(MAX(SEQ),0)  + 1 FROM BOARD), ?, ?, ?)";
	private static final String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
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
	

	// BOARD ���̺� ���� CRUD ����� �޼ҵ�
	// �� ���
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring ������� insertBoard() ó��");
		spring.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent()); // �� �����̸� JDBCUTIL���� Ŭ���� �ȸ����� �������� �˾Ƽ� Ŀ�ؼ����ش�.
		// �� ������ ?�� �ִ� �͵���  vo.getTitle(), vo.getWriter(), vo.getContent()�� �ٷ� �־��ָ� �ȴ�.
	}
	
	// �� ����
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring ������� updateBoard() ó��");
		spring.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring ������� deleteBoard() ó��");
		spring.update(BOARD_DELETE, vo.getSeq());
	}
	
	// �� �� ��ȸ
	// Ű ����
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring ������� getBoard() ó��");
		Object[] args = {vo.getSeq()}; //get�� ?�� �־ �Ķ���͸� �����ؾ��ϴϱ� ����
		// queryForObject �޼ҵ�� �˻� ����� �ϳ��� ������ ���� �޼ҵ��.
		// ���� ���� ����� �� �� �̻��̸� Exception �߻� �ΰ� �̻��� ���� �� ������ query �޼ҵ带 ����ؾ� �Ѵ�.
		return spring.queryForObject(BOARD_GET, args, new BoardRowMapper()); // args���ִ� ���� ������ �Ķ���͸� ä����
		
	}
	
	// �� ��� �˻�
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring ������� getBoardList() ó��");
		Object[] args = {vo.getSearchKeyword()}; //get�� ?�� �־ �Ķ���͸� �����ؾ��ϴϱ� ����
	    return spring.query(BOARD_LIST_TITLE, args, new BoardRowMapper()); // ����Ʈ�ȿ� �����·� ���� �������� ������� ���̴�.
	
	}
	
}
