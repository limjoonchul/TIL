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
#### 스프링 컨테이너가 로딩하는 xml 파일
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


## 애노테이션을 기반 SpringMVC
* 프로젝트를 진행할 때 화면을 보여주는 기능을 담당하는 Presentation layer와 비즈니스로직들이 동작하는 Business-layer로 나누어서
개발을 진행했다. 각각의 레이어마다 해당하는 xml을 따로 둬야 한다.
### Presentation.xml 설정
* 모든 등록한 빈 객체들과 HandlerMapping을 삭제하고 ViewResolver만 남겨놨다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd">
	
	<!-- ViewResolver를 등록한다.  -->
	<!-- 브라우저는 절대 서버가 관리하는 프로젝트의 WEB-INF폴더에 접근할 수 없다. 따라서 브라우저가 직접 접근해서는 안되는 파일은  WEB-IN폴더에 은닉한다. -->
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	     <property name="prefix" value="/WEB-INF/board/"></property>
	     <property name="suffix" value=".jsp"></property>
	</bean>
	
	<context:component-scan base-package="com.rubypaper.web"></context:component-scan>

</beans>
```
### Business xml을 분리
* 비즈니스 xml에 많은 기능을하는 코드들이 들어가 있어서 각 기능만 담당하도록 xml을 분리했따.
* 비즈니스 로직 설정 xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd">
	
	<!-- 컴포넌트 스캔 설정 -->
	<context:component-scan base-package="com.rubypaper.biz"></context:component-scan>
	
     <!-- DAO는 실제로 사용할 클래스 하나만 등록한다. -->
	<bean id="boardDAO" class="com.rubypaper.biz.board.BoardDAOSpring"></bean>
	<bean id="userDAO" class="com.rubypaper.biz.user.UserDAOSpring"></bean>
    
	<!-- Annotation 기반의 AOP 설정 -->
	<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
	
    <!-- Spring JDBC 설정 -->
    <bean class="org.springframework.jdbc.core.JdbcTemplate">
       <property name="dataSource" ref="dataSource"></property>
    </bean>
</beans>
```

* 트랜잭션 설정 xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
	http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
	http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
	http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd"
	>
	
	<!-- 트랜잭션 자동 설정  새로운종류의 작업을 지시하겠다.-->
	<!-- TransactionManager 등록
	  TransactionManager는 데이터베이스 연동 기술에 따라 다른 클래스를 등록해야 한다.
	  예를 들어 Hibernate를 이용하여 DAO 클래스를 구현했다면 HibernateTransactionManager를 등록해야 한다.
	  그리고 모든 TransactionManager 클래스들은 FlatformTransactionManager 인터페이스를 구현하고 있다. 
	-->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
    <!-- 여기서 데이터소스는 비즈니스 레이어에 있어서 클라이언트가 둘다 xml을 읽어들이면돼  -->
    
    <!-- 트랜잭션 Advice 클래스 등록
     tx 접두사로 시작하는 태그를 적절히 설정하면 스프링 컨테이너가 자동으로 트랜잭션 관리 Advice 객체를 생성해준다.
     유일하게 트랜잭션 관리 Advice 클래스만 우리가 구현하지 않는다.
      -->
    <tx:advice id="txAdvice" transaction-manager="txManager">
       <tx:attributes>
            <tx:method name="*" rollback-for="Exception"/>
       </tx:attributes>
    </tx:advice>
    <!-- * 조인포인트만으론 어드바이스가동작하지 않음 포인트컷이 있어야됌
* 포인트컷과 어드바이스를 연결하는 aspect가 필요함  -->

   <!-- 비즈니스메소드와 txAdvice를 연결하기 위한 aop 설정
     주의! 트랜잭션 aop 설정에서는 aspect 태그 대신 advisor 태그를 사용해야 한다.  -->
   <aop:config>
      <aop:pointcut id="txPointcut" expression="execution(* com.rubypaper.biz..*Impl.*(..))"/>
      <aop:advisor pointcut-ref="txPointcut" advice-ref="txAdvice"/>
   </aop:config>
