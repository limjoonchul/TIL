<%@ page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	
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

<%-- <%
    // 0. ���ǿ� ��ϵ� ���� ������
    // HttpSession session1 = request.getSession();
    UserVO user = (UserVO) session.getAttribute("user");
    List<BoardVO> boardList =(List) session.getAttribute("boardList");
    // �α����� �� user�� ���ǿ� ���, getBoardList.do���� boardList�� ���ǿ� ��´�.(set)
    // ����ó �������� set�� ��������Ǿ�� session���� �����Ͱ� �̵��Ǽ� ���⼭ get���� �����͸� ���� �� �ִ�. �׷��� getboardList.do���� ���� ����Ǿ�� �Ѵ�.
    // set�� ���� ������� ������ ������ �ٽ� �ε��ϰ� session.get�� �ϸ� ������ �ʱ�ȭ�Ǿ��־ ���𰡸� ����������Ҷ� ������ ����.

   
	
	// 3. ���� ȭ�� ����

%> --%>

<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=EUC-KR'>
<title><spring:message code="board.getboardlist.mainTitle"/></title>
</head>
<body>
	<center>
		<h1><spring:message code="board.getboardlist.boardList"/></h1>
		<h3>
			<font color='red'>${user.name}</font>�� �α��� ȯ���մϴ�...... <a href='logout.do'>Log-out</a>
		</h3>
		<!-- �˻� ���� -->
		<form action='getBoardList.do' method='post'>
			<table border='1' cellpadding='0' cellspacing='0' width='700'>
				<tr>
					<td align='right'>
					
					<select name='searchCondition'>
							<option value='TITLE' <c:if test="${search.searchCondition == 'TITLE'}">selected="selected"</c:if>><spring:message code="board.getboardlist.searchtitle"/></option>
							<option value='CONTENT' <c:if test="${search.searchCondition == 'CONTENT'}">selected="selected"</c:if>><spring:message code="board.getboardlist.searchcontent"/></option>
					</select> 
					<input name='searchKeyword' type='text' value="${search.searchKeyword}"/> 
					<input type='submit'value='<spring:message code="board.getboardlist.searchBtn"/>' />
					</td>
				</tr>
			</table>
		</form>
		<!-- �˻� ���� -->
		<table border='1' cellpadding='0' cellspacing='0' width='700'>
			<tr>
				<th bgcolor='orange' width='100'><spring:message code="board.getboardlist.number"/></th>
				<th bgcolor='orange' width='200'><spring:message code="board.getboardlist.boardTitle"/></th>
				<th bgcolor='orange' width='150'><spring:message code="board.getboardlist.boardWriter"/></th>
				<th bgcolor='orange' width='150'><spring:message code="board.getboardlist.boardDate"/></th>
				<th bgcolor='orange' width='100'><spring:message code="board.getboardlist.boardCnt"/></th>
			</tr>
		
		<c:forEach var="board" items="${boardList}">
        <tr>
            <td>${board.seq}</td>
            <td align='left'><a href='getBoard.do?seq=${board.seq}'>${board.title}</a></td>
            <td>${board.writer}</td>
            <td>${board.regDate}</td>
            <td>${board.cnt}<td>
        </tr>
        </c:forEach>
       
		</table>
		<br> <a href='insertBoard.do'><spring:message code="board.getboardlist.newBoardBtn"/></a>
	</center>
</body>
</html>
