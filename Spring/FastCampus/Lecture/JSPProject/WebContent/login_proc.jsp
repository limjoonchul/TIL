<%@page import="com.rubypapper.biz.user.UserDAO"%>
<%@page import="com.rubypapper.biz.user.UserVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<%
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
	   // 로그인 성공시, 세션에 상태정보를 저장한다. 내장객체여서 바로 쓰면 된다. 서블릿에서는 선언해서 얻어줘야하지만 jsp에서는 세션을 많이 사용하니깐 기본적으로 제공이 된다.
	   // HttpSession session1 = request.getSession(); 
	   session.setAttribute("user", user);
	   // 변환되는 서블릿 파일에 _jspService 메소드 안에 session 변수가 이미 선언되어 있다. 그래서 session으로 변수명을 사용하면 안된다.
	   // request, response처럼  따로 선언하지 않고 바로 쓸 수 있다. 이런걸 내장 객체라고 부른다.
	   // 내장객체 - 내장함수 데이터베이스의 avg(), min()과같이 선언하지 않고 초기화하지 않고 서버에서 알아서 초기화해주서 가져다 쓰기만 하면 된다.
	// Servletconfig - 로컬파라미터 추출, Servletcontext - 글로벌파라미터 추출
	// 로컬파라미터라고 부르는 이유는  로컬파라미터가 써져있는 해당 서블릿에서만 적용가능하니깐.
	// 서블릿컨피그는 init()오버라이딩해서  실행할 때 서블릿컨피그를 서블릿엔진이 생성해서 넣어준다.

	   response.sendRedirect("getBoardList.jsp");
   } else{
	   response.sendRedirect("login.html");
   }
%>    
    
