package com.rubypapper.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO boardDAO;
	
	// IoC 빈으로 등록할 때 사용
//	public BoardServiceImpl() {
//		System.out.println("===> BoardServiceImpl 객체 생성");
//	}
//	
//	public BoardServiceImpl(BoardDAO boardDAO) {
//		System.out.println("boardService 객체생성");
//		this.boardDAO = boardDAO;
//	}

	@Override
	public void insertBoard(BoardVO vo) {
		System.out.println("---> insertBoard 호출");
		boardDAO.insertBoard(vo);
	}

	@Override
	public void updateBoard(BoardVO vo) {
		System.out.println("---> updateBoard 호출");
		boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		System.out.println("---> deleteBoard 호출");
		boardDAO.deleteBoard(vo);
	}

	@Override
	public BoardVO getBoard(BoardVO vo) {
		if(vo.getSeq() == 0) {
			throw new IllegalArgumentException();
		}
		System.out.println("---> getBoard 호출");
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("---> getBoardList 호출");
		return boardDAO.getBoardList(vo);
	}

}
