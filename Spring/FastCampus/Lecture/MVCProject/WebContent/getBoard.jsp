<%@page import="com.rubypaper.biz.user.UserVO"%>
<%@page import="com.rubypaper.biz.board.BoardDAOJDBC"%>
<%@page import="com.rubypaper.biz.board.BoardVO"%>
<%@page contentType="text/html; charset=EUC-KR"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!--
 jsp파일에서 자바코드를 완벽히 없애기 위한 방법
  EL(Expression Language) 이란?
  JSP 파일에서 request나 session(내장 객체)에 등록한 데이터를 접근하기 위한 표현식  
  
  JSTL, Standard jar 라이브러리가 없어도 el은 잘 실행된다 el과 상관없음 jstl과 관련되어 있다.
  
  JSTL(JSP Standard Tag Library) 이란?
  JSP 파일에서 IF, FOR, SWITCH 등과 같은 자바 코드를 대체하는 JSP 표준 태그
 -->
<%
	//1. 세션에 등록된 정보 꺼내기
	// HttpSession session1 = request.getSession();
	//UserVO user = (UserVO) session.getAttribute("user");
	//BoardVO board = (BoardVO) session.getAttribute("board");
	// 로그인할 때 user를 세션에 담고, getBoard.do에서 board를 세션에 담는다.

%>
<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=EUC-KR'>
<title>글 상세</title>
</head>
<body>
	<center>
		<h1>글 상세</h1>
		<h3>
			<a href='logout.do'>Log-out</a>
		</h3>
		<hr>
		<form action='updateBoard.do' method='post'>
			<input type='hidden' name='seq' value='${board.seq }' />
			<table border='1' cellpadding='0' cellspacing='0'>
				<tr>
					<td bgcolor='orange' width='70'>제목</td>
					<td align='left'><input name='title' type='text'
						value='${board.title }>' /></td>
				</tr>
				<tr>
					<td bgcolor='orange'>작성자</td>
					<td align='left'>${board.writer}</td>
				</tr>
				<tr>
					<td bgcolor='orange'>내용</td>
					<td align='left'><textarea name='content' cols='40' rows='10'>${board.content}</textarea></td>
				</tr>
				<tr>
					<td bgcolor='orange'>등록일</td>
					<td align='left'>${board.regDate }</td>
				</tr>
				<tr>
					<td bgcolor='orange'>조회수</td>
					<td align='left'>${board.cnt }</td>
				</tr>
				<tr>
					<td colspan='2' align='center'><input type='submit'
						value='글 수정' /></td>
				</tr>
			</table>
		</form>
		<hr>
		<a href='insertBoard.html'>글등록</a>&nbsp;&nbsp;
<%-- 		<%
			//if (user.getRole().equals("관리자")) {
		%> --%>
		<c:if test="${user.role == '관리자'}">
		<%-- if, foreacha 만 기억하면된다. jstl 라이브러리를 추가해서  이렇게 사용할 수 있다.--%>
		<a href='deleteBoard.do?seq=${board.seq }>'>글삭제</a>&nbsp;&nbsp;
		</c:if>
<%-- 		<%
			//}
		%> --%>
		<a href='getBoardList.do'>글목록</a> 
		<!-- do로 바꿔줘야한다. 상세정보를 보는동안 누군가 글을 수정할수있으니 새로운 목록을 가져와야 한다. .jsp는 예전에 데이터가 나타난ㄴ다 새로운게 안나타난다. -->
	</center>
</body>
</html>