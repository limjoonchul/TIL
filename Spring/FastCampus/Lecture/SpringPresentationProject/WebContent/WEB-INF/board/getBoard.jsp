<%@page import="com.rubypaper.biz.user.UserVO"%>
<%@page import="com.rubypaper.biz.board.BoardDAOJDBC"%>
<%@page import="com.rubypaper.biz.board.BoardVO"%>
<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!--
 jsp���Ͽ��� �ڹ��ڵ带 �Ϻ��� ���ֱ� ���� ���
  EL(Expression Language) �̶�?
  JSP ���Ͽ��� request�� session(���� ��ü)�� ����� �����͸� �����ϱ� ���� ǥ����  
  
  JSTL, Standard jar ���̺귯���� ��� el�� �� ����ȴ� el�� ������� jstl�� ���õǾ� �ִ�.
  
  JSTL(JSP Standard Tag Library) �̶�?
  JSP ���Ͽ��� IF, FOR, SWITCH ��� ���� �ڹ� �ڵ带 ��ü�ϴ� JSP ǥ�� �±�
 -->
<%
	//1. ���ǿ� ��ϵ� ���� ������
	// HttpSession session1 = request.getSession();
	//UserVO user = (UserVO) session.getAttribute("user");
	//BoardVO board = (BoardVO) session.getAttribute("board");
	// �α����� �� user�� ���ǿ� ���, getBoard.do���� board�� ���ǿ� ��´�.

%>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=EUC-KR'>
<title><spring:message code="board.getboard.mainTitle"/></title>
</head>
<body>
	<center>
		<h1><spring:message code="board.getboard.boardDetail"/></h1>
		<h3>
			<a href='logout.do'><spring:message code="board.getboard.logout"/></a>
		</h3>
		<hr>
		<form action='updateBoard.do' method='post'>
			<input type='hidden' name='seq' value='${board.seq }' />
			<table border='1' cellpadding='0' cellspacing='0'>
				<tr>
					<td bgcolor='orange' width='70'><spring:message code="board.getboard.title"/></td>
					<td align='left'><input name='title' type='text'
						value='${board.title }' /></td>
				</tr>
				<tr>
					<td bgcolor='orange'><spring:message code="board.getboard.writer"/></td>
					<td align='left'>${board.writer}</td>
				</tr>
				<tr>
					<td bgcolor='orange'><spring:message code="board.getboard.content"/></td>
					<td align='left'><textarea name='content' cols='40' rows='10'>${board.content}</textarea></td>
				</tr>
				<tr>
					<td bgcolor='orange'><spring:message code="board.getboard.regDate"/></td>
					<td align='left'>${board.regDate}</td>
				</tr>
				<tr>
					<td bgcolor='orange'><spring:message code="board.getboard.cnt"/></td>
					<td align='left'>${board.cnt}</td>
				</tr>
				<tr>
					<td colspan='2' align='center'><input type='submit'
						value='<spring:message code="board.getboard.boardUpdate"/>' /></td>
				</tr>
			</table>
		</form>
		<hr>
		<a href='insertBoard.do'><spring:message code="board.getboard.boardInsert"/></a>&nbsp;&nbsp;
<%-- 		<%
			//if (user.getRole().equals("������")) {
		%> --%>
		<c:if test="${user.role == '������'}">
		<%-- if, foreacha �� ����ϸ�ȴ�. jstl ���̺귯���� �߰��ؼ�  �̷��� ����� �� �ִ�.--%>
		<a href='deleteBoard.do?seq=${board.seq}'><spring:message code="board.getboard.boardDelete"/></a>&nbsp;&nbsp;
		</c:if>
<%-- 		<%
			//}
		%> --%>
		<a href='getBoardList.do'><spring:message code="board.getboard.boardList"/></a> 
		<!-- do�� �ٲ�����Ѵ�. �������� ���µ��� ������ ���� �����Ҽ������� ���ο� ����� �����;� �Ѵ�. .jsp�� ������ �����Ͱ� ��Ÿ������ ���ο�� �ȳ�Ÿ����. -->
	</center>
</body>
</html>