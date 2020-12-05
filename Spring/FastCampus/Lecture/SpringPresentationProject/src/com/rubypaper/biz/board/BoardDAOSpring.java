package com.rubypaper.biz.board;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

// 2. DAO(Data Access Object) 클래스
//@Repository
public class BoardDAOSpring implements BoardDAO {
	
	// 내가 안만든 클래스는 메모리에 올릴 수 있는 방법은 빈으로 등록해야 객체 생성이 가능하다. 따로 클래스를 만들어서 JdbcTemplate 이걸 상속해서 애노테이션을 달아도 되지만 이건 사용안함
	@Autowired // 이걸 붙였으면 이 타입의 객체가 여기에 자동으로 할당이된다 , 이 타입의 객체가 없으면 에러가 난다. 없어도 안되고 두개있어도 안됨 유니크해야됨
	private JdbcTemplate spring; // 이게 핵심 스프링프레임워크가 jdbctemplate객체를 지원해준다.

	
	// BOARD 테이블 관련 SQL 명령어
	private static final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(MAX(SEQ),0)  + 1 FROM BOARD), ?, ?, ?)";
	private static final String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
	private static final String BOARD_UPDATE_CNT = "UPDATE BOARD SET CNT=CNT+1 WHERE SEQ=?";
	private static final String BOARD_DELETE = "DELETE BOARD WHERE SEQ=?";
	private static final String BOARD_GET = "SELECT * FROM BOARD WHERE SEQ=?";
	private static final String BOARD_LIST_TITLE = "SELECT * FROM BOARD WHERE TITLE LIKE '%'||?||'%' ORDER BY SEQ DESC";
	private static final String BOARD_LIST_CONTENT = "SELECT * FROM BOARD WHERE CONTENT LIKE '%'||?||'%' ORDER BY SEQ DESC";

	/**
	 * board_list를 하나만해서 where ? like '%?%' 로 했다면 stmt.setString에서 (1,'TITLE') 로하면
	 * 쿼리문에는  WHERE 'TITLE' 이런식으로 들어간다. '' 이게 추가되서 들어가서 안된다.
	 * 이런 문제때문에 마이바티스라는 프레임워크가 등장한다. 다이나믹 SQL은 동적으로 쿼리를 변경시킬 수 있다.
	 * @param vo
	 */
	

	// BOARD 테이블 관련 CRUD 기능의 메소드
	// 글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring 기반으로 insertBoard() 처리");
		spring.update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent()); // 이 한줄이면 JDBCUTIL같은 클래스 안만들어도됨 스프링이 알아서 커넥션해준다.
		// 이 쿼리에 ?가 있는 것들은  vo.getTitle(), vo.getWriter(), vo.getContent()을 바로 넣어주면 된다.
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring 기반으로 updateBoard() 처리");
		spring.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring 기반으로 deleteBoard() 처리");
		spring.update(BOARD_DELETE, vo.getSeq());
	}
	
	// 글 상세 조회
	// 키 벨류
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring 기반으로 getBoard() 처리");
		Object[] args = {vo.getSeq()}; //get에 ?가 있어서 파라미터를 전달해야하니깐 만듬
		// queryForObject 메소드는 검색 결과가 하나인 쿼리를 위한 메소드다.
		// 만약 실행 결과가 두 건 이상이면 Exception 발생 두건 이상이 나올 것 같으면 query 메소드를 사용해야 한다.
		return spring.queryForObject(BOARD_GET, args, new BoardRowMapper()); // args에있는 값이 쿼리의 파라미터를 채워줌
		
	}
	
	// 글 목록 검색
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring 기반으로 getBoardList() 처리");
		Object[] args = {vo.getSearchKeyword()}; //get에 ?가 있어서 파라미터를 전달해야하니깐 만듬
	    return spring.query(BOARD_LIST_TITLE, args, new BoardRowMapper()); // 리스트안에 맵형태러 여러 정보들이 들어있을 것이다.
	
	}
	
}
