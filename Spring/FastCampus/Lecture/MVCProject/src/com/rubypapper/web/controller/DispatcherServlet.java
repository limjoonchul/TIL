package com.rubypapper.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rubypapper.biz.board.BoardDAO;
import com.rubypapper.biz.board.BoardVO;
import com.rubypapper.biz.user.UserDAO;
import com.rubypapper.biz.user.UserVO;


public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DispatcherServlet() {
        System.out.println("===> DispatcherServlet 생성");
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 요청 path 정보를 추출한다.
		String uri = request.getRequestURI(); // 전체가 url이고 포트번호 뒤부터 부분이 uri이다.
		System.out.println(uri);
		String path = uri.substring(uri.lastIndexOf("/")); // 그 uri 맨 마지막 부분에 /뒤가 path
		System.out.println(path);
		
		// 2. 추출된 path에 따라 요청을 분기처리한다.
		if (path.equals("/login.do")) {
			
			System.out.println("로그인 기능 처리");
			 // 1. 사용자 입력 정보 추출
			   String id = request.getParameter("id"); // 작성한 jsp파일은 servlet파일 안에 service안에 들어간다  service에 request 매개변수가 있으니깐 사용 가능.
			   String password = request.getParameter("password");
			   
			   // 2. DB 연동 처리
			   UserVO vo = new UserVO();
			   vo.setId(id);
			   vo.setPassword(password);
			   
			   UserDAO userDAO = new UserDAO();
			   UserVO user = userDAO.getUser(vo);
			   
			   // 3. 화면 네비게이션
			   if(user != null){
				   
				   HttpSession session = request.getSession(); // jsp은 session을 바로 쓸 수 있지만 서블릿은 바로 쓸 수 없음 선언해줘야함.
				  
				   session.setAttribute("user", user); // 여기서 세션에 유저를 담고
		
				   
				   response.sendRedirect("getBoardList.do"); // jsp로가면안되고 do로 가야ㅗ된다.
			   } else{
				   response.sendRedirect("login.html");
			   }
			
		} else if (path.equals("/logout.do")) {
			
			System.out.println("로그아웃 기능 처리");
			// 로그아웃을 요청한 브라우저에 해당하는 세션 객체를 강제로 종료한다.
			HttpSession session = request.getSession();
			session.invalidate();
			
			response.sendRedirect("login.html");
			
		} else if (path.equals("/insertBoard.do")) {
			
			System.out.println("게시글 등록 기능 처리");

			// 1. 사용자 입력 정보 추출
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");

			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.insertBoard(vo);
			
			// 3. 화면 네비게이션
			response.sendRedirect("getBoardList.do"); // 글등록을 했으면 새로운 새션을 가져와서  갱신해야한다.
			// 글 수정 삭제 입력을 하면 갱신을 목록을 해서 꺼내야 한다.
			// 다시 로그인을해야 글목록을 다시 뿌릴 수 있다.
			
		} else if (path.equals("/updateBoard.do")) {
			
			System.out.println("게시글 수정 기능 처리");
			
			 String seq = request.getParameter("seq");
			 String title = request.getParameter("title");
			 String content = request.getParameter("content");

			   BoardVO vo = new BoardVO();
			   vo.setSeq(Integer.parseInt(seq));
			   vo.setTitle(title);
			   vo.setContent(content);

			   
			   BoardDAO boardDAO = new BoardDAO();
			   boardDAO.updateBoard(vo);
			   
			   response.sendRedirect("getBoardList.do");
			
		} else if (path.equals("/deleteBoard.do")) {
			
			System.out.println("게시글 삭제 기능 처리");
			
			  String seq = request.getParameter("seq");

			   BoardVO vo = new BoardVO();
			   vo.setSeq(Integer.parseInt(seq));
			   
			   BoardDAO boardDAO = new BoardDAO();
			   boardDAO.deleteBoard(vo);
			   
			   response.sendRedirect("getBoardList.do");
			
		} else if (path.equals("/getBoard.do")) {
			
			System.out.println("게시글 상세 조회 기능 처리");
			
			// Scriptlet
			String seq = request.getParameter("seq");

			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));

			BoardDAO boardDAO = new BoardDAO();
			BoardVO board = boardDAO.getBoard(vo);
			
			// 3. 검색 결과를 세션에 등록하고 jsp화면으로 이동한다.
			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			response.sendRedirect("getBoard.jsp");
			
		} else if (path.equals("/getBoardList.do")) {
			
			System.out.println("게시글 목록 기능 처리");
			
			 //1. 사용자 입력 정보 추출(검색 기능은 숙제...)
			// request.setCharacterEncoding("EUC-KR");	
			String searchCondition = request.getParameter("searchCondition"); 
			String searchKeyword = request.getParameter("searchKeyword");
			
			// null check  왜 널값을 체크하는지 제대로 못들음 알아보자.
			// 처음 로그인을 했을 때 이건 검색에 대한 파라미터를 가져오는 것이기 때문에 처음엔 값이 없다 
			// 그래서 nullpointException이 발생하기 때문에 null일때 값을 이렇게 설정해서 넣어주는 것이다. 이렇게 하면 모든게 나옴
			if(searchCondition == null) searchCondition = "TITLE";
			if(searchKeyword == null) searchKeyword = "";
			
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSerachCondition(searchCondition);
			vo.setSerachKeyword(searchKeyword);
			
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			// System.out.println(boardList);
			// db에서 글목록을 가져옴
			
			// 3. 검색 결과를 화면(JSP)에서 사용할 수 있도록 세션에 등록한다.
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList); // 글목록을 세션에 담음
			
			// 글 목록 화면(getBoardList.jsp)으로 이동한다.
			response.sendRedirect("getBoardList.jsp"); // 이걸 do로바꾸면 무한루프에 빠진다 세션에담고 다시 do로해서 이 조건문 실행...계속 됨.
			// 세션에 검색결과를 저장하면 미친짓이다? 이러면 안된다 이유는 세션은 서버메모리에 생ㅅ어되는 객체이다. 많은 사람들이 로그인 하면 세션이 메모리에 만개가 생성된다.
			// 그럼 세션이 커져 메모리를 많이쓴다/ 결론은 세션에 저장하면 안되고 request에 저장해야한다. request는 요청이들어오면 메모리에 request가 떴다가 추출하고 responser가 호출되면 사라짐
			// 세션은 서버가 떠있을 때 계속해서 떠있는다. 절대 검색결과는 세션에 담으면 안된다. 메모리 낭비.
			// 세션에는 가급적 아이디, 권한 정도를 저장한다.
			
		} else {
			
			System.out.println("URL을 다시 확인해주시기 바랍니다.");
		}
		

		
	}
	

}
