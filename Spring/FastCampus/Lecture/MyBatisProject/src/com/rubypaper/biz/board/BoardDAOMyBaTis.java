package com.rubypaper.biz.board;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.rubypaper.biz.util.SqlSessionFactoryBean;



// 2. DAO(Data Access Object) Ŭ����
// @Repository
public class BoardDAOMyBaTis{
	// sqlSession�� mybatis �����ӿ�ũ ��ü��. mybatis�� ���������� �����̳ʸ� ����Ѵ�.
	// ���� sqlSession ��ü�� mybatis�����̳ʴ�. �����ӿ�ũ == �����̳�
	private SqlSession mybatis;
	
	public BoardDAOMyBaTis() {
		mybatis = SqlSessionFactoryBean.getSqlSessionInstance(); // �ڹ��ڵ尡 �����̵Ǹ� ���̹�Ƽ�� �����̳ʰ� �����ȴٰ� ���� �ȴ�.
		// SqlSession�������̽����� �̰� ������ Ŭ������ ��ȯ���ش�.
	}

	// �� ���
	public void insertBoard(BoardVO vo) { 
		System.out.println("===> MyBatis ������� insertBoard() ó��");
		mybatis.insert("BoardDAO.insertBoard", vo); // sql�� id, �Ķ���� ��ü
		mybatis.commit();
	}
	
	
	// �� ���
	public void insertBoardMap(Map<String, Object> map) { 
			System.out.println("===> MyBatis ������� insertBoardMap() ó��");
			mybatis.insert("BoardDAO.insertBoardMap", map); // sql�� id, �Ķ���� ��ü
			mybatis.commit();
		}
	
	// �� ����
	public void updateBoard(BoardVO vo) {
		System.out.println("===> MyBatis ������� updateBoard() ó��");
		mybatis.update("BoardDAO.updateBoard", vo); // sql�� id, �Ķ���� ��ü
		mybatis.commit();
	}
	
	// �� ����  
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> MyBatis ������� deleteBoard() ó��");
		mybatis.delete("BoardDAO.deleteBoard", vo);
		mybatis.commit();
	}
	
	// �� �� ��ȸ
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> MyBatis ������� getBoard() ó��");
		
		
		return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo); // ��ȯ���� ������Ʈ���� ����ȯ�ؾ��Ѵ�.
	}
	
	// �� ��� �˻�
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> MyBatis ������� getBoardList() ó��");

		return mybatis.selectList("BoardDAO.getBoardList");
	}
	
	// �� ��� �˻�
	public List<Map<String, Object>> getBoardListMap(BoardVO vo) {
		System.out.println("===> MyBatis ������� getBoardListMap() ó��");

		return mybatis.selectList("BoardDAO.getBoardListMap");
	}
	
	// 
	public int getTotalCount(BoardVO vo) {
		System.out.println("===> MyBatis ������� getBoardList() ó��");

		return (int) mybatis.selectOne("BoardDAO.getTotalCount");
	}
	
}
