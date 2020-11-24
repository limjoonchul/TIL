# JSP(Java Server Page)
## JSP란?
* HTML내에 자바 코드를 삽입하여 웹 서버에서 동적으로 웹 페이지를 생성하여 웹 브라우저에 돌려주는 서버 사이드 스크립트 언어이다.
Java EE 스펙 중 일부로 웹 애플리케이션 서버에서 동작한다.

### JSP의 등장이유
#### 서블릿의 단점
* 코드를 수정하면 다시 로딩이 될 때까지 기다려야 했다.
* 서블릿이 나오고 많은 곳에서 사용이 되었지만, 서블릿은 Web.XML에 설정들을 일일히 등록을 해줘야 했다.
* 자바코드에 HTML을 넣어서 작성을 해야하기 때문에 디자인 코드를 구현할 때 한줄 한줄 출력 스트림(out.println())에 넣어서
구현을 했어야 했다. 작성하다보면 "" , '' 을 구별해서 작성해야 해서 자바 문법에 어긋나는 상황이 발생하기도 했다.
그래서 대규모 프로젝트에 사용하기에 굉장히 번거롭고 힘들었다.

* 위의 서블릿의 단점을 개선해서 등장한 것이 JSP이다.
* 작성해야 하는 코드가 더 간결해지고, Web.xml에 따로 설정을 등록을 안해도 되고, 수정 후에 리로딩 없이 바로 브라우저에 적용이 된다.

### JSP의 특징
* JSP는 서블릿의 단점을 개선하기 위해서 나온 언어여서 서블릿을 기반으로 동작이 이루어진다.
실행이 될 때 서블릿으로 변환이되서 실행이 된다.
* 그래서 오류가 나게 되면 로그에 서블릿 파일에 대한 로그가 남게 되서, 서블릿 파일을 확인해야 한다.
예로, 내가 작성한 JSP코드는 20줄인데 로그에는 서블릿 150라인에 오류가 났다 이렇게 보여지게 된다.
* JSP로 작성한 코드가 실행될 때 서블릿의 service()에 들어가게 된다.

### 문법
* `<% %>`(Scriptlet) - Scriptlet은 정상적인 자바코드가 들어간다.
* `<%= %>`(Expression) - Expression은 변수, 메소드 호출(리턴타입이 void가 아닌 뭔가를 리턴하는 메소드만 가능)이 들어갈 수 있다. 
안에 `;`가 뒤에 들어가면 정상적인 자바코드로 인식해서 안된다 ` ; `없어야 한다. 여러줄의 코딩을 할 수 없다.

* <%-- --%> jsp 주석처리  - servlet으로 변환되지 않는다. (단축키 ctrl + shift + /) 
* <! -- --> - html주석 servlet으로 변환될 때 포함이 된다.

```html
<%@page import="java.util.List"%>
<%@page import="com.rubypapper.biz.board.BoardDAO"%>
<%@page import="com.rubypapper.biz.board.BoardVO"%>
<%@page contentType="text/html; charset=EUC-KR" %>

<%
    //1. 사용자 입력 정보 추출(검색 기능은 숙제...)
	// request.setCharacterEncoding("EUC-KR");
	
	String searchCondition = request.getParameter("searchCondition"); 
	String searchKeyword = request.getParameter("searchKeyword");
	
	if(searchCondition == null) searchCondition = "TITLE";
	if(searchKeyword == null) searchKeyword = "";
	
	// 2. DB 연동 처리
	BoardVO vo = new BoardVO();
	vo.setSerachCondition(searchCondition);
	vo.setSerachKeyword(searchKeyword);
	
	BoardDAO boardDAO = new BoardDAO();
	List<BoardVO> boardList = boardDAO.getBoardList(vo);
	// DB에서 글목록을 가져옴
	
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
```
* 로그인 성공시, 세션에 상태정보를 저장한다. 내장객체여서 바로 쓰면 된다. 서블릿에서는 선언해서 얻어줘야하지만 jsp에서는 세션을 많이 사용하니깐 기본적으로 제공이 된다
  * 하면서 의아했던 부분인데 강사님이 설명해주심!
  * `HttpSession session1 = request.getSession();` 
  * `session.setAttribute("user", user);`
* 변환되는 서블릿 파일에 _jspService 메소드 안에 session 변수가 이미 선언되어 있다. 그래서 session으로 변수명을 사용하면 안된다.
*  request, response처럼  따로 선언하지 않고 바로 쓸 수 있다. 이런걸 내장 객체라고 부른다.
* 내장객체 - 내장함수 데이터베이스의 avg(), min()과같이 선언하지 않고 초기화하지 않고 서버에서 알아서 초기화해주서 가져다 쓰기만 하면 된다.
* Servletconfig - 로컬파라미터 추출, Servletcontext - 글로벌파라미터 추출
* 로컬파라미터라고 부르는 이유는  로컬파라미터가 써져있는 해당 서블릿에서만 적용가능하니깐.
* 서블릿컨피그는 init()오버라이딩해서  실행할 때 서블릿컨피그를 서블릿엔진이 생성해서 넣어준다.
### JSP의 내장 객체
* JSP페이지 내에서 자주 사용되는 객체들을 컨테이너가 생성하여 제공하는 객체이다.

![JSP내장객체](/Java/documents/images/JSP내장객체.jpg)
