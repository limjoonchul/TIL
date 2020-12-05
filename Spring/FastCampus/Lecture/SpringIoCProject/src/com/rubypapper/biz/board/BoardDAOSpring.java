package com.rubypapper.biz.board;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

// DAO(Data Access Object) 클래스
public class BoardDAOSpring implements BoardDAO {
	@Autowired
	private JdbcTemplate spring;
	
	// BOARD 테이블 관련 SQL 명령어
	private static final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(MAX(SEQ),0)  + 1 FROM BOARD), ?, ?, ?)";
	private static final String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
	private static final String BOARD_UPDATE_CNT = "UPDATE BOARD SET CNT=CNT+1 WHERE SEQ=?";
	private static final String BOARD_DELETE = "DELETE BOARD WHERE SEQ=?";
	private static final String BOARD_GET = "SELECT * FROM BOARD WHERE SEQ=?";
	private static final String BOARD_LIST_TITLE = "SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY SEQ DESC";
	private static final String BOARD_LIST_CONTENT = "SELECT * FROM BOARD WHERE CONTENT LIKE '%'||?||'%' ORDER BY SEQ DESC";

	// BOARD 테이블 관련 CRUD 기능의 메소드
	// 글 등록
	public void insertBoard(BoardVO vo) {
		spring.update(BOARD_INSERT, vo.getTitle(),vo.getWriter(), vo.getContent());
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		spring.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	// 글 삭제  나중에 유지보수할 때 int seq로작성했을때는 비밀번호도 필요하고그런 필요한 상황이 생겼을대 하나하나 넣어줘야 한다 그래서 vo를 통째로 넣는게 일관성 면에서도 좋다. 유지보수할 때 수정할 필요가 없어진다.
	// 지금은 메모리에 대한 걱정은 없어져서 vo를 통째로 넣어도 성능적으로 문제가 되지 않는다.
	public void deleteBoard(BoardVO vo) {
		spring.update(BOARD_DELETE, vo.getSeq());
	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring 기반으로 getBoard() 처리");
		Object[] args = {vo.getSeq()};
		return spring.queryForObject(BOARD_GET, args, new BoardRowMapper());	
	}
	
	// 글 목록 검색
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring 기반으로 getBoardList() 처리");
		Object[] args = {vo.getSearchKeyword()};
		return spring.query(BOARD_LIST_TITLE, args, new BoardRowMapper());
	}
	
}
