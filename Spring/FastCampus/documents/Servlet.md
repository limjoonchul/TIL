# Servlet
* 서블릿을 이해하면 자바의 서버사이드쪽의 동작원리를 이해하는데 많은 도움이 된다.

## 이클립스에서 프로젝트 설정
* lib폴더에 외부 라이브러리 jar파일(h2, lombok)을 넣으면 라이브러리스 폴더 하위에 `Web App Libraries`에 생긴다
  그래야 jar 파일에 있는 클래스를 우리가 만든 클래스에서 이용할 수 있다.
### 이클립스에서 servlet-mapping하는 방법
  1. 프로젝트에서 src폴더를 우클릭해서 서블릿을 누른다
  2. 패키지명, 클래스명을 입력하고 next 누르면 클래스명(HelloServlet)이 name(HelloServlet)으로 되어있다.
  3. 클래스명을 소문자(hello)로 바꾼다.  
  4. URL Mappings의 /hello를 눌러서 원하는데로 바꾸고(/hello.do) 피니시 누르면 된다.
  그러면 web.xml의 servlet-mapping이 생겨져있다.
  ```xml
    <servlet>
      <servlet-name>hello</servlet-name>
      <servlet-class>hello.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
      <servlet-name>hello</servlet-name>
      <url-pattern>/hello.do</url-pattern>
  ```
  * 브라우저가 /hello.do로 요청을 하면 hello.HelloServlet이 클래스를 찾아서 실행해라 라는 의미이다.

### 프로젝트를 서버에 등록하는 방법
1. 하단에 servers에서 tomcat에 오른쪽 키 add/remove를 누른다
2. 창이 뜨면 해당 파일을 눌러서 add를 클릭한다.
3. 오른쪽 configured로 add를 해주고 끝내면, 톰캣 서버가 이 프로젝트를 관리 해준다.
* servers창에서 톰캣을 누르면 프로젝트가 들어가 있는걸 확인할 수 있다. 
  * 단, 다이나믹 웹 프로젝트로 만들어져 있어야 등록 가능하다.

### 톰캣이란
* 톰캣은 아파치 재단에서 만든 자바로 되어있는 웹 어플리케이션 서버이다.
## Servlet
* 자바를 사용하여 웹페이지를 동적으로 생성하는 서버측 프로그램을 말한다.
* 웹 서버의 성능을 향상시키기 위해 사용되는 자바 클래스의 일종이다.
  * JSP와 비슷하지만 JSP는 HTML문서 안에 Java코드를 포함하고 Servlet은 Java 코드안에 HTML을 포함한다.
* 서블릿은 자바 기술은 맞지만 톰캣 도움 없인 절대 실행할 수 없다.
### 서블릿 클래스 작성 규칙
* 외울 필요는 없고 이런 규칙으로 작성된다는 것만 알아두면 된다. IDE에서 서블릿으로 클래스를 생성하게 되면 자동으로 이 규칙에 맞게 생성해 준다.
 1. HttpServlet 클래스를 상속해야 한다. - 서블릿으로 인지한다.
 2. public 클래스로 만들어야 한다. - 그래야 서블릿 엔진에서 접근할 수 있으니깐.
 3. default 생성자가 있어야 한다. - 안 만들어도 자동 제공
 4. 요청 방식(method)에 따라 doGet이나 doPost를 재정의(Overriding)한다.
 5. 부모(HttpServlet) 클래스의 메소드를 재정의 하지 않으면 상속된다.
 
### 서블릿의 특징
* 자바 클래스이고, ServletEngine에 의해서만 동작한다.
* Multi-Thread 
   * Servlet은 Multi-Thread로 동작하기 때문에 효율적이다.
   * 서블릿은 멀티스레드로 동작하기 때문에 웹에서 매우 효율적이다.
   * 서블릿을 멀티스레드로 동작시키는 것은 서블릿 엔진이 하는 것이지 개발자가 따로 구현해야 하는 것이 아니다.
   
