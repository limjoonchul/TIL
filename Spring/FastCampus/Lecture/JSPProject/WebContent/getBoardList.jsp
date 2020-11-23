<%@page import="java.util.List"%>
<%@page import="com.rubypapper.biz.board.BoardDAO"%>
<%@page import="com.rubypapper.biz.board.BoardVO"%>
<%@page contentType="text/html; charset=EUC-KR" %>

<%
    //1. ����� �Է� ���� ����(�˻� ����� ����...)
	// request.setCharacterEncoding("EUC-KR");
	
	
	String searchCondition = request.getParameter("searchCondition"); 
	String searchKeyword = request.getParameter("searchKeyword");
	
	// null check  �� �ΰ��� üũ�ϴ��� ����� ������ �˾ƺ���.
	// ó�� �α����� ���� �� �̰� �˻��� ���� �Ķ���͸� �������� ���̱� ������ ó���� ���� ���� 
	// �׷��� nullpointException�� �߻��ϱ� ������ null�϶� ���� �̷��� �����ؼ� �־��ִ� ���̴�. �̷��� �ϸ� ���� ����
	if(searchCondition == null) searchCondition = "TITLE";
	if(searchKeyword == null) searchKeyword = "";
	
	
	// 2. DB ���� ó��
	BoardVO vo = new BoardVO();
	vo.setSerachCondition(searchCondition);
	vo.setSerachKeyword(searchKeyword);
	
	BoardDAO boardDAO = new BoardDAO();
	List<BoardVO> boardList = boardDAO.getBoardList(vo);
	// db���� �۸���� ������
	
	// 3. ���� ȭ�� ����

%>

<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=EUC-KR'>
<title>�� ���</title>
</head>
<body>
	<center>
		<h1>�Խñ� ���</h1>
		<h3>
			���̴� �α��� ȯ���մϴ�...... <a href='logout_proc.jsp'>Log-out</a>
		</h3>
		<!-- �˻� ���� -->
		<form action='getBoardList.jsp' method='post'>
			<table border='1' cellpadding='0' cellspacing='0' width='700'>
				<tr>
					<td align='right'><select name='searchCondition'>
							<option value='TITLE'>����
							<option value='CONTENT'>����
					</select> <input name='searchKeyword' type='text' /> <input type='submit'
						value='�˻�' /></td>
				</tr>
			</table>
		</form>
		<!-- �˻� ���� -->
		<table border='1' cellpadding='0' cellspacing='0' width='700'>
			<tr>
				<th bgcolor='orange' width='100'>��ȣ</th>
				<th bgcolor='orange' width='200'>����</th>
				<th bgcolor='orange' width='150'>�ۼ���</th>
				<th bgcolor='orange' width='150'>�����</th>
				<th bgcolor='orange' width='100'>��ȸ��</th>
			</tr>
			<% for(BoardVO board :boardList) { %> <!-- ���� ������ ó���ؾ��� -->
			<tr>
				<td><%= board.getSeq() %></td>
				<td align='left'><a href='getBoard.jsp?seq=11'><%= board.getTitle() %></a></td>
				<td><%= board.getWriter() %></td>
				<td><%= board.getRegDate() %></td>
				<td><%= board.getCnt() %><td>
			</tr>
			<%} %>
		</table>
		<br> <a href='insertBoard.html'>���� ���</a>
	</center>
</body>
</html>