</beans>
```

* DataSource 설정 xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:p="http://www.springframework.org/schema/p"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-3.0.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-3.0.xsd">
	<!-- 외부 프로퍼티 파일 로딩하기 -->
	<context:property-placeholder location="classpath:database.properties" />
	
    <!-- DataSource 설정  -->
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
        destroy-method="close">
          <property name="driverClassName" value="${jdbc.driverClass}"></property>
          <property name="url"             value="${jdbc.url}"></property>
          <property name="username"        value="${jdbc.username}"></property>
          <property name="password"        value="${jdbc.password}"></property>     
    </bean>
</beans>
```
* DB연결 정보를 따로 파일로 만들어서 설정해두었다.
  * 이유는 이렇게 하면 다른 DB로 바꿀 때 이 파일만 수정하면 되서 유지보수성이 높아진다.
```properties
jdbc.driverClass="org.h2.Driver"
jdbc.url= "jdbc:h2:tcp://localhost/~/test"
jdbc.username = "sa"
jdbc.password= ""
```

### Controller Class
#### @Controller
* 클래스에 `@Controller`를 써서 해당 클래스를 메모리에 생성하고 Controller 객체로 인식하도록 한다.
* Controller를 POJO 스타일로 코딩할 수 있다.
  * 포조 스타일로 했을 때 인터페이스를 implements 안해도 되고, 파라미터도 마음대로 작성할 수 있다.
```java
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService; // 이 타입의 객체가 메모리에 없으면 애러 있으면 객체의 주소를 할당한다.
}
```
#### @RequestMapping
* `@RequestMapping("/getBoard.do")`를 메소드에 붙여주면 `/getBoard.do`로 요청이 들어올 때 바로 밑의 해당 메소드와 매핑시켜 메소드를 실행시킨다.
* `@RequestMapping`이 주석 처리되어 있으면 스프링 컨테이너에서 해당 메소드를 인식하지 못한다. 
##### 사용자 입력 값 자동 세팅
![자동세팅1](/Java/documents/images/사용자입력값자동세팅.jpg)

![자동세팅2](/Java/documents/images/자동세팅2.jpg)

* 아래 메소드 예처럼 `BoardVO vo`를 메소드의 파라미터로 넣어주면 서블릿의 Request, Response 객체처럼 스프링 컨테이너에서
이 메소드를 호출해서 사용하는 것으로 BoardVO 객체를 생성해서 넣어준다.(프레임워크의 역할이 어디까지인지 알고 있는게 중요!)
* BoardDAO, HTTPSession같은 클래스도 파라미터로 넣으면 컨테이너가 객체를 생성해서 넣어준다.
POJO이기 때문에 파라미터를 몇개를 넣어도 강제성이 없기 때문에 상관없고, 
이렇게 될 시에 getPrameter, vo.setXXX() 같은 코드를 컨테이너에서 알아서 생성해주기 때문에 작성할 필요가 없어져서 코드가 짧아지고, 유지보수가 편해진다.
* 위처럼 컨테이너에서 알아서 VO객체를 세팅해주는 기능을 하기 위한 전제 조건
  * JSP파일에 있는 name의 값과 VO객체의 setXXX()의 이름이 일치해야 한다.
  * 이 규칙이 지켜지면 내부적으로 Setter Injection이 동작한다.
  * 꼭 참조형 타입이 아닌 기본형 타입으로 값을 넣어도set과 일치하기만 하면 가능하다.  
