<%@page import="com.rubypapper.biz.user.UserDAO"%>
<%@page import="com.rubypapper.biz.user.UserVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>


<%
   // 1. ����� �Է� ���� ����
   String id = request.getParameter("id"); // �ۼ��� jsp������ servlet���� �ȿ� service�ȿ� ����  service�� request �Ű������� �����ϱ� ��� ����.
   String password = request.getParameter("password");
   
   // 2. DB ���� ó��
   UserVO vo = new UserVO();
   vo.setId(id);
   vo.setPassword(password);
   
   UserDAO userDAO = new UserDAO();
   UserVO user = userDAO.getUser(vo);
   
   // 3. ȭ�� �׺���̼�
   if(user != null){
	   response.sendRedirect("getBoardList.jsp");
   } else{
	   response.sendRedirect("login.html");
   }
%>    
    
