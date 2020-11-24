# Spring IoC
## 프레임 워크(Framework)란?
* 사전적 의미는 뼈대 혹은 골격이다.
* 예를 들어, A,B라는 개발자들에게 인형을 만들라고 했을 때, A와 B는 자신들의 생각을 바탕으로
각 자만의 인형을 만들 것이다. 그랬을 때 A,B는 서로가 만든 인형을 알지 못하고 어떤 구조로 되어있는지 파악하기도 힘들다.
그래서 서로의 인형들을 유지보수하게 되었을 때 또 여기서 각 자의 생각을 적용해서 원래의 형태가 아닌 다른 형태로 유지보수가 될 것이다.
* 이처럼 개발자의 각자의 생각과 능력에 따라 개발이 이루어지게 되면 만들려고 하는 프로그램의 일관성이 사라지게 된다.
프로그램, 서비스를 이어나가다보면 유지보수를 하는 개발자들이 달리지게 되어있다. 다른 사람들이 만들어 놓은 것을 유지보수 해야할 일들이 생길테니깐
* 그래서 개발자가 달라져도 똑같은 아키텍쳐를 유지할 수 있게 기본적인 뼈대를 제공해주는 것이 프레임 워크이다.
그 위에 개발자들이 비즈니스로직을 작성하는 것이다.

## 프레임워크의 장점
* 빠른 구현 시간 - 복잡한 클래스들을 프레임워크가 해주고, 개발자들이 만들지 않으니깐 비즈니스 로직만 만들면 되서 개발 시간이 빨라진다.
* 관리의 용이성 증가 - 코드가 줄어들고, 아키텍쳐 부분들을 신경 쓸 필요가 없기 때문에
* 개발자의 역량 획일화 - 프레임워크를 사용하면 프레임워크에서 개발자들이 신경써야 할 부분들을 많이 없애주고
같은 기능을 짧은 코드로 개발할 수 있게 만들어주기 때문에 경력이 많은 개발자와 경력이 짧은 개발자들 사이으이
역량차이가 많이 줄어든다.
* 검증된 아키텍쳐의 재사용과 일관성 유지 - 아키텍쳐를 오라클, IBM같은 큰 회사에서 개발하니 신뢰서이 좋고,
여러 시스템을 같은 프레임워크로 개발한다 했을 때 아키텍쳐는 같으니 하나를 분석을 잘하면 다른 시스템들도 쉽게 유지보수 할 수 있다.
아키텍쳐는 시간이 지나도 변경되지 않는다.

### 자바 기반의 Framework
* 자바기반의 프레임워크는 오픈소스이다.
  * 오픈소스는 소스를 모두에게 공개하는 것을 말하는데 즉, .java이 파일을 공개한다. 
  라이센스는 있지만 벼롣의 비용을 제공하지 않고도 사용할 수 있다.
* 오픈소스의 장점은 무료로 사용할 수 있는 점도 있지만 오픈소스를 이용해서 자신만의 프레임워크를 만들 수 있다.
커스터마이징이 가능하다는 것이 최대 장점이다.

![자바프레임워크](/Java/documents/images/자바프레임워크.jpg)

#### 화면 개발 
* Strutcs - 버전업이 느려서 사용하지 않는다. 버전업이 안되면 내가 기능을 추가해서 사용해야 함 귀찮다
* Spring MVC - 버전업이 굉장히 빨리 올라감, mvc아키텍쳐를 이용해 개발한다.
#### 비즈니스 로직처리 
* Spring - EJB기반으로 개발했는데 EJB도 망했다. 지금 자바기반 프레임워크 중에 젤 많이 사용된다.
#### 디비연동
* 하이버네이트 or jpa - orm
* iBatis or myBatis - data mapper
  * 두개의 공통점 디비연동을 자바코드 한줄로 가능함
  * 차이점은 아이바티스는 개발자가 sql 명령어를 xml파일에 작성해야 한다.
  하이버네이티는 orm이여서 오브젝트 관계 매핑 sql까지 프레임워크가 제공한다.

* SQL이 다르기 때문에 데이터베이스를 바꾸는 것은 엄청 어렵다. 
  * 아이바티스와 마이바티스는 dbms가 바뀌면 다 뜯어고쳐야한다
  * 하이버네이트 jpa는 프레임워크 xml파일에 dbms가 바뀐다고 한줄만 써주면 된다.
  * 세계적으론 ORM(하이버네이트, JPA)를 많이 쓰는데 국내에선 배우기 쉬워서 아이바티스 마이바티스가 많이쓰인다. 
  ORM은 배우기 어렵다.