```java
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService; // 이 타입의 객체가 메모리에 없으면 애러 있으면 객체의 주소를 할당한다.
	// 글 상세
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println("게시글 상세 조회 기능 처리");

		BoardVO board = boardService.getBoard(vo);
		System.out.println("글 상세 조회에서의 BoardVO  객체 정보");
		System.out.println(vo.toString());
		model.addAttribute("board", board);

		return "getBoard";
	}

	// 글 목록
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo,Model model) {
		System.out.println("게시글 목록 기능 처리");

		if (vo.getSearchCondition() == null)
			vo.setSearchCondition("TITLE");
		if (vo.getSearchKeyword() == null)
			vo.setSearchKeyword("");

		model.addAttribute("boardList", boardService.getBoardList(vo));
		model.addAttribute("search", vo);

		return "getBoardList";

	}
}
```
#### 요청 방식에 따른 @RequestMapping
* `@RequestMapping(value="/insertBoard.do", method=RequestMethod.GET)`처럼 안에 내용을 이렇게 바꾸주면 
요청 방식이 GET, POST에 따라 다른 메소드 호출을 할 수 있다. (서블릿에서 doGet(),doPost() 방식과 같이)
* 이런 방식을 적용할 수 있는 예시로는 사용자가 로그인을 하고 어떤 물건을 구매할 때 구매페이지로 가게 되면 로그인해서 등록되어있는 
사용자의 이름이라던지, 전화번호, 주소 등과 같은 정보들이 채워져 있는 화면을 봤을텐데 이런 곳에 이용할 수 있다. 
아래 GET부분에 set으로 값을 설정해놓고 실행시켜서 글 등록에 들어가게 되면, 아래 내용이 채워진 글 등록 페에지를 볼 수 있다.
* 매개변수로 받은 vo 객체는 자동으로 request 내장 객체에 등록된다.
* 따라서 최종적으로 실행되는 화면(jsp)에서 EL을 이용하여 값을 뿌릴 수 있다.
  * EL에서 request, session에 있는 데이터들을 확인해서 값이 있으면 그 값들을 가져오니깐
````java
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService; // 이타입의 객체가 메모리에 없으면 애러 있으면 객체의 주소를 할당한다.
	
	// 글등록 화면 이동
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.GET)
	public String insertBoardView(BoardVO vo) {
		
		vo.setTitle("200자 미만으로..");
		vo.setWriter("테스터");
		vo.setContent("2000자 미만으로 작성해주세요.");
		return "insertBoard"; // 포워드: 하고 띄워쓰기하면 안됨~

		// jsp에ㅐ서 el로 해주면 boardVO로 받는다.
	}
	// 똑같은 insetboard.do 로 왔을대 get/post방식이냐에 따라서 다르게 동작시킴.
	// 메소드가 있고 없고의 차이때문에 오버로딩으로 식별은 되지만 이름을 다르게 해줌.
	
	// 글등록
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(BoardVO vo ) {
		boardService.insertBoard(vo);
		return "forward:getBoardList.do"; // 포워드: 하고 띄워쓰기하면 안됨~
	}
}
````
* 아래 View 파일에도 `${boardVO.title}` 설정을 해줘서 값을 받을 수 있게 설정해야 한다.
```html
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>새글등록</title>
</head>
<body>
<center>
	<h1>새글 등록</h1>
	<hr>
		<form action="insertBoard.do" method="post">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left"><input type="text" name="title" value="${boardVO.title}"/></td>
			</tr>
			<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left"><input type="text" name="writer" size="10" value="${boardVO.writer}"/></td>
			</tr>
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left"><textarea name="content" cols="40" rows="10">${boardVO.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value=" 새글 등록 "/></td>
			</tr>
		</table>
		</form>
	<hr>
</center>
</body>
</html>
```

### ModelAndView
* 검색 결과를 Session에 담아서 값을 보내면 안된다.
  * 이유는 세션은 세팅이되면 서버가 내려가기전까지 계속 메모리에 남아있기 때문에 계속 값이 보내지게 되면
  메모리 용량이 커지게되서 메모리 낭비가 된다.
  * 그래서 Request에 검색결과를 보내줘야 하는데 ModelAndView에 검색결과를 저장하면 자동으로 request에 저장이 된다.
   (request는 response를 하면 소멸되니깐)
