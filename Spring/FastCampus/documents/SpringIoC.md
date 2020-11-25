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

* 컨테이너는 내부적으로 `Map`이 들어있는 것이다. 빈 등록을 할 때 XML에 id로 Tv라고 지정하여 구글 티비클래스를 작성하면
컨테이너에 Tv라는 키값으로 구글티비가 value로 등록되는 것이다. 컨테이너가 맵을 내부적으로 운영하면서 XML에 등록된 객체들을 생성해서 관리한다. 
 
* 컨테이너란 당신이 작성한 코드의 처리과정을 위임받은 독립적인 존재라고 생각하면 된다. 
컨테이너는 적절한 설정만 되어 있다면 누구의 도움 없이도 프로그래머가 작성한 코드를 스스로 참조한 뒤 알아서 객체의 생성과 소멸을 컨트롤 해준다.
  * 출처: https://limmmee.tistory.com/13 [심플하게 개발] 

![컨테이너](/Java/documents/images/Spring컨테이너동작.jpg)

* `applicationContext.xml`에 자바 빈을 등록해준다.
* `applicationContext.xml`을 보고 해당 객체를 생성하고, 객체 호출이 오면 해당 객체를 반환해준다.

### Spring XML 파일(applicationContext.xml)
* 스프링 컨테이너에서 `applicationContext.xml`파일을 로딩하기 때문에 중요하다.
이 xml파일에 작성하는 것에 따라서 스프링 컨테이너에서 생성하는 객체들이 달라지고 하니깐 스프링 컨테이너를 제어할 수 있다.
* 아키텍쳐는 jar라는 라이브러리에 다 들어있다. jar파일 안에 엄청 많은 클래스들이 들어있고 이걸 프레임워크가 제공해준다.
* 컨테이너는 아키텍쳐를 제공해준다, 뼈대한테 어떤 살(비즈니스 로직)을 알려주게 하는게 xml 파일,
 xml을 이용하기 때문에 이걸 어떻게 작성하느냐에 따라 컨테이너 동작이 달라진다.

#### 스프링 XML 파일 설정
* XML에 파일을 보면 Beans에 `xmlns = "..." `라고 작성되어 있는 부분이 있다.
xml뒤에 ns는 namespace의 약자이고, 이 namespace에 등록된 태그만 사용해야 한다.

* `xmlns="http://www.springframework.org/schema/beans" `
  * 이 namespace의 등록된 태그는 description, import, allias, bean 이 4개만 사용 할 수 있다.
  * 그 중 `bean(<bean id="Tv" class="pholymorphism4.LGTv"></bean>)` 이걸 주로 사용하고 나머진 거의 잘 안쓴다.
* `xmlns:context="http://www.springframework.org/schema/context" ` 이렇게 추가하면 더 많은 태그들을 사용할 수 있다.
* bean 태그의 속성
   * id 속성과 비슷한 속성은 name 속성
      * id는 제약 조건이 있다 숫자로 시작할 때 에러남. 공백을 포함할 수 없다 특수 기호를 못 쓴다. 자바의 변수 이름 규칙과 똑같다.
      * name은 제약조건이 없다. 자바 변수명을 따르지 않음. 
      특수한 아이디를 등록할 때 name을 쓰지만 일반적으로는 id를 사용한다. name은 권장하지 않음
   * 기본적인 스프링 컨테이너의 빈 등록된 객체들은 pre-loading인데 lazy-loading으로 설정할 수 있다.
      * lazy-init = "true"를 넣어주면 해당 빈은 클라이언트의 요청이 올 때 메모리에 뜬다.
      * false를 하면 프리로딩을 의미한다.이게 디폴트이다.
   * scope 속성
      * 여러 곳에서 이 <bean> 태그의 클래스를 사용할 때, new로 여러 객체들을 생성하면 여러 개가 메모리에 뜨게 되고 주소가 달라지는데
      똑같은 객체는 어차피 하나의 기능만 하니깐 여러 개 생성할 필요가 없다.
      그래서 이 scope속성을 제공하여서 객체 생성을 관리한다.   
      * 기본 값이 `singtone`이여서 따로 지정을 안해줘도 같은 클래스의 객체들이 여러개 생성되도 하나의 객체만 생성되어 주소만 알려주는 형태가 된다.
      * `scope="prototype" ` 로 지정하면 여러 개의 객체가 생성 되도록 바꿀 수 있다.

