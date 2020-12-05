package com.rubypaper.biz.board;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
	public static void main(String[] args) {
//		String[] config = {"business-layer.xml", "business-transaction.xml"};
//		GenericXmlApplicationContext container = new GenericXmlApplicationContext(config);
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("business-*.xml"); //이렇게도 가능함.
		
		BoardService boardService = (BoardService) container.getBean("boardService");
		// 클라이언트가 getbeandㄹ하기 때문에 보드서비스임플은 id가 필요하다.
		
		if(boardService != null) {
			System.out.println(boardService + " : Lookup 성공");
		}
		
		BoardVO vo = new BoardVO();
		vo.setSeq(101);
		vo.setTitle("Spring 제목");
		vo.setWriter("테스터");
		vo.setContent("Spring 내용");
		boardService.insertBoard(vo);
		
		
		
//		vo.setSeq(2);
//		BoardVO board = boardService.getBoard(vo);
//		System.out.println(board.getSeq() + "번 글 상세 정보");
//		System.out.println("제목 : " + board.getTitle());
//		System.out.println("작성자 : " + board.getWriter());
//		System.out.println("내용: " + board.getContent());
//		System.out.println("등록일 : " + board.getRegDate());
//		System.out.println("조회수 : " + board.getCnt());
		
		
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		
		System.out.println("[BOARD LIST]");
		for (BoardVO board : boardList) {
			System.out.println("---> " + board.toString());
		}

		container.close();
		
	}
}