* addObject()메소드에 KEY,VALUE 쌍으로 보내면 Request 객체에 똑같이 KEY,VALUE 쌍으로 저장이 된다.
* 포조로 작성한 코드라도 리턴값을 String 통일하는게 좋은데, ModelAndView을 사용했을 때 String으로 통일을 할 수가 없다.
그래서 Model을 사용한다.  
```groovy
@RequestMapping("/getBoardList.do")
public ModelAndView modelAndView(BoardVO vo,ModelAndView mav) {
	System.out.println("게시글 목록 기능 처리");

	if (vo.getSearchCondition() == null)
		vo.setSearchCondition("TITLE");
	if (vo.getSearchKeyword() == null)
		vo.setSearchKeyword("");
	// 절대 검색 결과는 세션에 저장해서는 안된다. 검색 결과는 request에 등록해야 한다!!
	// ModelAndView나 Model 객체에 검색 결과를 등록하면 자동으로 request에 등록해준다. 중요한 개념이다!!!! 
	// 그래야 세션을 남용하지 않는다.
	mav.addObject("boardList", boardService.getBoardList(vo));
	mav.setViewName("getBoardList");

	return mav;
}
```
### Model
* ModelAndView와 똑같지만 반환값을 String으로 해줄 수 있다.
* 뷰는 사용할 수 없다.
* 절대 검색 결과는 세션에 저장해서는 안된다. 검색 결과는 request에 등록해야 한다!!
* ModelAndView나 Model 객체에 검색 결과를 등록하면 자동으로 request에 등록해 준다.(중요한 개념!!) 
그래야 세션을 남용하지 않는다.
```groovy
@SessionAttributes("board")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService; // 이타입의 객체가 메모리에 없으면 애러 있으면 객체의 주소를 할당한다.

	// 글 수정
	// @ModelAttribute는 세션에 "board"라는 이름으로 데이터가 등록되어있다면 그 객체를 vo변수에 바인딩해라 라는 의미이다. 없으면 패스
	@RequestMapping("/getBoardList.do")
    public String getBoardList(BoardVO vo,Model model) {
    	System.out.println("게시글 목록 기능 처리");
    
    	if (vo.getSearchCondition() == null)
    		vo.setSearchCondition("TITLE");
    	if (vo.getSearchKeyword() == null)
    		vo.setSearchKeyword("");

    	model.addAttribute("boardList", boardService.getBoardList(vo));
    	model.addAttribute("search", vo);
    
    	return "getBoardList";
    }
}
```
### @ModelAttribute, @SessionAttributes()
* JSP 파일에서 제목과 내용만 수정하게 하고, WRITER에 대한 부분은 수정하지 않게 구성을 해놓고,
* BoardDAO의 UPDATESQL을 WRITER를 추가해서 수정해서 4개의 파라미터를 전달 받게 하고,
update메소드로 가서 stmt.setXXX()도 4개 설정을 해준 다음에  업데이트를 했을때 작성자의 값이 NULL값이 된다.

![ModelAttribute](/Java/documents/images/ModelAttribute.jpg)

#### Null이 나오는건 당연하지만, 기존에 작성했던 사람의 데이터를 그대로 가져가게 할 순 없을까?
* 이런 문제를 해결할 수 있는 방법으로 @ModelAttribute, @SessionAttributes()를 사용하면 된다.
* 수정을 하려면 설계상 상세조회를 클릭해서 들어가면 글 수정하는 화면이 나오게 되어있다.
그래서 상세조회를 클릭해서 들어가면 getBoard()가 호출이 되고, 
> BoardVO board = boardDAO.getBoard(vo);
> model.addAttribute("board", board);
* 위처럼 작성해주면 model에 상세조회했던 제목, 작성자, 내용, 날짜, 조회수 등의 모든 정보가 담기게 된다.
* 그리고 클래스의 `@SessionAttributes("board")`를 써주면 Model에 `board`로 담긴 정보들이 세션에도 담기게 된다.
* 그럼 다음 글을 수정하고 수정 버튼을 누르면 `public String updateBoard(@ModelAttribute("board") BoardVO vo) `처럼 설정을 해줘서
vo객체에 getBoard에서 보냈던 `board`내용들이 전달되서 담기게 되고, 기존 내용에서 수정된 내용들만 수정되서 객체 자체로 전달 되는 것이다.

