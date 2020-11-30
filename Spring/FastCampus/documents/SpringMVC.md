# Spring MVC
* Spring을 통해서 프로젝트를 설계할 때 비즈니스 레이어와 트랜잭션 레이어를 나눠서 설꼐하는데
이런걸 Two Layered Architecture 라고 한다.

## MVC 프레임워크 구조

![mvcframework](/Java/documents/images/MVCFramework.jpg)

* Model 1 방식은 JSP 와 JAVABeans로 나눠져서 JSP에 비즈니스로직과 디자인을 합쳐진 형태로 하나의 파일에 작성했었는데
이랬을 때 코드수정과 유지보수성이 어려워서 이를 해결하기 위해서 MVC 아키텍처를 적용했다.
* Model 2(MVC 아키텍처) 방식으로 비즈니스로직과 JSP를 나눴지만, 하나의 파일에 모든 비즈니스 로직이 들어가 있어서
코드의 양이 많아진다. 그래서 이런 기능들을 여러 구조로 나눈 것이 위 그림이다.

## 직접 MVC 아킥텍쳐의 클래스를 구현
* 클라이언트의 요청이 와서 디스패처서블릿에서 받아서 web.xml을 참고하여서 HandlerMapping을 통해서 요청한 컨트롤러로 매핑이 된다.
요청에 대한 기능을 수행하고, 수행된 결과를 화면에 뿌려줄 View 클래스명을 반환해 주면 ViewResolver한테 다시 전달이 되서
뷰 리졸버가 확인을 해서 해당 View파일을 찾아서 클라이언트에게 응답해줘서 해당 화면이 렌더링 된다.

### DispatcherServlet Class
```java
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
    public DispatcherServlet() {
        System.out.println("===> DispatcherServlet 생성");
    }
    
    @Override
    public void init() throws ServletException {
    	 handlerMapping = new HandlerMapping();
    	 viewResolver = new ViewResolver();
    	 
    	 // ViewReslover의 접두사와 접미사를 설정한다.
    	 viewResolver.setPrefix("./");
    	 viewResolver.setSuffix(".jsp");
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 요청 path 정보를 추출한다.
		String uri = request.getRequestURI(); 
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("path : " + path);
		
		// 2. HandlerMapping을 통해 path에 해당하는 Controller를 검색한다.
		Controller ctrl = handlerMapping.getController(path);
		System.out.println("ctrl" + ctrl);
		
		// 3. 검색된 Controller를 실행한다.
		String viewName = ctrl.handleRequest(request, response);
		System.out.println("viewName" + viewName);
		
		// 4. ViewResolver를 통해서 viewName에 해당하는 화면을 검색한다.
		String view = null;
		if(!viewName.contains(".do")) {
			view = viewResolver.getView(viewName);
			
		} else {
			view = viewName;
		}
		// 5. ViewResolver 가 검색해준 화면으로 이동한다.
		response.sendRedirect(view);
		
	}
}
```

### HandlerMapping Class
```java
public class HandlerMapping {
	private Map<String, Controller> mappings;

	public HandlerMapping() {
		// 모든 컨트롤러 객체를 Map에 등록한다.
		mappings = new HashMap<String, Controller>();
		
		// USER 관련 컨트롤러 등록
		mappings.put("/login.do", new LoginController());
		mappings.put("/logout.do", new LogoutController());
		
		// BOARD 관련 컨트롤러 등록
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/getBoard.do", new GetBoardController());
	}

	public Controller getController(String path) {
		// 매개변수로 받은 path에 해당하는 컨트롤러 객체를 검색하여 리턴한다.
		return mappings.get(path);
	}
}
```

### InsertBoardController Class 
* 각 기능별로 나눠놨다.
```java
public class InsertBoardController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("게시글 등록 기능 처리");

		// 1. 사용자 입력 정보 추출
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		// 2. DB 연동 처리
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);
		
		BoardDAOJDBC boardDAO = new BoardDAOJDBC();
		boardDAO.insertBoard(vo);
		
		// 3. 화면 네비게이션		
		return "getBoardList.do";
	}
}
```

