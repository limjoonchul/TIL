package com.rubypaper.biz.board;



import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.rubypaper.biz.util.SqlSessionFactoryBean;



// 2. DAO(Data Access Object) 클래스
// @Repository
public class BoardDAOMyBaTis{
	// sqlSession이 mybatis 프레임워크 객체다. mybatis도 내부적으로 컨테이너를 운용한다.
	// 따라서 sqlSession 객체는 mybatis컨테이너다. 프레임워크 == 컨테이너
	private SqlSession mybatis;
	
	public BoardDAOMyBaTis() {
		mybatis = SqlSessionFactoryBean.getSqlSessionInstance(); // 자바코드가 실행이되면 마이바티스 컨테이너가 생성된다고 보면 된다.
		// SqlSession인터페이스여서 이걸 구현한 클래스를 반환해준다.
	}

	// 글 등록
	public void insertBoard(BoardVO vo) { 
		System.out.println("===> MyBatis 기반으로 insertBoard() 처리");
		mybatis.insert("BoardDAO.insertBoard", vo); // sql의 id, 파라미터 객체
		mybatis.commit();
	}
	
	
	// 글 등록
	public void insertBoardMap(Map<String, Object> map) { 
			System.out.println("===> MyBatis 기반으로 insertBoardMap() 처리");
			mybatis.insert("BoardDAO.insertBoardMap", map); // sql의 id, 파라미터 객체
			mybatis.commit();
		}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> MyBatis 기반으로 updateBoard() 처리");
		mybatis.update("BoardDAO.updateBoard", vo); // sql의 id, 파라미터 객체
		mybatis.commit();
	}
	
	// 글 삭제  
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> MyBatis 기반으로 deleteBoard() 처리");
		mybatis.delete("BoardDAO.deleteBoard", vo);
		mybatis.commit();
	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> MyBatis 기반으로 getBoard() 처리");
		
		
		return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo); // 반환값이 오브젝트여서 형변환해야한다.
	}
	
	// 글 목록 검색
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> MyBatis 기반으로 getBoardList() 처리");

		return mybatis.selectList("BoardDAO.getBoardList");
	}
	
	// 글 목록 검색
	public List<Map<String, Object>> getBoardListMap(BoardVO vo) {
		System.out.println("===> MyBatis 기반으로 getBoardListMap() 처리");

		return mybatis.selectList("BoardDAO.getBoardListMap");
	}
	
	// 
	public int getTotalCount(BoardVO vo) {
		System.out.println("===> MyBatis 기반으로 getBoardList() 처리");

		return (int) mybatis.selectOne("BoardDAO.getTotalCount");
	}
	
}