* 플랫폼 독립성
  * 서블릿은 자바로 작성되기 때문에 플랫폼에 무관하게 동일한 실행결과를 보인다.
  
* 서버 독립성
  * 서블릿은 웹 서버와 무관하게 실행이 된다. 서블릿 규칙에 맞게 작성을 해놓으면 톰캣서버에서 되던게 제우스 같은 다른 서버에서도 똑같이 실행이 된다.
  
* 플랫폼 독립성과 서버 독립성 이 두가지로 이식성이 좋다. 
* 확장성 - 외부 프레임워크라 유틸리티 클래스라던지 서블릿 프로그램을 더 빠르게 할 수 있다.

### 서블릿의 단점
* 서블릿은 Java 코드안에 HTML을 담는 것이여서 HTML같은 코드를 바꿀려면 하나하나 바꿔 줘야 하고 자동완성 기능도 지원이 안된다.
그리고 `""` , `''` 를 같이 써야 해서 굉장히 헷갈리고 자바 문법에 어긋나서 에러가 나는 경우도 생긴다.
전체적으로 디자인을 바꾸는게 엄청 귀찮고 번거롭다.

### 톰캣 서버 구동
* 톰캣 서버를 구동하면 톰캣 객체가 생성이 되고, 그 안에 포함 되어있는 서블릿 엔진도 생성이 된다.
* 서블릿 엔진은 web.xml을 로딩하고, 스레드 풀을 생성하고, 전체 라이프 사이클을 관리한다.

* 웹 기술은 개발자는 클래스만 만들 뿐이고 객체 생성해주는 건 개발자가가 아니다.
* 서블릿 엔진이 이 객체들을 생성해주는 코드를 실행하고 메소드를 호출하는 코드를 실행시킨다. 우리 눈엔 내부 동작이 안 보이게 동작한다.
* 서블릿 엔진이 생성될 때 web.xml이 로딩한다고 했는데 개발자가 개입할 수 있는건 hello.do라고 치면 hello.servlet이라는 객체를 생성하고
객체가 가지고 있는 메소드를 호출하도록 설정만 해두는 것이다.

#### 구동 흐름
* 서버 구동 버튼을 누르면 내부적으로 `new tomcat()`이 객체 생성이 되는 것이다.
* 톰캣 객체가 생성되면서 servers 창에 로그가 뜨는데 `StartServiceCatalina` 까지가 톰캣 서버가 구동 되었다는 의미이다.
  * 이 과정에서 에러가 안나면 된다.
* 톰캣 객체가 서블릿 엔진을 생성하는 코드를 가지고 있어서 서블릿 엔진이 생성이 된다.
* 서블릿 엔진 객체가 생성이 될 때 web.xml 파일이 전달 되는 것이다. 그래서 web.xml이 중요한 것이다. `new ServletEngine(web.xml)`
* 그 다음 클라이언트의 요청이 오면 web.xml에 매핑된 정보를 가지고 매핑해서 서블릿 객체를 생성하는 서블릿 라이프 사이클이 돌아가는 것이다.
밑에 서블릿 수행 흐름이 실행이 된다.

### 서블릿 수행 흐름

![서블릿수행흐름](/Java/documents/images/서블릿수행흐름.jpg)


1. 클라이언트에서 서블릿 요청(/hello.do)이 오면 서블릿 엔진은 요청된 서블릿이 메모리에 존재하는지 여부를 판단한다.
2. 요청한 서블릿이 메모리에 없으면 서블릿도 클래스니깐 web.xml에 매핑된 hello.servlet를 객체를 찾아서 생성하고 메모리에 로딩한다.
3. 생성자를 호출한다. 무조건 디폴트 생성자를 찾아서 메모리에 뜬다.(절대 매개변수가 있는 생성자를 인식하지 못한다.)
4. Init()를 호출한다. 디폴트 생성자가 호출이 되서 서블릿 객체가 메모리에 뜨기 때문에 멤버번수를 `단 한번만` 초기화할 때 필요하다.
5. 스레드 풀에서 스레드가 랜덤으로 튀어 나와서 할당이 된다.
6. 이 스레드가 서블릿 객체의 service()를 호출한다.(스레드는 run()를 호출하게 되어있는데 내부적으로 이 run()은 service()만 호출하게 되어있다.)
   * 메모리에 올라간 서블릿 객체를 스레드 풀에 있는 스레드들이 공유한다.
