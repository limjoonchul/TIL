package com.rubypaper.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
//	@Autowired
//	private BoardDAO boardDAO; // jdbc기반
	
	@Autowired
	private BoardDAO boardDAO; // 스프링jdbc기반

	public BoardServiceImpl(){
		System.out.println("===> BoardServiceImpl  생성");
	}
	
//	public void setBoardDAO(BoardDAO boardDAO) {
//		this.boardDAO = boardDAO;
//	}



	@Override
	public void insertBoard(BoardVO vo) {
//		if(vo.getSeq() == 0) {
//			throw new IllegalArgumentException();
//			// 은행을 예로두면 계좌 금액이 음수인 상황이면 잔액이 부족하여 인출할 수 없습니다라든지 이런 처리를할 때 사용.
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
