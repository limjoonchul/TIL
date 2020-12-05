package com.rubypaper.web.controller.board;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.rubypaper.biz.board.BoardService;
import com.rubypaper.biz.board.BoardVO;

// @SessionAttributes를 이용하면 특정 이름으로 Model에 저장된 데이터를 세션에도 등록되도록 한다.
// 따라서 getBoard 메소드에서 Model에 "board"라는 이름으로 BoardVO 객체를 저장하면 
// 세션에도 "board" 라는 이름으로 BoardVO 객체가 등록되는 것이다.
// 배열형태로도 여러 개 등록 할 수 있다.
@SessionAttributes("board")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService; // 이타입의 객체가 메모리에 없으면 애러 있으면 객체의 주소를 할당한다.
	
	
	@RequestMapping("/dataTransform.do")
	@ResponseBody // http 응답 프로토콜 body에 Json으로 변환된 글 목록을 출력하도록 하는 설정 어떤디바이스에서 검색을하든 url만 공개해주면 개발하려는 시스템에서  글목록이 json형태로 들어온다
	// 이json형태로 들어온 값을 이용해서 화면을 만드는 사람들이 데이터를 골라서 뿌려주면 되는것이다. 중요한건 어느 언어로 개발하든 다 상관없이 json데이터로 처리할 수 있다.
	public List<BoardVO> dataTransform(BoardVO vo){
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		return boardService.getBoardList(vo);
	}
	
	// 글등록 화면 이동
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.GET)
	public String insertBoardView(BoardVO vo) {
		// 매개변수로 받은 vo 객체는 자동으로 request 내장 객체에 등록된다.
		// 따라서 최종적으로 실행되는 화면(jsp)에서 el을 이용하여 값을 뿌릴 수 있다.
		vo.setTitle("200자 미만으로..");
		vo.setWriter("테스터");
		vo.setContent("2000자 미만으로 작성해주세요.");
		return "insertBoard"; // 포워드: 하고 띄워쓰기하면 안됨~

		// jsp에ㅐ서 el로 해주면 boardVO로 받는다.
	}
	// 똑같은 insetboard.do 로 왔을대 get/post방식이냐에 따라서 다르게 동작시킴.
	// 메소드가 있고 없고의 차이때문에 오버로딩으로 식별은 되지만 이름을 다르게 해줌.

	// 글등록
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(BoardVO vo ) throws Exception {
		
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) // 파일 업로드 정보가 하나라도 있다면...
			uploadFile.transferTo(new File("C:/DEV/upload_files/" + uploadFile.getOriginalFilename()));
		
		// 글 등록 기능 처리
		boardService.insertBoard(vo);	
		return "forward:getBoardList.do";
	}

	// 글 상세
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println("게시글 상세 조회 기능 처리");
		BoardVO board = boardService.getBoard(vo);
//		session.setAttribute("board", board);
		System.out.println("글 상세 조회에서의 BoardVO  객체 정보");
		System.out.println(vo.toString());
		model.addAttribute("board", board);

		return "getBoard";
	}

	// 글 목록
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo,Model model) {
		System.out.println("게시글 목록 기능 처리");

		if (vo.getSearchCondition() == null)
			vo.setSearchCondition("TITLE");
		if (vo.getSearchKeyword() == null)
			vo.setSearchKeyword("");
		// 절대 검색 결과는 세션에 저장해서는 안된다. 검색 결과는 request에 등록해야 한다!!
		// ModelAndView나 Model 객체에 검색 결과를 등록하면 자동으로 request에 등록해준다. 중요한 개념이다!!!! 
		// 그래야 세션을 남용하지 않는다.
//		session.setAttribute("boardList", boardDAO.getBoardList(vo)); // 글목록을 세션에 담음
//		session.setAttribute("search", vo);
		
		model.addAttribute("boardList", boardService.getBoardList(vo));
		model.addAttribute("search", vo);

		return "getBoardList";

	}
	
	// 글 수정
	// @ModelAttribute는 세션에 "board"라는 이름으로 데이터가 등록되어있다면 그 객체를 vo변수에 바인딩해라 라는 의미이다. 없으면 패스
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {

		System.out.println("게시글 수정 기능 처리");
		System.out.println("수정에서의 BoardVO  객체 정보");
		System.out.println(vo.toString());
		// Model에 저장한 데이터는 자동으로 request에 등록된다. 세션이 아니라
		// 
		boardService.updateBoard(vo);
		return "forward:getBoardList.do";

	}

	// 글 삭제

	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) { // 나중에 매개변수가늘어나고 줄어들 수 있기 때문에 객체형식으로 넣어주는게 좋다.
		System.out.println("게시글 삭제 기능 처리");
		boardService.deleteBoard(vo);
		return "forward:getBoardList.do";

	}

	/*
	 * // 글 삭제
	 * 
	 * @RequestMapping("/deleteBoard.do") public String
	 * deleteBoard(@RequestParam(value="seq", defaultValue="1", required=true) int
	 * boardSeq, BoardDAOJDBC boardDAO) { // 기본형도 넣을 수 있다. 매개변수이름만 똑같다면 가능. 데이터타입까지도
	 * 내부적으로 처리해줌 System.out.println(boardSeq + "번 글 삭제 처리"); //
	 * boardDAO.deleteBoard(vo);
	 * 
	 * return "forward:getBoardList.do";
	 * 
	 * }
	 */

}
