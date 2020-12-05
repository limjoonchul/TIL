package com.rubypapper.biz.user;

public interface UserDAO {

	// USERS 테이블 관련 CRUD 기능의 메소드
	// 회원 등록
	void insertUser(UserVO vo);
	//	
	//	// 글 수정
	//	public void updateBoard(BoardVO vo) {
	//		try {
	//			conn = JDBCUtil.getConnection();
	//			stmt = conn.prepareStatement(BOARD_UPDATE);
	//			
	//			stmt.setString(1, vo.getTitle()); // sql 세팅 값을 넣어줌.
	//			stmt.setString(2, vo.getWriter());
	//			stmt.setString(3, vo.getContent());
	//			
	////			stmt.setString(1, title); // sql 세팅 값을 넣어줌.
	////			stmt.setString(2, content);
	////			stmt.setInt(3, seq);
	//			
	//			
	//			stmt.executeUpdate();
	//		}catch (Exception e) {
	//			e.printStackTrace();
	//		} finally {
	//			JDBCUtil.close(stmt, conn);
	//		}
	//	}
	//	
	//	// 글 삭제  나중에 유지보수할 때 int seq로작성했을때는 비밀번호도 필요하고그런 필요한 상황이 생겼을대 하나하나 넣어줘야 한다 그래서 vo를 통째로 넣는게 일관성 면에서도 좋다. 유지보수할 때 수정할 필요가 없어진다.
	//	// 지금은 메모리에 대한 걱정은 없어져서 vo를 통째로 넣어도 성능적으로 문제가 되지 않는다.
	//	public void deleteBoard(BoardVO vo) {
	//		try {
	//			conn = JDBCUtil.getConnection();
	//			stmt = conn.prepareStatement(BOARD_DELETE);
	//			
	//			stmt.setInt(1, vo.getSeq());
	//			
	//			stmt.executeUpdate();
	//		}catch (Exception e) {
	//			e.printStackTrace();
	//		} finally {
	//			JDBCUtil.close(stmt, conn);
	//		}
	//	}

	// 회원 상세 조회
	UserVO getUser(UserVO vo);

}