7. service() 메소드를 오버라이딩 하지 않으면 부모의 것이 호출이 되는데 이건 브라우저가 서버에 요청한 방식에 따라서
doGet(), doPost() 메소드를 호출하여서 메소드 동작이 이루어진다.
   * 브라우저의 요청 방식에 대해서 확신이 있다면 해당 메소드만 오버라이딩하면 되는데, 요청 방식을 모르는 경우 service()를 이용하여 모든 요청을 다 처리하면 된다.
8. 그 다음 결과를 응답으로 클라이언트에게 보내준다.

* 처음 서블릿 요청이 들어오게 되면 위의 과정을 처음부터 수행하지만, 한 번 메모리가 할당이 되면 메모리에 존재하기 때문에
그 이후 부터는 5번부터 과정부터 시작이되는데 이때 새로운 스레드가 할당되서 실행이 이루어진다. 앞의 과정을 생략하니 수행 속도가 빠르다. 

* 그래서 어플리케이션을 서버에 올려서 배포를 할 때 한 번 동작을 시키고 배포를 한다 그래야 이용하는 클라이언트들이
모두 비슷한 실행 속도를 제공받을 수 있으니깐.

* doPost(),doGet() 메소드를 오버라이딩하면 HttpReqest, HttpResponse 객체를 매개변수로 받게 되어 있는데,
이 매개변수의 객체들도 서블릿 엔진이 생성해서 넣어주는 것이다.
  * 먼저 Request, Response 객체를 생성하고, doPost(),doGet()를 호출한다.


* 서버를 재 구동할 때 까지 서블릿 객체가 메모리에 띄워져 있다. 서버를 끊으면 서블릿 엔진이 죽어서 서블릿 객체를 메모리에서 회수하게 되어있다.
서블릿 객체가 종료되기 직전에 destroy() 메소드가 호출되면서 자원들을 회수하고 종료된다.

### HttpServletRequest, HttpServletResponse

![Servlet주요객체](/Java/documents/images/Servlet주요객체.jpg)

#### HttpServletReqeust
* 클라이언트가 전달한 정보를 추출하기 위해 사용한다.
* Request 객체는 브라우저가 서버쪽으로 보낸 요청 프로토콜이 담겨서 메소드의 파라미터로 전달된다.
  * 엄청나게 많은 정보들이 담겨져서 전송이 된다.
* 클라이언트의 요청이 와서 서블릿 객체가 생성이 될 때 생성되서 전달된다.
* 클라이언트에게 다시 응답이 되기 전까지 계속 상태 유지가 된다.(하나의 요청을 처리하는 스레드에서 용도를 다 할 때까지 존재)
* Request의 주요 사용하는 메소드
  * setCharacterEncoding() - 입력 정보가 한글로 되어있을 때 변환되게 하기 위해서 사용한다.
  * getParameter() - URLD의 ?뒤의 파라미터를 가져올 때 사용한다.
  * getSession() - 세션 정보를 가져올 때 사용한다.
  * getReuestURL() - URL 호출
  * getMethod() - 요청방식이 무엇인지 확인한다.
  * getQueryString() - 쿼리스트링을 출력한다. 
  
