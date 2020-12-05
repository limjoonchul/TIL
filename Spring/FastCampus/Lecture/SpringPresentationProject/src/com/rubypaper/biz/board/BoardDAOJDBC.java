package com.rubypaper.biz.board;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rubypaper.biz.common.JDBCUtil;

// 2. DAO(Data Access Object) 클래스
// @Repository
public class BoardDAOJDBC implements BoardDAO {
	// JDBC 관련 변수 선언
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	// BOARD 테이블 관련 SQL 명령어
	private static final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(MAX(SEQ),0)  + 1 FROM BOARD), ?, ?, ?)";
	private static final String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?, WRITER=?, CONTENT=? WHERE SEQ=?";
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
	
	
//	public BoardDAO(){
//		System.out.println("===> BoardDAO  생성");
//	}
	// BOARD 테이블 관련 CRUD 기능의 메소드
	// 글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC 기반으로 insertBoard() 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT); // sql이 완성되지 않았다 ?로 넣었기 때문에.
			
			stmt.setString(1, vo.getTitle()); // sql 세팅 값을 넣어줌.
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			
			
//			stmt.setString(1, title); // sql 세팅 값을 넣어줌.
//			stmt.setString(2, writer);
//			stmt.setString(3, content);
			
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC 기반으로 updateBoard() 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			
			stmt.setString(1, vo.getTitle()); // sql 세팅 값을 넣어줌.
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.setInt(4, vo.getSeq());
			
//			stmt.setString(1, title); // sql 세팅 값을 넣어줌.
//			stmt.setString(2, content);
//			stmt.setInt(3, seq);
			
			
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 글 삭제  나중에 유지보수할 때 int seq로작성했을때는 비밀번호도 필요하고그런 필요한 상황이 생겼을대 하나하나 넣어줘야 한다 그래서 vo를 통째로 넣는게 일관성 면에서도 좋다. 유지보수할 때 수정할 필요가 없어진다.
	// 지금은 메모리에 대한 걱정은 없어져서 vo를 통째로 넣어도 성능적으로 문제가 되지 않는다.
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC 기반으로 deleteBoard() 처리");
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
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> JDBC 기반으로 getBoard() 처리");
		BoardVO board = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			
			stmt.setInt(1, vo.getSeq());
			
			rs= stmt.executeQuery();
			
			if (rs.next()) {
//				System.out.println(rs.getInt("SEQ") + "번 게시글 내용 : " + rs.getString("CONTENT"));
				
				// 검색 결과 한건을 BoardVO 객체를 매핑한다.
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				// 검색 결과가 존재하는 경우 조회수를 증가시킨다.
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
	
	// 글 목록 검색
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC 기반으로 getBoardList() 처리");
		// 비어있는 리스트 컬렉션을 생성한다.
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
				// 검색 결과 한 row당 하나의 BoardVO 객체를 매핑한다.
//				System.out.println(rs.getInt("SEQ") + " : " + rs.getString("CONTENT"));
				
				BoardVO  board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				// 검색 결과가 매핑된 BoardVO 객체를 리스트 컬렉션에 등록한다.
				boardList.add(board);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		// 검색 결과가 저장된 리스트 컬렉션을 클라이언트로 리턴한다.
		return boardList;
	}
	
}
