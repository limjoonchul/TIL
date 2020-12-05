package com.rubypaper.biz.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardServiceClient {
	public static void main(String[] args) {
//		BoardVO  vo = new BoardVO();
//		vo.setTitle("MyBatis ����");
//		vo.setWriter("�׽���");
//		vo.setContent("MyBatis ����....");
		
		// �ʰ�ü�� �̿��� ���
		Map<String, Object> paramMap =new HashMap<String, Object>();
		paramMap.put("title","MyBatis ����");
		paramMap.put("writer","�׽���");
		paramMap.put("content","MyBatis ����....");
		
		BoardVO  vo = new BoardVO();
		BoardDAOMyBaTis boardDAO = new BoardDAOMyBaTis();
		boardDAO.insertBoardMap(paramMap);
		
		List<Map<String, Object>> boardList = boardDAO.getBoardListMap(vo);
		for (Map<String, Object> board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}

}