* @ModelAttribute("board")이 있으면 세션에 담긴 정보들이 vo에 담기게 된다.
그리고 수정했던 내용들만 그위에 변경이되서 vo에 담겨서 업데이트가 이루어진다.
* @SessionAttributes를 이용하면 특정 이름으로 Model에 저장된 데이터를 세션에도 등록되도록 한다.
따라서 getBoard 메소드에서 Model에 "board"라는 이름으로 BoardVO 객체를 저장하면 
세션에도 "board" 라는 이름으로 BoardVO 객체가 등록되는 것이다. 배열 형태로도 여러 개 등록 할 수 있다.
```java
@SessionAttributes("board")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService; // 이 타입의 객체가 메모리에 없으면 애러 있으면 객체의 주소를 할당한다.

	// 글 상세
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {
		System.out.println("게시글 상세 조회 기능 처리");
		BoardVO board = boardService.getBoard(vo);
		System.out.println("글 상세 조회에서의 BoardVO  객체 정보");
		System.out.println(vo.toString());
		model.addAttribute("board", board);

		return "getBoard";
	}

	// 글 수정
	// @ModelAttribute는 세션에 "board"라는 이름으로 데이터가 등록되어있다면 그 객체를 vo변수에 바인딩해라 라는 의미이다. 없으면 패스
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {

		System.out.println("게시글 수정 기능 처리");
		System.out.println("수정에서의 BoardVO  객체 정보");
		System.out.println(vo.toString());
		boardService.updateBoard(vo);
		return "forward:getBoardList.do";

	}
}
```

## Layer 통합
![레이어연결](/Java/documents/images/Layer연결.jpg)

1. 서버를 실행시키면 web.xml을 로딩해서 서블릿 컨테이너가 생성이 된다.
2. contextloaderlistener 객체를 만든다. listener는 business-*.xml을 로딩해서 첫번째 스프링 컨테이너인 xmlwebapplicationcontext를 생성한다.
3. 웹인 환경에서 사용할 수 있는 컨테이너이다, 웹이 아닌 환경에서 사용할 수 있는것이 generic....
4. ServiceImpl 객체(service)와 Dao 객체(repository)가 메모리에 뜬다.
5. 서비스 객체가 dao를 참조할 수 있도록 autowired가 되어있다. 자동으로 객체간의 관계를 설정하는 ioc로 설정되어있다.
6. 서비스 임플에 포인트컷을 설정해 놓고 어드바이스의 서비스를 또 생성해 놓고, 이 두개를  apect  또는 advisor로 연결시켜 관리한다. 메모리에 뜬다
여기까지가 서버를 껐다 켰을 때 이런 동작이 이루어진다.
7. 그 다음 브라우저에서 .do라고 요청이 오면 서블릿 컨테이너가 dispatcherservlet 객체를 생성한다.(lazy)
리스너가 먼저 메모리에 뜰 수밖에 없는 구조이다.
8. 디스패처서블릿의 init메소드가 오버라이드 되어있고 이 메소드가 presentation-layer.xml을 로딩한다. 
9. 그럼 두번째 스프링 컨테이너를 생성한다. (xmlwebapplicationcontext)
10. 프레젠테이션xml을 로딩했기 때문에 컨트롤러 객체들만 메모리에 띄운다. Autowired로 컨트롤러가 서비스임플을 참조한다.

* 각 컨테이너의 객체 관리
  * 서블릿 컨테이너는 리스너 ,서블릿, 필터를 관리한다.
  * 스프링 컨테이너 1 은 Service와 DAO, aspect를 관리한다.
  * 스프링 컨테이너 2 는 Controller를 관리한다.
  
```xml
<!-- 스프링 프레임워크에서 제공하는 ContextLoaderListener 클래스를 등록한다.(pre-loading)  -->
<!-- 글로벌 파라미터  -->
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:business-*.xml</param-value>
</context-param>

<listener>
   <listener-class>
          org.springframework.web.context.ContextLoaderListener
   </listener-class>
</listener>
```

* 레이어를 나눠서 개발한다는 것은 하나의 레이어가 변경되었을 때
다른 하나에 영향이 가지 않아야 한다
* 그래서 비즈니스레이어와 프레젠테이션 레이어를 따로따로 운영할줄 알아야한다 그럴려면 xml이 각각 필요하다.
레이어당 xml은 따로따로 있어야 한다.


