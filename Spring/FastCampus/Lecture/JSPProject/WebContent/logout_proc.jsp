<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
  HttpSession session1 = request.getSession();
  session1.invalidate();
  
  response.sendRedirect("login.html");
%>

