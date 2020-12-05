package com.rubypaper.biz.board;

import java.util.List;
// 3.SERVICE �������̽�
public interface BoardService {

	/**
	 * board_list�� �ϳ����ؼ� where ? like '%?%' �� �ߴٸ� stmt.setString���� (1,'TITLE') ���ϸ�
	 * ����������  WHERE 'TITLE' �̷������� ����. '' �̰� �߰��Ǽ� ���� �ȵȴ�.
	 * �̷� ���������� ���̹�Ƽ����� �����ӿ�ũ�� �����Ѵ�. ���̳��� SQL�� �������� ������ �����ų �� �ִ�.
	 * @param vo
	 */
	// BOARD ���̺� ���� CRUD ����� �޼ҵ�
	// �� ���
	void insertBoard(BoardVO vo);

	// �� ����
	void updateBoard(BoardVO vo);

	// �� ����  ���߿� ���������� �� int seq���ۼ��������� ��й�ȣ�� �ʿ��ϰ�׷� �ʿ��� ��Ȳ�� �������� �ϳ��ϳ� �־���� �Ѵ� �׷��� vo�� ��°�� �ִ°� �ϰ��� �鿡���� ����. ���������� �� ������ �ʿ䰡 ��������.
	// ������ �޸𸮿� ���� ������ �������� vo�� ��°�� �־ ���������� ������ ���� �ʴ´�.
	void deleteBoard(BoardVO vo);

	// �� �� ��ȸ
	BoardVO getBoard(BoardVO vo);

	// �� ��� �˻�
	List<BoardVO> getBoardList(BoardVO vo);

}