### ViewResolver Class
* ViewResolver는  setPrefix()와 setSuffix()를 이용해서 세팅을 하고
getView()로 viewName 앞 뒤로 prefix, subfix를 붙인다. ("/" + "getBoardList + ".jsp")
```java
package com.rubypaper.web.controller;

public class ViewResolver {
	// 접두사
	private String prefix;
	// 접미사
	private String suffix;
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
}
```
## Spring MVC를 이용한 구현

![SpringMVC](/Java/documents/images/SpringMVC구조.jpg)

### Spring MVC 동작 순서
1. 서버가 실행되면 Servlet Container가 Web.xml을 로딩해서 생성이 된다.
2. 기다리고 있다가 클라이언트로부터 요청이 오면 DispatcherServlet 객체를 생성한다
3. 이때, init()메소드가 딱 한번 실행되는데 이 내부적으로 XmlWebApplicationContext 스프링 컨테이너가
action-servlet.xml을 로딩해서 생성된다.
4. action-servlet.xml에는 빈 등록이 되어있어서 객체들이 메모리에 떠서 요청에 대한 결과를
처리할 수 있게 된다.

### 중요! 핵심!!!
* 웹에서는 서블릿컨테이너와 스프링컨테이너로 동작이 이뤄진다.
* 디스패처서블릿에서 스프링컨테이너를 생성하는 이유
  * 서블릿컨테이너에 등록할 수 있는 종류는 서블릿, 필터, 리스너 이 세가지만 Web.xml에 설정할 수 있다.
  그래서 컨트롤러, 핸들러 매핑, 뷰리졸버 이런 클래스들을 서블릿컨테이너에 설정을 할 수가 없다.
  * 클라이언트의 요청을 수행하는 기능을 수행하는 컨트롤러나 이런 클래스들을 메모리에 띄워놔야 기능 수행이 가능하니깐
  별도로 메모리에 띄울 수 있게 스프링 컨테이너에 로딩되는 xml에 빈 등록을 하면 메모리에 띄울 수 있으니깐 스프링 컨테이너가 필요한 것이다.
#### 컨트롤러 클래스
* 반환 타입을 ModelAndView로 바꾸고 아래 코드를 넣어주면 된다.
```groovy
ModelAndView mav = new ModelAndView();
mav.setViewName("forward:getBoardList.do");
return mav;
```
#### 스프링컨테이너가 로딩하는 xml 파일
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	
	<!-- 모든 컨트롤러 클래스들을 bean 등록한다. -->
	<bean id="login" class="com.rubypaper.web.controller.user.LoginController"></bean>
	<bean id="logout" class="com.rubypaper.web.controller.user.LogoutController"></bean>
	<bean id="getBoardList" class="com.rubypaper.web.controller.board.GetBoardListController"></bean>
	<bean id="getBoard" class="com.rubypaper.web.controller.board.GetBoardController"></bean>
	<bean id="insertBoard" class="com.rubypaper.web.controller.board.InsertBoardController"></bean>
	<bean id="updateBoard" class="com.rubypaper.web.controller.board.UpdateBoardController"></bean>
	<bean id="deleteBoard" class="com.rubypaper.web.controller.board.DeleteBoardController"></bean>
	
	<!-- 클라이언트의 요청을 어떤 컨트롤러가 처리할 지 HandlerMapping으로 매핑한다.  -->
	<bean id="handlerMapping" class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
	   <property name="mappings">
	      <props>
	          <prop key="/login.do">login</prop>
	          <prop key="/logout.do">logout</prop>
	          <prop key="/getBoardList.do">getBoardList</prop>
	          <prop key="/getBoard.do">getBoard</prop>
	          <prop key="/insertBoard.do">insertBoard</prop>
	          <prop key="/updateBoard.do">updateBoard</prop>
	          <prop key="/deleteBoard.do">deleteBoard</prop>        	          
	      </props>
	   </property>
	  
	</bean>
	<!-- 아이디를 변수명 처럼 작성하면 되지만... 이 클래스는 id가 고정되어있다  handlerMapping 이걸로 해줘야한다 꼭 꼭 생각해야함  -->
	<!-- properties객체를 는 props로 넣어주면 된다.  -->
	
	<!-- ViewResolver를 등록한다.  -->
	<!-- 브라우저는 절대 서버가 관리하는 프로젝트의 WEB-INF폴더에 접근할 수 없다. 따라서 브라우저가 직접 접근해서는 안되는 파일은  WEB-IN폴더에 은닉한다. -->
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	     <property name="prefix" value="/WEB-INF/board/"></property>
	     <property name="suffix" value=".jsp"></property>
	</bean>
</beans>
``` 

* SimpleUrlHandlerMapping은 prop태그를 이용해 요청이 Key값(key="/getBoardList.do")로 들어오면 id가 getBoardList로 매핑하는 것이다.
* 컨테이너에서 제공해주는 객체를 이용하면 xml로 설정만 변경하면 되니 더 효과적이다. 
#### 주의!
* 개발자가 직접 만드는 클래스들을 빈으로 등록할 때 id를 변수명 규칙과 비슷한 규칙안에서 아무렇게나 작성하는건 상관이 없지만,
스프링 컨테이너에서 제공하는 클래스들을 빈으로 등록할 때는 끝에 두 단어를 첫글자를 소문자로 변환후 id로 설정해야 한다.
그렇지 않으면 에러가 난다. 주의해야 한다!
* 브라우저는 절대 서버가 관리하는 프로젝트의 WEB-INF폴더에 접근할 수 없다,
따라서 브라우저가 직접 접근해서는 안되는 파일은 WEB-INF폴더에 숨겨놓으면 된다.
  * 서버에서 해당 파일명으로 URL로 접근하려고 하면 해당 파일을 찾을 수 없도록 에러가 뜬다.
  
* 스프링 컨테이너에서 로딩하는 파일이 `action-layer.xml`로 고정이 되어있는데 파일명을 편하게 변경하고, Web.xml에서 달라진 파일명을 참조하도록 바꿀 수 있다.

```xml
<!-- Local parameter는 서블릿의 init() 메소드에서 ServletConfig를 통해 추출할 수 있다.  -->
<init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>/WEB-INF/config/presentation-layer.xml</param-value>
</init-param>
``` 