* 서블릿 컨테이너로 클라이언트에서 요청이 오면 서블릿이 하나만 생성되서 스레드풀의 스레드들이 이 서블릿을 공유하게 되어있다.
   * 서블릿 객체가 하나 하나 생성되는 것보다 스레드가 메모리를 적게 잡아 먹으니 적은 메모리로 여러번 생성되게 하기 위해서
* 스프링 컨테이너는 빈에 클래스가 들어 있으면 이 클래스를 객체로 만들어서 컨테이너에 생성 되는게 아니라, 
내부적으로 변환이 이뤄나서 jsp가 서블릿으로 변환되는 것처럼 참조하는 객체가 생성이 되는데 이걸 싱글톤으로 내부적으로 구현이 되어 있다.


## IoC(Inversion of Control) 제어 역전
* 스프링 프레임워크가 갖는 핵심 특징 중 하나이다.
* 순제어가 있는데 이 순제어라는 것은 개발자들이 자바 코드를 작성할 때 객체를 직접 new 해서 생성을 해 줬는데  이런걸 순제어라고 한다.
 객체를 생성하고 관리하는 제어권이 개발자에게 있었다. 이것의 문제점은 객체를 바꾸고 싶을 때 코드를 하나 하나 바꿔줘야 했다.
* 이거와 반대로 역제어는 xml파일에 클래스를 작성해 놓으면 컨테이너에서 xml을 로딩하니깐 이 객체를 생성해주고, 관리한다.
개발자들이 직접 객체를 생성하고 관리하지 않아도 된다. 즉 주체자가 개발자에서 프레임워크로 넘어간 것이다.
* A라는 객체를 사용하는 다른 객체들이 많은 상황에서 A 객체를 다른 B 객체로 바꿔줘야 할 때
 xml에 파일의 클래스만 바꿔주면 되기 때문에 일일히 참조하고 있는 다른 객체들의 코드를 바꿔주지 않고도 수정이 가능하다.
이런 면에서 유지보수성이 높아지는 것이다. 
* 결국 유지보수를 계속 해나가야 하는 거니깐 이런 면을 고려해서 개발을 해야 하니 이런 점이 굉장히 중요한 일이구나 인식하면서 개발을 해 나가야 할 것 같다.
* IoC를 이용하면 두 가지 제어를 컨테이너가 가져갔다.
  1. 객체 생성 (어플리케이션에 필요한 객체들을 new로 생성해서 사용했었는데 컨테이너에서 해준다,)
  2. 객체 와 객체간의 의존관계 (예젠엔 이것도 자바소스로 직접 의존관계를 설정했었는데 컨테이너에서 알아서 설정에 맞춰서 의존성 주입을 해준다.)
  
   
### 클래스를 객체로 사용하기 위한 방법
#### xml에 직접 빈 등록
* Spring에서 클래스를 객체로 사용하기 위해서 xml에 빈 등록을 해줘야 한다.
그래야 컨테이너에서 이 빈을 가지고 와서 객체를 주입시켜준다.
* XML에 등록된 빈 객체는 순서대로 메모리에 뜬다. 빈 등록된 객체들이 생성될 때는 `디폴트 생성자`가 필요하다(중요!!)
* 이 등록한 객체들을 생성할 때 콘솔을 보면 메모리에 id가 표시되서 출력이 되는데, 따로 id를 지정해주지 않는다면 `패키지.클래스명.#0` 형태로 출력이 된다.
  * 그래서 사용하기 쉽게 id를 편리한 이름을 사용해서 지정해서 사용하면 된다.
  * `<bean id="tv" class="polymorphism4.SamsungTv"></bean>`

#### 애노테이션을 사용해서 빈 등록
* 스프링의 IoC컨셉은 개발자들은 유지보수할 때 자바소스를 건드리지말고 xml설정을 건드리면서 개발해라.
* 근데 오히려 xml파일이 길어지면?? 또 관리하기 어려워진다. 그래서 애노테이션으로 관리한다.

```java
package pholymorphism4;

import org.springframework.stereotype.Component;

@Component("apple")
public class AppleSpeaker implements Speaker{
	public AppleSpeaker() {
		System.out.println("===> AppleSpeaker 생성");
	}

	public void volumeUp() {
		System.out.println("AppleSpeaker---소리 올린다.");
	}

	public void volumeDown() {
		System.out.println("AppleSpeaker---소리 내린다.");
	}
}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component("Tv")
public class LGTv implements Tv {

	@Autowired
	private AppleSpeaker speaker; // 이타입의 객체가 반드시 메모리에 있어야 한다 없으면 노서치익셉션이 발생함.

	public LGTv() {
		System.out.println("===> LGTv 생성");
	}

	public LGTv(AppleSpeaker speaker) {
		this.speaker = speaker;
	}

	@Override
	public void powerOn() {
		System.out.println("LGTv---전원 켠다.");
	}

	@Override
	public void powerOff() {
		System.out.println("LGTv---전원 끈다.");
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		speaker.volumeUp();
	}
}
```
* @Autowired : type injection
   * 변수의 타입을 기준으로 의존성 주입을 처리한다.
   *  해당 타입의 객체가 메모리에 있기만 하면 컨테이너가 그 객체를 변수에 할당한다.
   *  만약 주입할 객체가 없으면 Exception 발생
