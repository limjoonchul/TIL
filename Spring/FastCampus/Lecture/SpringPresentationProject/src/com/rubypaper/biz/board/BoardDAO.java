package com.rubypaper.biz.board;

import java.util.List;

public interface BoardDAO {

	// �� ���
	void insertBoard(BoardVO vo);

	// �� ����
	void updateBoard(BoardVO vo);

	 
	
	void deleteBoard(BoardVO vo);

	// �� �� ��ȸ
	BoardVO getBoard(BoardVO vo);

	// �� ��� �˻�
	List<BoardVO> getBoardList(BoardVO vo);

}