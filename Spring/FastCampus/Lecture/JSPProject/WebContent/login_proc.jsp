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
	   response.sendRedirect("getBoardList.jsp");
   } else{
	   response.sendRedirect("login.html");
   }
%>    
    