#### HttpServletResponse
* 프로그램의 처리 결과를 클라이언트로 보내기 위해 사용한다.
* Response 객체는 클라이언트로 보낼 정보들이 담겨지고, 응답 프로토콜과 관련되서 정보들이 응답 프로토콜에 의해 클라이언트로 전송 된다, 응답을 보내고 사라 진다.  
* Response의 주요 사용하는 메소드
  * setContentType() - 브라우저쪽에 출력하는 메소드에 한글이 있을 때 이걸 먼저 설정을 해준다.
  그런 다음 출력스트림을 얻는다. 
  * getWriter() - 이걸 호출하면 프린트라이터라는 출력스트림이 얻어지는데 
   이 출력스트림은 정확히 바디와 관련된 출력스트림이 만들어진다.
  * sendRedirect() - 새로운 URL로 보낼 때 사용하는 메소드이다. 브라우저에게 새로운 URL을 요청하라고 명령하는 메소드
    * 해당 페이지가 클라이언트에게 응답 객체를 보내 다시 클라이언트측에서 전송된 URI로 자동 접속하도록 만든다
    * `Foward`는 서버내에서 다른 페이지로 넘겨 로직을 수행한 후에 클라이언트에게 응답이가고,
    `Redirect`는 서버에서 클라이언트로 응답을 보내고 다시 클라이언트에서 uri를 재호출해서 다른 페이지로 넘어가도록 한다.
    
### Encoding 설정
* `request.setCharacterEncoding("EUC-KR")`처럼 각 클래스마다 직접 인코딩 지정하는 방식을 사용할 수 있는데,
인코딩 정책이 바뀌거나, 유지 보수적인 측면에서도 좋지 못하다 그래서 변수로 값을 전달바는 형태로 바꾸어야 한다.
* 방법에는 2가지 방법이 있는데 하나는 로컬 파라미터를 이용하는 것이고 다른 하나는 글로벌 파라미터를 이용하는 것이다.

#### 로컬 파라미터(Local Parameter)
* 이렇게 설정하면 insertBoardServlet에서만 로컬 파라미터에 접근할 수 있다.
* Web.XML에서만 변경을 하면되서 자바코드를 하나하나 수정할 필요가 없다.
* 서블릿 객체가 호출 되면 처음에 디폴트 생성자가 호출되고 init()이 처음이 호출이 되는데 이 init이 config객체를 호출하는데 서블릿 엔진에 의해서 생성되는데
이 config객체는 로컬파라미터만 세팅이 된다. 로컬 파라미터의 정보가 담겨져서 config 객체가 생성되서 파라미터로 담겨진다.
* 이렇게 했을 때 단점은 XML 모든 서블릿에 하나하나 설정해줘야하는 번거로움이 생긴다.
그래서 전체에 한번만 파라미터를 설정하면 되는 글로벌 파라미터 방법이 있다.
* `ServletConfig`를 사용한다.
```java
package com.rubypaper.web.board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rubypapper.biz.board.BoardDAO;
import com.rubypapper.biz.board.BoardVO;

public class InsertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String encoding;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	   // web.xml 파일에 설정된 로컬 파라미터 정보 추출
	   encoding = config.getInitParameter("boardEncoding");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding(encoding); // 인코딩 설정
	}
}
```
```xml
<servlet>
  <servlet-name>insertBoard</servlet-name>
  <servlet-class>com.rubypaper.web.board.InsertBoardServlet</servlet-class>
  <init-param>
    <param-name>boardEncoding</param-name>
    <param-value>EUC-KR</param-value>
  </init-param>
</servlet>
```
#### 글로벌 파라미터(Global Parameter)
* XML에 한번만 설정해주면 서블릿 전체에서 사용할 수 있다.
* `ServletContext`를 사용한다.
```java
package com.rubypaper.web.board;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rubypapper.biz.board.BoardDAO;
import com.rubypapper.biz.board.BoardVO;

public class InsertBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String encoding;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 글로벌 파라미터 정보 추출
		ServletContext context = getServletContext(); // 부모로부터 상속됨, httpservlet에서 상속됨
		encoding = context.getInitParameter("boardEncoding");
		
		request.setCharacterEncoding(encoding); // 마찬가지로 가져올 때 포맷 설정
	}
}
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd" id="WebApp_ID" version="2.5">
  <display-name>ServletProject</display-name>
  <welcome-file-list>
    <welcome-file>index.html</welcome-file>
    <welcome-file>index.htm</welcome-file>
    <welcome-file>index.jsp</welcome-file>
    <welcome-file>default.html</welcome-file>
    <welcome-file>default.htm</welcome-file>
    <welcome-file>default.jsp</welcome-file>
  </welcome-file-list>
  <context-param>
    <param-name>boardEncoding</param-name>
    <param-value>EUC-KR</param-value>
  </context-param>
  <servlet-mapping>
    <servlet-name>insertBoard</servlet-name>
    <url-pattern>/insertBoard.do</url-pattern>
  </servlet-mapping>
  <servlet>
    <description></description>
    <display-name>deleteBoard</display-name>
    <servlet-name>deleteBoard</servlet-name>
    <servlet-class>com.rubypaper.web.board.DeleteBoardServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>deleteBoard</servlet-name>
    <url-pattern>/deleteBoard.do</url-pattern>
  </servlet-mapping>
</web-app>
```

