package com.rubypapper.biz.user;

public interface UserDAO {

	// USERS ���̺� ���� CRUD ����� �޼ҵ�
	// ȸ�� ���
	void insertUser(UserVO vo);
	//	
	//	// �� ����
	//	public void updateBoard(BoardVO vo) {
	//		try {
	//			conn = JDBCUtil.getConnection();
	//			stmt = conn.prepareStatement(BOARD_UPDATE);
	//			
	//			stmt.setString(1, vo.getTitle()); // sql ���� ���� �־���.
	//			stmt.setString(2, vo.getWriter());
	//			stmt.setString(3, vo.getContent());
	//			
	////			stmt.setString(1, title); // sql ���� ���� �־���.
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
	//	// �� ����  ���߿� ���������� �� int seq���ۼ��������� ��й�ȣ�� �ʿ��ϰ�׷� �ʿ��� ��Ȳ�� �������� �ϳ��ϳ� �־���� �Ѵ� �׷��� vo�� ��°�� �ִ°� �ϰ��� �鿡���� ����. ���������� �� ������ �ʿ䰡 ��������.
	//	// ������ �޸𸮿� ���� ������ �������� vo�� ��°�� �־ ���������� ������ ���� �ʴ´�.
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

	// ȸ�� �� ��ȸ
	UserVO getUser(UserVO vo);

}