```xml
<context:component-scan base-package="pholymorphism4"></context:component-scan>
```
* 이 패키지로 시작하는 모든 클래스들 중에서 @Componet가 붙어있는 클래스를 스캔해라라는 의미이다.

### DI(Dependency Injection) 의존성 주입
* IoC의 핵심으로 Constructor Injection, Setter Injection 이 두 가지가 있다.
* 객체 지향적으로 설계를 하게 되면 클래스들을 각 역할을 하도록 나누게 되니깐 클래스들이 굉장히 많아진다.
예를 들어서 Tv가 있으면 이 Tv는 화면이 나오는 기능을 하고, 소리가 나오는 Speaker 클래스를 따로 두어서 기능을 나누게 된다.
이때 Tv에서 소리가 나오게 되어 있으니 Tv가 Speaker를 포함하고 있어서 Tv객체가 생성이 되고 이 와 연관된 Speaker객체를 생성해주면 되는데
Tv객체의 스피커가 고장나거나 해서 바꿀 수 있으니 유지보수성 좋게 Tv클래스를 변경하지 않고, 컨테이너에서 객체를 넣어주도록 하는 방법이다.
 
* 하나의 스피커를 여러개의 tv(lg, samsung, google)가 사용할 수 있고, 
이 Tv를 여러명의 클라이언트가 사용할 수 있어서 유연하게 객체들을 수정해주고 주입시켜주기 위해서 
유지보수성을 높일 수 있게 이런 방법을 이용한다.
  * 이 방법을 사용하지 않았을 때는 소니스피커를 사용하다가 애플스피커로 교체할려고 할 때 tv클래스들을 일일히 바꿔줘야 했다.
  
#### Constructor Injection 생성자 주입
* 스프링에서 기본적으로 Tv 객체를 생성하게 되면 디폴트 생성자만 호출하게 되어있어서 매개변수 생성자가 호출되지 못한다.
그래서 이걸 xml이나, 애노테이션 설정을 통해서 Tv가 생성될 때 Speaker의 객체도 주입해줘서 생성되게 하는 방법이다.
```xml
<bean id ="speaker" class="pholymorphism4.AppleSpeaker"></bean>
<bean id="Tv" class="pholymorphism4.SamsungTv">
    <constructor-arg ref="speaker"></constructor-arg>
    <constructor-arg value="150000"></constructor-arg> // 이걸 호출하는 생성자들을 생략하면 실행할 때 오류남.
</bean>     
```
* `<constructor-arg>` 를 사용하여서 삼성티비가 생성될 때 참조하는 객체를 넘겨준다는 의미이다. 그래서 애플스피커의 생성자가 호출되어 생성되게 된다. 
* ref - 레퍼런스 ""안에 있는 id를 참조한다, 여기선 스피커 객체를 참조한다.
* value - 고정된 값일 때 사용, 값을 생성해서 넣어준다. ref는 어떤걸 참조할 때 사용한다. id는 숫자로 시작할 수 없으니 value로 써야한다.


#### Setter Injection
```xml
<bean id ="speaker" class="pholymorphism4.AppleSpeaker"></bean>
<bean id="Tv" class="pholymorphism4.SamsungTv">
    <property name="speaker" ref="speaker"></property>
    <property name="price" value="15000"></property>
</bean>
```
* property를 사용한다. 삼성티비를 생성할 때 setSpeaker()를 호출하여서 애플스피커를 넘겨주라는 의미이다.
* name은 speaker는 setSpeaker메소드를 의미한다. 자동으로 set을 가리키게 되어있다. 
setSpeaker라고 표현하면 setsetSpeaker를 찾게 되서 오류남. ref는 위의 빈을 의미한다.
## 참고
* 스프링 개발을 편리하게 하기 위한 도구로 STS(Spring Tool Suite)를 사용하면 좋다.
  * 복잡한 클래스 이름등을 자동완성 시켜주고 xml에 필요한 클래스들을 import 하는데 편리성을 제공한다.
   

 
