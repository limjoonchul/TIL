package com.rubypaper.biz.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardServiceClient {
	public static void main(String[] args) {
//		BoardVO  vo = new BoardVO();
//		vo.setTitle("MyBatis 제목");
//		vo.setWriter("테스터");
//		vo.setContent("MyBatis 내용....");
		
		// 맵객체를 이용한 방법
		Map<String, Object> paramMap =new HashMap<String, Object>();
		paramMap.put("title","MyBatis 제목");
		paramMap.put("writer","테스터");
		paramMap.put("content","MyBatis 내용....");
		
		BoardVO  vo = new BoardVO();
		BoardDAOMyBaTis boardDAO = new BoardDAOMyBaTis();
		boardDAO.insertBoardMap(paramMap);
		
		List<Map<String, Object>> boardList = boardDAO.getBoardListMap(vo);
		for (Map<String, Object> board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}

}
