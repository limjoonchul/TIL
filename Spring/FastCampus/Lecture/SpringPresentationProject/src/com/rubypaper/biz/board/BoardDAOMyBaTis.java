package com.rubypaper.biz.board;



import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;



// 2. DAO(Data Access Object) Ŭ����
// @Repository
public class BoardDAOMyBaTis implements BoardDAO{
	
	@Autowired
	private SqlSessionTemplate mybatis;

	// �� ���
	public void insertBoard(BoardVO vo) { 
		System.out.println("===> MyBatis ������� insertBoard() ó��");
		mybatis.insert("BoardDAO.insertBoard", vo); // sql�� id, �Ķ���� ��ü
//		mybatis.commit(); // �̰Ͷ����� ��ð� ° �������� ������ !!!!
	}
	
	// �� ����
	public void updateBoard(BoardVO vo) {
		System.out.println("===> MyBatis ������� updateBoard() ó��");
		mybatis.update("BoardDAO.updateBoard", vo); // sql�� id, �Ķ���� ��ü
	}
	
	// �� ����  
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> MyBatis ������� deleteBoard() ó��");
		mybatis.delete("BoardDAO.deleteBoard", vo);

	}
	
	// �� �� ��ȸ
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> MyBatis ������� getBoard() ó��");
		
		return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo); // ��ȯ���� ������Ʈ���� ����ȯ�ؾ��Ѵ�.
	}
	
	// �� ��� �˻�
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> MyBatis ������� getBoardList() ó��");

		return (List<BoardVO>) mybatis.selectList("BoardDAO.getBoardList",vo);
	}

}
