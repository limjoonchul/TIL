package com.rubypapper.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO boardDAO;
	
	// IoC ������ ����� �� ���
//	public BoardServiceImpl() {
//		System.out.println("===> BoardServiceImpl ��ü ����");
//	}
//	
//	public BoardServiceImpl(BoardDAO boardDAO) {
//		System.out.println("boardService ��ü����");
//		this.boardDAO = boardDAO;
//	}

	@Override
	public void insertBoard(BoardVO vo) {
		System.out.println("---> insertBoard ȣ��");
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		System.out.println("---> updateBoard ȣ��");
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		System.out.println("---> deleteBoard ȣ��");
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		if(vo.getSeq() == 0) {
			throw new IllegalArgumentException();
		}
		System.out.println("---> getBoard ȣ��");
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("---> getBoardList ȣ��");
		return boardDAO.getBoardList(vo);
	}

}
