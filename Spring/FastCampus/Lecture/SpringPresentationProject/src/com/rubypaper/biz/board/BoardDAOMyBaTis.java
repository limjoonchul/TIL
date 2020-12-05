package com.rubypaper.biz.board;



import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;



// 2. DAO(Data Access Object) 클래스
// @Repository
public class BoardDAOMyBaTis implements BoardDAO{
	
	@Autowired
	private SqlSessionTemplate mybatis;

	// 글 등록
	public void insertBoard(BoardVO vo) { 
		System.out.println("===> MyBatis 기반으로 insertBoard() 처리");
		mybatis.insert("BoardDAO.insertBoard", vo); // sql의 id, 파라미터 객체
//		mybatis.commit(); // 이것때문에 몇시간 째 개지랄을 떨었네 !!!!
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> MyBatis 기반으로 updateBoard() 처리");
		mybatis.update("BoardDAO.updateBoard", vo); // sql의 id, 파라미터 객체
	}
	
	// 글 삭제  
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> MyBatis 기반으로 deleteBoard() 처리");
		mybatis.delete("BoardDAO.deleteBoard", vo);

	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> MyBatis 기반으로 getBoard() 처리");
		
		return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo); // 반환값이 오브젝트여서 형변환해야한다.
	}
	
	// 글 목록 검색
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> MyBatis 기반으로 getBoardList() 처리");

		return (List<BoardVO>) mybatis.selectList("BoardDAO.getBoardList",vo);
	}

}
