package com.rubypaper.biz.board;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
	public static void main(String[] args) {
//		String[] config = {"business-layer.xml", "business-transaction.xml"};
//		GenericXmlApplicationContext container = new GenericXmlApplicationContext(config);
		GenericXmlApplicationContext container = new GenericXmlApplicationContext("business-*.xml"); //�̷��Ե� ������.
		
		BoardService boardService = (BoardService) container.getBean("boardService");
		// Ŭ���̾�Ʈ�� getbeand���ϱ� ������ ���弭�������� id�� �ʿ��ϴ�.
		
		if(boardService != null) {
			System.out.println(boardService + " : Lookup ����");
		}
		
		BoardVO vo = new BoardVO();
		vo.setSeq(101);
		vo.setTitle("Spring ����");
		vo.setWriter("�׽���");
		vo.setContent("Spring ����");
		boardService.insertBoard(vo);
		
		
		
//		vo.setSeq(2);
//		BoardVO board = boardService.getBoard(vo);
//		System.out.println(board.getSeq() + "�� �� �� ����");
//		System.out.println("���� : " + board.getTitle());
//		System.out.println("�ۼ��� : " + board.getWriter());
//		System.out.println("����: " + board.getContent());
//		System.out.println("����� : " + board.getRegDate());
//		System.out.println("��ȸ�� : " + board.getCnt());
		
		
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