### Servlet Filter
* Filter는 서버가 뜰 때 바로 메모리에 로딩되서 Pre-Loading, 서블릿은 요청이 있을 때 메모리에 로딩되서 Lazy-Loding이다.
* 필터는 클라이언트의 Request가 Servlet에 도달하기 전에 요청 데이터를 원하는 형태로 조작하는 사전처리,
혹은 Servlet에서 나온 응답 데이터를 조작하여 사후처리 목적으로 사용된다.
* 필터는 기능당 하나씩 만든다 (예로, 시간을 체크하는 TimeCheckFilter, 인코딩 설정하는 EncodingFilter 등)
* 서블릿 실행 사전 처리 사후처리할 때 필요하다.
* 실제 서블릿을 실행시키는 코드는 `chain.doFilter()` 이걸 주석 처리하면 서블릿이 실행이 안된다. 사전처리 사후처리만 실행이 된다.

![ServletFilter](/Java/documents/images/ServletFilter.jpg)

* Servlet 뒤에 .do만 붙어있으면  요청이 오면 동작이 되도록 설정했다.(*.do) 
* init(), destroy(), doFilter()는 Filter 인터페이스를 상속하고 있어서 오버라이딩이 되어 있어야 한다.
#### Filter 순서
1. 브라우저에서  .do로 요청이 오면 filter가 가로채서 사전 처리가 된다. 
2. 그런 다음 chain.dofilter를 만나면 해당 서블릿이 실행된다. 서블릿의 메소드들이 실행이 된다. 
3. 메소드가 실행되고 권한이 다시 필터로 돌아오고 사후처리가 실행되고 브라우저로 돌아간다.


```java
package com.rubypapper.web.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TimeCheckFilter implements Filter {

    public TimeCheckFilter() {
        System.out.println("===> TimeCheckFilter 생성");
    }
	public void init(FilterConfig fConfig) throws ServletException {}

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// 클라이언트가 요청한 서블릿 정보 추출
		HttpServletRequest req = (HttpServletRequest) request; // 왜 형변환 하는지 확인
		String uri = req.getRequestURI(); 
		String path = uri.substring(uri.lastIndexOf("/")); // 마지막 /이후의 값이 나온다.
		
		long startTime = System.currentTimeMillis();
		// 이 시점이 클라이언트가 호출한 서블릿이 실행된다. 이코드를 기준으로 위의 시간을 구하고 끝나고 실행시간을 구하면 어떤 서블릿이 실행되는 시간을 구할 수 있다. 
		// 해당 서블릿 호출이 오랜시간이 걸리면 코드를 수정해야 한다.
		chain.doFilter(request, response);
		long endTime = System.currentTimeMillis();
		System.out.println(path + "요청 처리에 소요된 시간: " + (endTime-startTime) + "(ms)초");

	}
}
```
```xml
 <filter>
    <filter-name>timeCheck</filter-name>
    <filter-class>com.rubypapper.web.common.TimeCheckFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>timeCheck</filter-name>
    <url-pattern>*.do</url-pattern>
  </filter-mapping>
```

