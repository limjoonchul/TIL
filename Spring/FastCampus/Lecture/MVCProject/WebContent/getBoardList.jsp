<%@ page contentType="text/html; charset=EUC-KR"%>
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

<%-- <%
    // 0. 세션에 등록된 정보 꺼내기
    // HttpSession session1 = request.getSession();
    UserVO user = (UserVO) session.getAttribute("user");
    List<BoardVO> boardList =(List) session.getAttribute("boardList");
    // 로그인할 때 user를 세션에 담고, getBoardList.do에서 boardList를 세션에 담는다.(set)
    // 디스패처 서블릿에서 set이 먼저실행되어야 session으로 데이터가 이동되서 여기서 get으로 데이터를 꺼낼 수 있다. 그래서 getboardList.do부터 먼저 실행되어야 한다.
    // set이 먼저 실행되지 않으면 서버를 다시 로딩하고 session.get을 하면 세션은 초기화되어있어서 무언가를 가져오라고할때 오류가 난다.

   
	
	// 3. 응답 화면 구성

%> --%>

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
		<%-- <% for(BoardVO board :boardList) { %> <!-- 따로 나눠서 처리해야함 --> --%>
		<c:forEach var="board" items="${boardList}">
        <tr>
            <td>${board.seq}</td>
            <td align='left'><a href='getBoard.do?seq=${board.seq}'>${board.title}</a></td>
            <td>${board.writer}</td>
            <td>${board.regDate}</td>
            <td>${board.cnt}<td>
        </tr>
        </c:forEach>
        <%-- <%} %> --%>
		</table>
		<br> <a href='insertBoard.html'>새글 등록</a>
	</center>
</body>
</html>
