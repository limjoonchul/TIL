package com.rubypaper.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
//	@Autowired
//	private BoardDAO boardDAO; // jdbc���
	
	@Autowired
	private BoardDAO boardDAO; // ������jdbc���

	public BoardServiceImpl(){
		System.out.println("===> BoardServiceImpl  ����");
	}
	
//	public void setBoardDAO(BoardDAO boardDAO) {
//		this.boardDAO = boardDAO;
//	}



	@Override
	public void insertBoard(BoardVO vo) {
//		if(vo.getSeq() == 0) {
//			throw new IllegalArgumentException();
//			// ������ ���εθ� ���� �ݾ��� ������ ��Ȳ�̸� �ܾ��� �����Ͽ� ������ �� �����ϴٶ���� �̷� ó������ �� ���.
//		}
		boardDAO.insertBoard(vo);		

	}

	@Override
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);

	}

	@Override
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);

	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		
		return boardDAO.getBoardList(vo);
	}

}