#### init()
* 필터를 생성할 때 `FilterConfig` 객체를 서블릿 엔진에서 생성해서 `web.xml`의 로컬 파라미터를 담아서 `init()`의 파라미터로 넘겨준다.

```java
package com.rubypapper.web.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	private String encoding;
	private String name;

	public void init(FilterConfig fConfig) throws ServletException {
		// web.xml 파일에 설정된 로컬 파라미터 정보 추출
		encoding = fConfig.getInitParameter("encoding");
		name = fConfig.getInitParameter("name");
	}
}
```

#### chain.doFilter()
* 메소드명에서도 유추해볼 수 있듯이 필터들을 체인처럼 연쇄적으로 동작 시킬 수 있다.
필터는 하나의 기능만을 하기 때문에 사전 처리나 사후 처리를 할 때 설정을 해두면 
필터 하나가 하나의 기능을 수행하고 끝나고 `chan.doFilter()`를 만나면 다음 기능을 가진 필터가 수행이 된다.
이렇게 쭉 메모리에 로딩된 순서대로 동작을 하고 필터가 없을 때 서블릿으로 실행 권한이 넘어가게 된다.

* TimeCheckFilter가 먼저 실행되고 CharacterEncodingFilter가 실행이 된다.
```java
public class TimeCheckFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 클라이언트가 요청한 서블릿 정보 추출
		HttpServletRequest req = (HttpServletRequest) request; // 왜 형변환 하는지 확인
		String uri = req.getRequestURI(); 
		String path = uri.substring(uri.lastIndexOf("/")); // 마지막 /이후의 값이 나온다.
		
		long startTime = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		long endTime = System.currentTimeMillis();
		System.out.println(path + "요청 처리에 소요된 시간: " + (endTime-startTime) + "(ms)초");

	}
}
```
```java
public class CharacterEncodingFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
}
```

#### 서블릿의 로딩시점을 변경 가능
* 필터는 Pre-Loading이라 바로 메모리에 올라가지만 서블릿은 Lazy-Loding이여서 요청이 올 때 메모리에 로딩이 되는데
이걸 web.xml에 설정을 해서 Pre-Loding처럼 바로 메모리에 올라가도록 변경이 가능하다.
* 해당 서블릿에 `<load-on-startup>숫자</load-on-startup>` 이걸 작성해주면 되는데 숫자가 작을 수록 우선 순위가 더 높다.
```xml
<servlet>
    <description></description>
    <display-name>login</display-name>
    <servlet-name>login</servlet-name>
    <servlet-class>com.rubypaper.web.user.LoginServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>login</servlet-name>
    <url-pattern>/login.do</url-pattern>
  </servlet-mapping>
  <servlet>
    <description></description>
    <display-name>getBoardList</display-name>
    <servlet-name>getBoardList</servlet-name>
    <servlet-class>com.rubypaper.web.board.GetBoardListServlet</servlet-class>
    <load-on-startup>2</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>getBoardList</servlet-name>
    <url-pattern>/getBoardList.do</url-pattern>
  </servlet-mapping>
```

### ServletListener
* 서블릿엔진이 실행된 직후 바로 실행이 되는 객체이다.
* 서블릿이 실행되기 전에 딱 한번 실행이 되는 로직이 있다면 Listener init()메소드에 설정을 해 놓으면 된다.
* 필터보다 먼저 메모리에 뜨게 된다.