## 부가적인 기능
### FileUpload
* 파일을 업로드할 수 있는 기능을 할 수 있다.

#### xml 파일 업로드 설정
* 스프링 컨테이너는  CommonsMultipartResolver 객체를 이용하여 MultipartFile 객체를 생성한다.
* value에 파일의 최대 size를 설정한다, size를 넘어가는 것이 들어오면 exception이 발생한다 무한대로 두고싶다면 -1를 주면 된다.
```xml
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
     <property name="maxUploadSize" value="10000000"></property>
</bean>
```
#### jsp 파일 설정
*  글 등록 화면을 파일 업로드 가능한 화면으로 수정한다.
* <form> 시작 태그에 method="post" GET 방식은 파일 업로드를 지원하지 않는다,
그리고 enctype="multipart/form-data" 속성이 설정되어야 이 form이 업로드 기능을 지원하는 것이다.
* 파일을 업로드하는 <tr> row를 추가해 준다. 입력되는 파라미터가 늘어서 BoardVO도 변수에 MultipartFile를 추가해야한다.
* MultipartFile 객체는 스프링에서 지원한다, 이 객체에는 사용자가 업로드한 파일의 모든 정보(파일의 이름, 경로, 바이트 배열 등이 담겨있다.)
  * 스프링 컨테이너는 CommonsMultipartResolver 객체를 이용하여 MultipartFile 객체를 생성한다.
```html
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>새글등록</title>
</head>
<body>
<center>
	<h1>새글 등록</h1>
	<hr>

		<form action="insertBoard.do" method="post" enctype="multipart/form-data">
		<table border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left"><input type="text" name="title" value="${boardVO.title}"/></td>
			</tr>
			<tr>
				<td bgcolor="orange">작성자</td>
				<td align="left"><input type="text" name="writer" size="10" value="${boardVO.writer}"/></td>
			</tr>
			<tr>
				<td bgcolor="orange">내용</td>
				<td align="left"><textarea name="content" cols="40" rows="10">${boardVO.content}</textarea></td>
			</tr>
			<tr>
				<td bgcolor="orange">업로드</td><td align="left">
				<input type="file" name="uploadFile"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value=" 새글 등록 "/></td>
			</tr>
		
		</table>
		</form>
	<hr>
</center>
</body>
</html>
```

#### Controller Class 설정
* jsp파일의 입력을 받는 input의 name과 매핑되는 vo 객체의 setUploadFile에 컨테이너가 Setter Injection을 해줘서 정보를 담아서 vo객체를 생성한다.
그래서 클라이언트가 입려한 값을 vo 객체에서 받아서 사용할 수 있다. 
* 스프링이 메모리에 올려 놓더라도 CommonsMultipartResolver 의 id가 잘 못 되어 있으면 스프링에서 인지하지 못한다.
 CommonsMultipartResolver 없으면 multipartfile 객체 생성이 안된다.
* transferTo() 메소드에서 파일을 업로드하는 동작이 이루어진다. 파일을 동적으로 업로드할 수 있다.  
```java
@SessionAttributes("board")
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService; // 이 타입의 객체가 메모리에 없으면 애러 있으면 객체의 주소를 할당한다.

	// 글등록
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(BoardVO vo) throws Exception {
		
		// 파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {// 파일 업로드 정보가 하나라도 있다면
			uploadFile.transferTo(new File("C:/DEV/upload_files/" + uploadFile.getOriginalFilename())); // 파일명 문자열로 리턴		
		}
				
		boardService.insertBoard(vo);
		return "forward:getBoardList.do";
	}
}
```

### 예외처리 화면 설정
* After-Throwing은 비즈니스 로직이 실행할 때 예외가 발생했을 때 적절한 예외를 하는 것이고
지금하는 것은 화면에서의 예외처리를 한 것이다. 이 둘은 다른 것이다.

