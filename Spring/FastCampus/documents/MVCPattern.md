# MVC 패턴
## MVC 패턴 이란?
* Model, View, Controller 이 세가지 구성요소로 프로그래밍이 이루어지는 패러다임으로
클라이언트의 요청이 오면 Controller에서 값을 받아서 처리하고 Model에 담아서 View로 전달해서 화면에 뿌리는 형태이다.

* 지금까지 Spring을 나가기전에 스프링의 기본이 되는 Servlet/Jsp를 이용해서 예제를 진행했었다.
JSP에 비즈니스 로직과 디자인을 한 곳에 뒤섞여 작성해 놨었다.

### Model 1 구조
* 브라우저가 있고, 서버가 있을 때 서버 안에 JSP(View + 비즈니스 로직)와 JavaBeans(Model)가 있는데 이런 구조가 Model 1 아키텍쳐이다. (비즈니스로직과 디자인이 하나의 파일에 합쳐진 구조)
  * Model 1 아키텍쳐는 90년대 말부터 지금가지 쓰고있는 웹 아키텍쳐이다.
    * 아키텍쳐는 뼈대, 골격이라고 의미하면된다.
* 브라우저가 `getBoard.jsp`로 어떤 요청을 했다면, JSP는 사용자의 입력 정보추출, DB연동, 응답 화면을 구성하는 역할을 하고
 데이터를 담는 자바 객체인 DAO,VO가 JavaBeans에 해당한다. 
* 이 JavaBeans를 이용해서 DB에서 데이터를 가져오고 입력을 하는 것이다.

### MVC 패턴 특징
* JSP는 지금까지는 비즈니스로직과 디자인을 합쳐서 두 개의 역할을 하도록 하나의 파일에 작성을 했었는데(Model 1 구조) 
이렇게 했을 때 코드양도 길어지고, 자바코드랑 디자인이 뒤섞여 있으니 수정하기도 어렵고 유지보수하기 어려워진다.
그래서 비즈니스 로직과 디자인을 분리해서 작성하는 것이 MVC 아키텍쳐이다.
* 컨트롤러로서 역할을 할 수 있는 새로운 서블릿을 만들고 요청이 들어오면 서블릿에서 값들을 처리해서 Model에 담아서 View쪽으로 전달한다.
  * 컨트롤러는 비즈니스 로직을 처리하는 것을 말하는데 비즈니스 로직은 다음과 같은 것들을 말한다.
    * 사용자 입력 정보 추출
    * DB 연동
    * 화면 이동 처리

## MVC 패턴을 적용해서 게시판 만들기 구현
* 컨트롤러(Dispatcher Servlet)에 들어오는 값들을 처리하고, Model(DAO,VO)에 담아서 View(JSP)로 전달에서
 화면에 뿌려지게 만들도록 분리시킨다.  
