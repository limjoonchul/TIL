package com.rubypaper.biz.board;

import java.util.List;
// 3.SERVICE 인터페이스
public interface BoardService {

	/**
	 * board_list를 하나만해서 where ? like '%?%' 로 했다면 stmt.setString에서 (1,'TITLE') 로하면
	 * 쿼리문에는  WHERE 'TITLE' 이런식으로 들어간다. '' 이게 추가되서 들어가서 안된다.
	 * 이런 문제때문에 마이바티스라는 프레임워크가 등장한다. 다이나믹 SQL은 동적으로 쿼리를 변경시킬 수 있다.
	 * @param vo
	 */
	// BOARD 테이블 관련 CRUD 기능의 메소드
	// 글 등록
	void insertBoard(BoardVO vo);

	// 글 수정
	void updateBoard(BoardVO vo);

	// 글 삭제  나중에 유지보수할 때 int seq로작성했을때는 비밀번호도 필요하고그런 필요한 상황이 생겼을대 하나하나 넣어줘야 한다 그래서 vo를 통째로 넣는게 일관성 면에서도 좋다. 유지보수할 때 수정할 필요가 없어진다.
	// 지금은 메모리에 대한 걱정은 없어져서 vo를 통째로 넣어도 성능적으로 문제가 되지 않는다.
	void deleteBoard(BoardVO vo);

	// 글 상세 조회
	BoardVO getBoard(BoardVO vo);

	// 글 목록 검색
	List<BoardVO> getBoardList(BoardVO vo);

}