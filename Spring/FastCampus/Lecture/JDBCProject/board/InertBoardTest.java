package com.rubypapper.biz.board;

public class InertBoardTest {

	public static void main(String[] args) {
		
		// 1. 글 등록 기능 처리
		BoardDAO boardDAO = new BoardDAO();
		
		BoardVO vo = new BoardVO();
		vo.setTitle("VO 테스트");
		vo.setTitle("테스터");
		vo.setTitle("VO 내용.....");
		
		boardDAO.insertBoard(vo); 
		// boardDao만 작성 했을 때 문제점
		// 문제1. 처음볼 때 들어가는 값이 제목인지 컨텐트인지 모른다. 이 메소드를 들어가봐야 의미를 파악할 수 있다.
		// 문제2. 파라미터의 타입과 순서만 맞으면 데이터가 잘못들어가도 들어가게 된다. 
		// 에러가뜨고 컴파일 오류가 뜨면 다행인데 안뜨고 잘못 들어가면 문제가 된다.
		// 
		
		// vo를 만든 후 가독성이 좋아짐 코드가 늘어나도 상관이 없다.
		
		// vo를 해도 문제가 생긴다. 데이터 타입과 변수명을 바꿔야할 때, 새로운 변수를 추가할 때 또 getter/setter를 만들어야한다.
		// 귀찮아진다. 이걸 자동으로 처리할 수 있다.
		
		
		// 2. 글 목록 검색 기능 처리
		boardDAO.getBoardList();
		
		
		
		
		
		
		/*
		 * Connection conn = null; PreparedStatement stmt = null;
		 * 
		 * try { conn = JDBCUtil.getConnection();
		 * 
		 * // 3. SQL 전달 객체를 생성한다. String sql =
		 * "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0) + 1 from board), ?, ?, ?)"
		 * ; stmt = conn.prepareStatement(sql);
		 * 
		 * // 파라미터 세팅 stmt.setString(1, "JDBC 제목 "); stmt.setString(2, "테스터");
		 * stmt.setString(3, "JDBC 내용...");
		 * 
		 * // 4. SQL 전송
		 * 
		 * int cnt = stmt.executeUpdate(); System.out.println(cnt + "건의 데이터 처리 성공!");
		 * 
		 * } catch (Exception e) { e.printStackTrace();
		 * 
		 * } finally { JDBCUtil.close(stmt, conn); }
		 */
		
		
	}

}
