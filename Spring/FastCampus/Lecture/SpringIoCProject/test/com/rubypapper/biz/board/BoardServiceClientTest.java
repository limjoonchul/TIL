package com.rubypapper.biz.board;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClientTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("business-layer.xml");
		
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		if (boardService != null) {
			System.out.println("boardService Lookup 성공!");
		}

		BoardVO vo = new BoardVO();
//		vo.setTitle("spring 제목");
//		vo.setWriter("테스터");
//		vo.setContent("spring 내용");
		
		//boardService.insertBoard(vo);
		
//		vo.setSeq(2);
//		boardService.getBoard(vo);	
		
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		
		for (BoardVO board : boardList) {
			System.out.println("SEQ : "+board.getSeq());
			System.out.println("TITLE : "+board.getTitle());
			System.out.println("Writer : "+board.getWriter());
			System.out.println("Content : "+board.getContent());
		}
		
		container.close();
		
	}

}
