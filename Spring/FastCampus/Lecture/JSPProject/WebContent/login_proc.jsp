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
	   // �α��� ������, ���ǿ� ���������� �����Ѵ�. ���尴ü���� �ٷ� ���� �ȴ�. ���������� �����ؼ� ������������ jsp������ ������ ���� ����ϴϱ� �⺻������ ������ �ȴ�.
	   // HttpSession session1 = request.getSession(); 
	   session.setAttribute("user", user);
	   // ��ȯ�Ǵ� ���� ���Ͽ� _jspService �޼ҵ� �ȿ� session ������ �̹� ����Ǿ� �ִ�. �׷��� session���� �������� ����ϸ� �ȵȴ�.
	   // request, responseó��  ���� �������� �ʰ� �ٷ� �� �� �ִ�. �̷��� ���� ��ü��� �θ���.
	   // ���尴ü - �����Լ� �����ͺ��̽��� avg(), min()������ �������� �ʰ� �ʱ�ȭ���� �ʰ� �������� �˾Ƽ� �ʱ�ȭ���ּ� ������ ���⸸ �ϸ� �ȴ�.
	// Servletconfig - �����Ķ���� ����, Servletcontext - �۷ι��Ķ���� ����
	// �����Ķ���Ͷ�� �θ��� ������  �����Ķ���Ͱ� �����ִ� �ش� ���������� ���밡���ϴϱ�.
	// �������Ǳ״� init()�������̵��ؼ�  ������ �� �������Ǳ׸� ���������� �����ؼ� �־��ش�.

	   response.sendRedirect("getBoardList.jsp");
   } else{
	   response.sendRedirect("login.html");
   }
%>    
    