#### xml파일 설정
* SimpleMappingExceptionResolver으로 예외처리를 하는데 예외가 `java.lang.ArithmeticException`일 때 `error/arithmeticError`를 실행하는 것이다. viewresolver를 고려해서 설정해야 한다.
* prop을 추가해서 예외에 따라 다른 화면이 보여질 수 있게 할 수 있다.
* defultError는 따로 작성해 놨는데 우리가 알지 못하는 에러가 나왔을 때 기본 화면을 제공하기 우해서 따로 property로 놔둔 것이다.
```xml
<!-- 예외 화면 처리 설정  -->
<bean id="exceptionResolver" class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
  <property name="exceptionMappings">
     <props>
     <!-- 에러 전용 JSP파일을 등록할 때는 ViewResolver 설정을 고려해야 한다. -->
        <prop key="java.lang.ArithmeticException">error/arithmeticError</prop>
        <prop key="java.lang.ArithmeticException">error/nullPointerError</prop>
     </props>
  </property>
  <property name="defaultErrorView" value="error/defaultError"></property>
</bean>
```

#### ControllerClass 설정
* 이렇게 일부로 에러를 발생하는 코드를 설정해놨다. 여기서 에러가 나면 에러화면을 보여주는 jsp 파일로 넘어가서 그 화면이 출력된다.
```java
@Controller
public class LoginController{
	
	@RequestMapping(value = "/login.do", method=RequestMethod.GET)
	public String login(UserVO vo) {
		System.out.println("로그인 기능 처리");
		System.out.println(9/0); // error
		vo.setId("admin");
		vo.setPassword("admin");
		return "login";

	}
}
```

#### error화면이 발생 했을 때 처리 화면
* 에러가 났을 때 따로 구현한 화면을 보여질 수 있게 view를 구현해서 이 jsp파일이 렌더링될 수 있게 한다.
```html
<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<title>Error</title>
</head>
<script language="javascript">
</script>
<body bgcolor="#ffffff" text="#000000">

<!-- 타이틀 시작 -->
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="93%" bgcolor="orange">문제 발생!(java.lang.ArithmeticException)</td>
	</tr>
</table>
<!-- 타이틀 끝 -->
<br>
<table width="100%" border="1" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td align="center">
			<table width="500" border="0" cellspacing="0" cellpadding="0">
				<tr><td>Message:</td><td></td><td></td></tr>
				<tr>
					<td width="50">&nbsp;</td>
					<td width="400">${exception.message}</td>
					<td width="50">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
```

### 다국어 화면 설정
* 각 접속하는 국가마다 다른 언어를 제공하는 기능들이 있는데 그걸 설정해보는 것이다.
* Properties 파일에 언어별로 메세지를 작성하는 파일을 둔다.

![message](/Java/documents/images/message.jpg)
### Properties 파일 설정
```properties
user.login.mainTitle=LOGIN
user.login.welcomeMsg=Member LOG-IN(english)
user.login.id=ID
user.login.password=PASSWORD
user.login.loginBtn=Login GO
user.login.langLink.ko=KOREA
user.login.langLink.en=ENGLISH
```


#### xml 파일 설정
1. MessageSource 등록: 언어별로 작성한 메세지 파일(properties)들을 메모리에 로딩한다.
    * properties 확장자는 빈에 등록할 때 생략하고 작성한다. 마지막 . 을 기준으로 왼족은 패키지명, 오른쪽은 파일명이 된다.
    * 컴포넌트 하나당 따로따로 메세지 파일을 하나씩 만들어야한다.
      * 근데 만약 컴포넌트 하나당 여러개의 언어들을 지원한다고하면 각 언어별로 properties 파일을 만들어야 하니깐
      너무 많은 파일이 생성되고 언어를 삭제하고 추가할 때마다 파일을 삭제 추가해야하기 때문에 properteis 확장자는 뺀다.
      * 하나만 등록해 놓으면 메세지 소스에 관한 모든 파일을 `ResourceBundleMessageSource`이  알아서 등록한다.
```xml

<bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
    <property name="basenames">
         <list>
            <value>message.messageSource</value>
         </list>
    </property>
</bean>
```