```groovy
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
			   
			   HttpSession session = request.getSession(); // jsp은 session을 바로 쓸 수 있지만 서블릿은 바로 쓸 수 없음. 선언해줘야 함.  
			   session.setAttribute("user", user); // 여기서 세션에 유저를 담고
			   
			   response.sendRedirect("getBoardList.do"); // jsp로 가면 안되고 do로 가야된다.
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
			response.sendRedirect("getBoardList.do"); // 글 등록을 했으면 새로운 새션을 가져와서 갱신해야 한다.
			// 글 수정 삭제 입력을 하면 갱신을 목록을 해서 꺼내야 한다.
			// 다시 로그인을 해야 글목록을 다시 뿌릴 수 있다.
			
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
			String searchCondition = request.getParameter("searchCondition"); 
			String searchKeyword = request.getParameter("searchKeyword");
			
			if(searchCondition == null) searchCondition = "TITLE";
			if(searchKeyword == null) searchKeyword = "";
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSerachCondition(searchCondition);
			vo.setSerachKeyword(searchKeyword);
			
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			
			// 3. 검색 결과를 화면(JSP)에서 사용할 수 있도록 세션에 등록한다.
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList); // 글목록을 세션에 담음
			
			// 글 목록 화면(getBoardList.jsp)으로 이동한다.
			response.sendRedirect("getBoardList.jsp");
            // 이걸 do로바꾸면 무한루프에 빠진다 세션에담고 다시 do로해서 이 조건문 실행...계속 됨.
					
		} else {
			
			System.out.println("URL을 다시 확인해주시기 바랍니다.");
		}
	}
}
```
```html
<%
	// 1. 세션에 등록된 정보 꺼내기	
	UserVO user = (UserVO) session.getAttribute("user");
	BoardVO board = (BoardVO) session.getAttribute("board");
	// 로그인할 때 user를 세션에 담고, getBoard.do에서 board를 세션에 담는다.
%>

<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=EUC-KR'>
<title>글 목록</title>
</head>
<body>
	<center>
		<h1>게시글 목록</h1>
		<h3>
			<font color='red'>${user.name}></font>님 로그인 환영합니다...... <a href='logout.do'>Log-out</a>
		</h3>
		<!-- 검색 시작 -->
		<form action='getBoardList.jsp' method='post'>
			<table border='1' cellpadding='0' cellspacing='0' width='700'>
				<tr>
					<td align='right'><select name='searchCondition'>
							<option value='TITLE'>제목
							<option value='CONTENT'>내용
					</select> <input name='searchKeyword' type='text' /> <input type='submit'
						value='검색' /></td>
				</tr>
			</table>
		</form>
		<!-- 검색 종료 -->
		<table border='1' cellpadding='0' cellspacing='0' width='700'>
			<tr>
				<th bgcolor='orange' width='100'>번호</th>
				<th bgcolor='orange' width='200'>제목</th>
				<th bgcolor='orange' width='150'>작성자</th>
				<th bgcolor='orange' width='150'>등록일</th>
				<th bgcolor='orange' width='100'>조회수</th>
			</tr>
		<% for(BoardVO board :boardList) { %>		
        <tr>
            <td>${board.seq}</td>
            <td align='left'><a href='getBoard.do?seq=${board.seq}'>${board.title}</a></td>
            <td>${board.writer}</td>
            <td>${board.regDate}</td>
            <td>${board.cnt}<td>
        </tr>
        <%} %>
		</table>
		<br> <a href='insertBoard.html'>새글 등록</a>
	</center>
</body>
</html>
```
### 위의 코드에서 주의할 점
* 원래 Select한 결과를 세션에 담아서 보내면 안된다.
  * 이유는 세션은 서버 메모리에 생성이 되는 객체여서 많은 사람들이 로그인을 할 때마다 세션이 생성 된다.
  여기에 Select한 결과까지 담기게 되면 세션이 커져서 메모리를 많이 쓰게 되서 메모리 낭비가 된다.
  그래서 Request 객체 담아야 한다 세션은 서버가 켜져있으면 계속 메모리에 올라가 있는데 Request는 저장된 데이터를 추출해서 response 객체가 호출되면 메모리에서 사라지기 때문에
  * 세션에는 가급적 아이디, 권한 정도를 저장한다.
  * Set으로 먼저 세션을 설정해주고, get으로 세션에 대한 데이터들을 가져오게 해야 한다.
  * 입력, 수정, 삭제에 대한 기능이 수행되었을 때 DB의 데이터가 변경되니깐 세션에 이 데이터들을 담아서 전송하니
  새로운 글 목록 화면을 다시 리다이렉션해서 세션값을 받아오도록 리다이렉션해야 한다. (getBoardList.do, getBoard.do..)
  * Servlet에서는 Session을 생성해주고 사용해야 하지만 JSP에서는 서블릿으로 변경되기 때문에 내장객체로 Session이 선언되어 있어서
  바로  선언해서 사용할 수 있다.
* 위의 코드처럼 분리해서 작성은 했지만, View쪽에 아직까지 자바코드가 남아있어서 완전히 분리되지 않은 것처럼
보일 수 있는데 MVC 패턴은 비즈니스로직과 디자인을 분리하는 것인데 if, for 등과같이 위의 자바로 된 코드들은
비즈니스로직에 해당되지 않아서 상관이 없다.
* 그런데도 완전히 자바코드와 디자인까지 분리하고 싶다하면 JSTL, EL을 사용하면 된다.

#### JSP 파일에서 자바 코드를 완벽히 없애기 위한 방법
* EL(Expression Language) 이란?
  * JSP 파일에서 request나 session(내장 객체)에 등록한 데이터를 접근하기 위한 표현식  

* JSTL(JSP Standard Tag Library) 이란?
  * JSP 파일에서 if, for, switch 등과 같은 자바 코드를 대체하는 JSP 표준 태그
    
* JSTL, Standard jar 라이브러리가 없어도 EL은 잘 실행된다 EL과 상관없음 JSTL과 관련되어 있다.
  
```html
<c:forEach var="board" items="${boardList}">
<tr>
    <td>${board.seq}</td>
    <td align='left'><a href='getBoard.do?seq=${board.seq}'>${board.title}</a></td>
    <td>${board.writer}</td>
    <td>${board.regDate}</td>
    <td>${board.cnt}<td>
</tr>
</c:forEach>
```   
* 위의 html처럼 작성을 하면 따로 자바코드를 작성해서 데이터를 전달받는걸 할 필요가 없다. 
* 세션이나 리퀘스트에서 알아서 해당하는 객체이름의 데이터들을 가져오고 문법만 올바르게 작성하면 그것에 대한 값들을 출력해준다.
* 가져오는 객체가 없어도 NullPointException이 발생하지 않는다.
* 세션과 리퀘스트에 똑같은 객체가 들어가 있으면 리퀘스트가 우선권을 가져서
리퀘스트에서 먼저 얻어서 적용해서 충돌이 나지 않는다.