## 스프링 프레임워크의 특징
### IoC, AoP
### 경량 
  1. 라이브러리 자체가 사이즈가 작다. 전부 다운로드해도 Mega단위 밖에 안된다. 스프링 자체가 가볍다.
  2. POJO를 사용하기 때문에 가볍다.
     * POJO(Plain Old Java Object) - 옛날 평범한 자바 객체를 말한다. 아무런 규칙이 없이 작성된 자바 코드 메모리 사용량이 매우 적다.
     * Not POJO - 클래스를 작성하는데 엄격한 규칙을 적용해서 작성하는 것.
     ```java
       package com.rubypaper.web.board;
       
       import java.io.IOException;
       import java.io.PrintWriter;
       import java.util.List;
       
       import javax.servlet.ServletException;
       import javax.servlet.http.HttpServlet;
       import javax.servlet.http.HttpServletRequest;
       import javax.servlet.http.HttpServletResponse;
       import javax.servlet.http.HttpSession;
       
       import com.rubypapper.biz.board.BoardDAO;
       import com.rubypapper.biz.board.BoardVO;
       import com.rubypapper.biz.user.UserVO;
       
       
       public class GetBoardListServlet extends HttpServlet {
       	private static final long serialVersionUID = 1L;
       	
       
       	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       		// 0. 세션 체크
       		HttpSession session = request.getSession(); //세션객체가 재사용 된다.
       		UserVO user = (UserVO) session.getAttribute("user"); // 오브젝트 객체가 튀어나와서 명시적 형변환해야함.
  
       		// 1. 사용자 입력 정보 추출(검색 기능은 숙제...)
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
       		// db에서 글목록을 가져옴
       		
       		// 3. 응답 화면 구성
       		response.setContentType("text/html;charset=euc-kr"); // 이걸해야 브라우저에서도 글씨가 안깨짐
       	    PrintWriter out = response.getWriter(); // 바디와 연결도ㅚㄴ출력스트림을 얻어서
       	    
              ...
       	}
       }
     ```
      * 위의 서블릿 클래스 처럼 public으로 작성되어야 하고, HttpServlet을 상속받아야하고 service()등을 오버라이딩해야하고,
      디폴트 생성자가 있어야 하는 것처럼 규칙이 있어야 하는것을 NotPoJo라고 부른다.
### 컨테이너(엄청나게 중요한 특징)
* 컨테이너는 물건을 안전하게 관리하고, 용도에 맞게 물건을 구분해서 관리할 목적으로 사용한다.
* 서블릿 컨테이너(서블릿 엔진)는 Web.XML에 등록된 클래스를 컨테이너에서 객체를 만들어서 매핑해서 관리한다.
* 서블릿 컨테이너와 스프링 컨테이너의 차이
  * 서블릿 컨테이너는 클라이언트가 요청을 할 때 서블릿을 생성하게 되어있다 (Lazy-Loding)
  * 스프링 컨테이너는 무조건 서버가 뜰 때 빈에 등록된 객체들이 생성이 된다.(Pre-Loding)
* 서블릿 컨테이너와 스프링 컨테이너의 공통점
  * 둘다 매개변수가 있는 생성자만 존재할 때 디폴트 생성자가 없기 때문에 에러가 난다.
  디폴트 생성자 없이 컨테이너는 객체를 생성할 수 없다.

* 컨테이너란 당신이 작성한 코드의 처리과정을 위임받은 독립적인 존재라고 생각하면 된다. 컨테이너는 적절한 설정만 되어있다면 누구의 도움없이도 프로그래머가 작성한 코드를 스스로 참조한 뒤 알아서 객체의 생성과 소멸을 컨트롤해준다.
  * 출처: https://limmmee.tistory.com/13 [심플하게 개발] 

![컨테이너](/Java/documents/images/Spring컨테이너동작.jpg)

* `applicationContext.xml`에 자바 빈을 등록해준다.
* `applicationContext.xml`을 보고 해당 객체를 생성하고
객체 호출이 오면 해당 객체를 반환해준다.
