<%@page import="java.util.List"%>
<%@page import="com.rubypapper.biz.board.BoardDAO"%>
<%@page import="com.rubypapper.biz.board.BoardVO"%>
<%@page contentType="text/html; charset=EUC-KR" %>

<%
    //1. 사용자 입력 정보 추출(검색 기능은 숙제...)
	// request.setCharacterEncoding("EUC-KR");
	
	
	String searchCondition = request.getParameter("searchCondition"); 
	String searchKeyword = request.getParameter("searchKeyword");
	
	// null check  왜 널값을 체크하는지 제대로 못들음 알아보자.
	// 처음 로그인을 했을 때 이건 검색에 대한 파라미터를 가져오는 것이기 때문에 처음엔 값이 없다 
	// 그래서 nullpointException이 발생하기 때문에 null일때 값을 이렇게 설정해서 넣어주는 것이다. 이렇게 하면 모든게 나옴
	if(searchCondition == null) searchCondition = "TITLE";
	if(searchKeyword == null) searchKeyword = "";
	
	
	// 2. DB 연동 처리
	BoardVO vo = new BoardVO();
	vo.setSerachCondition(searchCondition);
	vo.setSerachKeyword(searchKeyword);
	
	BoardDAO boardDAO = new BoardDAO();
	List<BoardVO> boardList = boardDAO.getBoardList(vo);
	// db에서 글목록을 가져옴
	
	// 3. 응답 화면 구성

%>

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
			에이님 로그인 환영합니다...... <a href='logout_proc.jsp'>Log-out</a>
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
			<% for(BoardVO board :boardList) { %> <!-- 따로 나눠서 처리해야함 -->
			<tr>
				<td><%= board.getSeq() %></td>
				<td align='left'><a href='getBoard.jsp?seq=11'><%= board.getTitle() %></a></td>
				<td><%= board.getWriter() %></td>
				<td><%= board.getRegDate() %></td>
				<td><%= board.getCnt() %><td>
			</tr>
			<%} %>
		</table>
		<br> <a href='insertBoard.html'>새글 등록</a>
	</center>
</body>
</html>