2. LocaleResolver 등록: 브라우저가 전송해준 Locale을 지속적으로 유지해주는 객체
* 아래 3가지 클래스를 기억해야 한다. 
   * AcceptHeaderLocaleResolver: 요청할 때마다 브라우저의 Locale을 체크해서 언어를 자동으로 변경한다.(엄청 느리다)
   자동으로 언어가 바뀐데 느리다.
   * SessionLocaleResolver : 한번 전송된 Locale을 세션에 등록하고 세션이 종료될 때까지 지속적으로 유지한다. 주로 이걸 사용한다.
   * FiexedLocaleResolver : 특정 언어로 고정해버리겠다. 언어를 중간에 바꾸어야해서 거의 사용하지 않는다.

```xml
<bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver"></bean>
```

3. LocaleChangeInterceptor 등록 : 중간에 언어를 변경하는 객체 
```xml
<mvc:interceptors>
   <bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
      <property name="paramName" value="lang"></property>
   </bean>
</mvc:interceptors>
```

#### jsp 파일 설정
* <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 이걸 추가 해 줘야 한다.
* 언어가 바뀌는 부분에 <spring:message code="user.login.welcomeMsg"/>처럼 작성하는데 
code = "" 부분은 Prperties에 작성한 key값을 넣어주면 된다.
```html
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title><spring:message code="user.login.mainTitle"/></title>
</head>
<body>
<center>
<h1><spring:message code="user.login.welcomeMsg"/></h1>
<a href="login.do?lang=ko"><spring:message code="user.login.langLink.ko"/></a>&nbsp;&nbsp;&nbsp;
<a href="login.do?lang=en"><spring:message code="user.login.langLink.en"/></a>
<hr>
<form action="login.do" method="post">
<table border="1" cellpadding="0" cellspacing="0" width="200">
<tr>
  <td bgcolor="orange"><spring:message code="user.login.id"/></td>
  <td><input type="text" name="id" size ="10" value="${userVO.id}"></td>
</tr>
<tr>
  <td bgcolor="orange"><spring:message code="user.login.password"/></td>
  <td><input type="password" name="password" size ="10" value="${userVO.password}"></td>
</tr>
<tr>
  <td colspan="2" align="center"><input type="submit" size ="20" value="<spring:message code="user.login.loginBtn"/>"></td>
</tr>
</table>
</form>
</center>
</body>
</html>
```

### 데이터 변환 설정
* XML은 텍스트형태의 파일이기때문에 텍스트를 지원하지 않은 플랫폼은 존재하지 않는다
텍스트를 처리 못 하는 프로그램도 존재 하지 않는다.
* 그래서 텍스트형태의 XML로 데이터를 보내고받으면 개발언어에 상관없이데이터를 처리할 수 있었다. 근데 XML은 사이즈가 너무 크다.
시작태그와 종료태그로 감싸야하기 때문에 무겁다.
* 경량화된 데이터포맷 JSON으로 하는 것이다. `"age":30` 이 형식의 데이터
* 예전에는 3개의 클래스를 빈등록했어야 하는데  요샌 `<mvc:annotation-driven/>`이 설정 하나만 해 놓으면
검색결과인 VO객체를 JSON포맷 데이터로 변환해준다.


```xml
<!-- 데이터 변환 설정 : 검색 결과(VO 객체)를 JSON형태의 데이터로 변환한다.-->
<mvc:annotation-driven/>
```

* JSON으로 변환된 값들을 화면에 보이지 않게 할려고 하면
`@JsonIgnore`을 검색결과인  VO 객체의 getXXX()에 붙이면 된다.
```java
@Data
public class BoardVO {
	// 테이블의 컬럼 이름(데이터 타입까지)과 동일한 멤버변수를 private로 선언한다.
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	private String searchCondition;
	private String searchKeyword;
	
	@JsonIgnore
	public String getSearchCondition() {
		return searchCondition;
	}
	@JsonIgnore
	public String getSearchKeyword() {
		return searchKeyword;
	}

	@JsonIgnore
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
}
```