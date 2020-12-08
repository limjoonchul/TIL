# Spring Boot
* 스프링 부트는 스프링 프레임워크를 사용 가능한 상태로 만들어주는 도구이다.
부트는 키는것 전원 스위치라는 의미이니깐 곧바로 스프링을 사용할 수 있도록 제반사항들을 초기화 해주는 것
좀 더 빨리 보다 쉽게 스프링을 사용할 수 있도록도와주는 도구, 유틸리티이다.

## 스프링 부투의 등장
* 스프링 자체가 웹 어플리케이션만을 위해 만들어진게 아니고 범용적인 프레임워크로서 만들어졌다.
* 많은 사람들이 웹 개발을 할 때 사용을 하게 되었다. 그런데 웹 개발에 최적화 되어 나온 루비 온 레일즈, 노드 기반의 프레임워크와
경쟁에 있어서 스프링은 많은 것들을 지원했어야 하기 때문에 웹 기반에서 부족했다.

### 스프링을 가지고 웹을 개발했을 때 문제점
1. 라이브러리들을 설정하는게 너무 어렵다.
2. 리졸버, 핸들러 매핑과 같은 객체들을 빈 등록을 해야하는 xml설정이  복잡하다.
실수로 id와 name같은 것들을 잘 못 설정할 수도 있으니깐 그런 것들이 복잡했다.

* 이런 문제들을 한번에 해결하고 소프트웨어 시장에서 빠른 어플리케이션 개발을 지원하는
경량의 프레임워크를 선호하는 시장에서 스프링부트가 개발되었다.

### 스프링 부트의 장점
1. 라이브러리 관리 자동화
2. 버전 관리 자동화
3. 자동 설정
4. 내장 서버를 통해 톰캣 설정 최소화
5. 독립적으로 실행 가능한 jar(jar로 패키징해도 실행 가능하다.)
 
## 이클립스에서 프로젝트 생성
* File -> new -> other -> SpringBoot Spring Starter Project 
-> 프로젝트명 작성과 설정을 하고 -> available이 어떤 라이브러리들을 추가할 것인지
하는 것이다. 필요한 라이브러리들을 찾아서 추가하고 finish
* 프로젝트를 생성하면 pom.xml에 spring-boot-maven-plugin이 에러가 났다 이걸 해결하기 위해선 
위쪽에서 spring-boot-starter-parent를 ctrl + 이 라인을 누르면 링크를타고 부모 xml로 넘어간다.
여기서 한번도 똑같이 spring-boot-dependencies를 타고 들어가면 많은 디펜던시들이 작성되어 있다.
* 여기서 <maven-jar-plugin.version>3.2.0</maven-jar-plugin.version>를 복사해서 java-version 밑에 추가하면 에러가 사라진다.

### Starter
* 라이브러리를 추가할 때, Spring Web을 추가했는데 이것이 Spring-Starter-web이
pom.xml에 dependency에 생성이된다.
* 스타터들에는 많은 연관된 라이브러리들이 포함되어 있다.
* pom에는 하나의 starter가 dependency에 추가되었지만, 그 안에는 연관된 많은 다른 라이브러리들이 연쇄적으로 추가가 된다.
* 스타터는 jar파일 하나가 아니라 관련된 라이브러리들의 패키지라고 이해하면 된다.
* 프로젝트에 필요한 라이브러리들을 패키지 단위로 최소한의 디펜던시 설정만으로 관리할 수 있다.
### 컨테이너 설정
* 스프링 컨테이너에는 GenericWebApplicationContext, XmlWebApplicationContext 두 가지가 있다.
* 제네릭은 웹이 아닌 일반 자바 어플리케이션 환경이고,xml은 웹 환경에서 생성하고 실행하는 어플리케이션 환경이다.

#### GenericWebApplicationContext
* 스프링부트에서 제네릭 컨테이너로 실행시키기 위해서 메인클래스에서 `WebApplicationType.None`을 해주면 된다.
```groovy
SpringApplication application = new SpringApplication(SpringBootProject01Application.class);
application.setWebApplicationType(WebApplicationType.None);
application.run(args);
```
* 제네릭으로 실행시키면 웹이 아니기 때문에 많이 실행시켜도 포트 충돌이 발생하지 않는다.

#### XmlWebApplicationContext
* 웹 환경에서 실행시키고 싶으면 두 가지 방법이 있다.
1. 처음 프로젝트를 만들면 메인 클래스에서 아래처럼 자동으로 설정이 되어있는데 이 코드가 디폴트로 xml컨테이너로 설정이 되어 있는 것이다.
```groovy
SpringApplication.run(SpringBootProject01Application.class, args);
``` 
2. 아래처럼 설정할 수 있는데 `WebApplicationType.Servlet`으로 바꾸면 Xml컨테이너로 사용가능 하다.
```groovy
SpringApplication application = new SpringApplication(SpringBootProject01Application.class);
application.setWebApplicationType(WebApplicationType.Servlet);
application.run(args);
```

### 외부 프로퍼티 설정
* 직접 메인 클래스에서 위의 방법들처럼 컨테이너를 설정할 수 있지만, 외부 프로퍼티에서 지정할 수 있다. 
* 외부 프로퍼티를 이용해서 컨테이너에 대한 환경 설정들을 작성해주면, 
스프링부트에서 자동으로 인식해서 프로퍼티에 설정한 환경으로 컨테이너를 생성해준다.
* 외부 프로퍼티에서 지정하는 것이 더 우선순위가 높아서 여기서 작성하는 방식대로 컨테이너가 생성이 된다.
* spring.main.web-application-type을 써주고 이것이 none이면 Generic, Servlet이면 Xml이다.
```properties
// webApplication Type Setting
spring.main.web-application-type=servlet
```
* server port setting 
  * `server.port=8080`

* Banner Setting
  * `spring.main.banner-mode=console;`
  * 프로젝트 실행시에 Spring의 모양이 나타나는게 있는데 이것이 배너이다.
  * 이 모양을 바꿀 수 있다. banner.txt라고 파일을 만들면 스프링부트에서 애를 인식해서 안에 내용으로 실행될 때 로그를 출력시켜준다.
  * http://www.patorjk.com/software/taag 이 사이트에 들어가면 원하는 모양으로 바꿀 수 있다.
  ```text
                                   /$$                                                        
                                  | $$                                                        
     /$$$$$$/$$$$  /$$   /$$      | $$$$$$$   /$$$$$$  /$$$$$$$  /$$$$$$$   /$$$$$$   /$$$$$$ 
    | $$_  $$_  $$| $$  | $$      | $$__  $$ |____  $$| $$__  $$| $$__  $$ /$$__  $$ /$$__  $$
    | $$ \ $$ \ $$| $$  | $$      | $$  \ $$  /$$$$$$$| $$  \ $$| $$  \ $$| $$$$$$$$| $$  \__/
    | $$ | $$ | $$| $$  | $$      | $$  | $$ /$$__  $$| $$  | $$| $$  | $$| $$_____/| $$      
    | $$ | $$ | $$|  $$$$$$$      | $$$$$$$/|  $$$$$$$| $$  | $$| $$  | $$|  $$$$$$$| $$      
    |__/ |__/ |__/ \____  $$      |_______/  \_______/|__/  |__/|__/  |__/ \_______/|__/      
                   /$$  | $$                                                                  
                  |  $$$$$$/                                                                  
                   \______/                                                                   
                   
    ${spring-boot.formatted-version}
  ```
*  Logging setting
  * `logging.level.org.springframework=info`
  
### 스프링 부트 방식의 개발
* 스프링은 xml파일을 로딩해서 컨테이너가 생성되었는데, 스프링부트는 Configuration이라는 클래스를 만들어서
xml 대신해서 로딩할 때 컨테이너가 참조해서 띄울 수 있게 설정할 수 있다. 이 클래스가 들어가 있는 패키지 명을 참조하도록 할 수 있다. 
* xml 설정을 없애는 대신 다시 자바 코드로 돌아온 것이다.

*  AnnotationConfigApplicationContext를 GenericWebApplicationContext처럼 로그만 남기게 자바 어플리케이션 환경으로 사용할 수 있다.
 `AnnotationConfigApplicationContext container = new AnnotationConfigApplicationContext(TvConfiguration.class);`
 뒤에 ()안에 실행되는 클래스 명이나, 클래스가 속한 패키지명을 넣어주면 된다 xml처럼 로딩해서 안에 빈 등록된 객체가 메모리에 뜬다.
 * `SamsungTV tv = (SamsungTV) container.getBean("tv");`에서 ()안에 TvConfiguration에서
 빈 등록된 객체의 @Bean("stv")같은 id명이나, 해당 메소드의 메소드명을 넣어주면 된다.
```groovy
AnnotationConfigApplicationContext container =
				new AnnotationConfigApplicationContext("com.rubypaper.tv");
SamsungTV tv = (SamsungTV)container.getBean("stv");
```

#### Setter Injection
* 컨피규레이션 클레스에 객체를 @Bean을 사용해서 빈 등록한다. 
```groovy
@ComponentScan(basePackages = {"com.google"})
@Configuration
public class TvConfiguration {
	@Bean("stv")
	public SamsungTV tv() {
		SamsungTV tv = new SamsungTV();
		tv.setSpeaker(speaker()); 
		return tv; // new SamsungTV();로 썼다가 리턴이 안됨... 멍청한 녀석 왜 안되냐고 찾고 있었네
	}
	// 세터인젝션
	public AppleSpeaker speaker() {
		return new AppleSpeaker();
	}
}
```
```groovy
public class SamsungTV {
	
	private AppleSpeaker speaker;
	
	public SamsungTV() {
		System.out.println("===> SamsungTV");
	}
	public void setSpeaker(AppleSpeaker speaker) {
		this.speaker = speaker;
	}
	public void powerOn() {
		System.out.println("SamsungTV ---> powerOn");
	}
	public void powerOff() {
		System.out.println("SamsungTV ---> powerOff");
	}
	public void volumeup() {
		speaker.volumeup();
	}
	public void volumdown(){
		speaker.volumdown();
	}
}
```
* TvConfiguration Class가 XML처럼 환경 설정으로 지정되는데 이 클래스가 들어있는 패키지를 XML처럼 지정해도 로딩이 된다.
* 패키지 밑의 모든 환경설정 클래스를 로딩할 수 있다.

* @Configuration을 TvConfiguration한테 줘야한다. @Configuration도 컴포넌트를 가지고 있다.
이 객체도 컴포넌트 스캔이 되서 메모리에 뜬다. 그래서 메소드에 구현된 클래스들도 메모리에 뜨게 된다.

#### Type Injection
```groovy
@ComponentScan(basePackages = {"com.google"})
@Configuration
public class TvConfiguration {
	@Bean("stv")
	public SamsungTV tv() {
		SamsungTV tv = new SamsungTV();
		return tv;
	}
	@Bean  // 타입인젝션을 할 땐 빈등록을 해줘야 한다.
	public AppleSpeaker speaker() {
		return new AppleSpeaker();
	}
}
```
```groovy
public class SamsungTV {
	@Autowired
	private AppleSpeaker speaker;
	
	public SamsungTV() {
		System.out.println("===> SamsungTV");
	}
	public void powerOn() {
		System.out.println("SamsungTV ---> powerOn");
	}
	public void powerOff() {
		System.out.println("SamsungTV ---> powerOff");
	}
	public void volumeup() {
		speaker.volumeup();
	}
	public void volumdown(){
		speaker.volumdown();
	}
}
```
#### RequestMapping
* get방식으로 들어온 URL을 매핑해서 해당하는 메소드를 실행시켜준다.
 @ResponseBody가 있으면 실행한 반환값을 response 프로토콜에 담아서 클라이언트에 보내줘서
 화면이 출력된다. 
* @RestController를 붙여주면 @ResponseBody가 필요 없다.
```groovy
@Controller
public class BoardController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name){
        return "hello" + name;
    }

//    @RequestMapping("/getBoard")
    @GetMapping("/getBoard")
    @ResponseBody
    public BoardVO getBoard(){
        BoardVO board = new BoardVO();
        board.setSeq(1);
        board.setTitle("타이틀");
        board.setWriter("작성자");
        board.setContent("내용");
        board.setRegDate(new Date());
        board.setCnt(0);
        return board;
    }

//    @RequestMapping("/getBoardList")
    @GetMapping("/getBoardList")
    @ResponseBody
    public List<BoardVO> getBoardList(){
        List<BoardVO> boardList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            BoardVO board = new BoardVO();
            board.setSeq(++i);
            board.setTitle("타이틀" + i);
            board.setWriter("작성자" + i);
            board.setContent("내용" + i);
            board.setRegDate(new Date());
            board.setCnt(0);
            boardList.add(board);
        }
        return boardList;
    }
}
```
#### 주의
* java.lang.IllegalStateException: Ambiguous mapping found. Cannot map ‘projectController’ bean method 과 같은
에러 메세지가 뜨면 GetMapping의 uri들이 중복되는 것이 없는지 잘 적혀있는지 확인해야 한다.
* 즉, xml대신에 클래스를 만들어서 컨테이너 실행될 때 로딩할 수 있고,
 이 클래스가 들어있는 패키지로도 로딩이 가능하다
컨피규레이션 클래스에 빈을 등록해 놓으면 그것들이 메모리에 올라간다.

#### 다른 패키지의 클래스 참조
* 컨피규레이션 클래스에 @ComponentScan(basePackages = {"com.google"})주면
자동으로 컨포넌트를 스캔해서 그 패키지 밑에있는 클래스들 중에 컨포넌트가 있는
애노테이션이 붙은 객체들도 메모리에 뜬다.

### 스프링 부트 웹 어플리케이션
#### Controller Class
```groovy
//@Controller
@RestController
public class BoardController {
	public BoardController() {
		System.out.println("===> BoardController created");
	}
	
//	@RequestMapping(value="/hello", method = RequestMethod.GET)
//	@ResponseBody // 응답프로토콜바디에 넣는다.
	@GetMapping("/hello") // 리큐ㅞ스트  매핑을 겟방식으로 한다고 바꿀 수 있음
	public String hello(String name) {
		return "hello : " + name;
	}
	
	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		
		board.setSeq(1);
		board.setTitle("임시 제목");
		board.setWriter("익명");
		board.setContent("임시 내용...");
		board.setCreateDdate(new Date());
		board.setCnt(0);
		
		return board;
	}
	
	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		for (int i = 0; i < 5; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(++i);
			board.setTitle("임시 제목" + i);
			board.setWriter("익명" + i);
			board.setContent("임시 내용..." + i);
			board.setCreateDdate(new Date());
			board.setCnt(0);
			boardList.add(board);
		}
		
		return boardList;
	}
}
```
* @RestController를 이용하면 이 클래스에 있는 모든 메소드에
@ResponseBody를 따로 설정 안해줘도 된다.

#### Main Class
```java
@SpringBootApplication
public class SpringBootProject01Application {
	
	public static void main(String[] args) {
//		SpringApplication.run(SpringBootProject01Application.class, args);
		SpringApplication application = new SpringApplication(SpringBootProject01Application.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.setBannerMode(Banner.Mode.OFF); //  
		application.run(args); // 어플 실행
	}
}
```
* @SpringBootApplication이 중요하다. 안에 많은 어노테이션들이 포함되어 있다.
   * @SpringBootConfiguration, @EnableAutoConfiguration, @ComponentScan 이 중요 하다.
#### @SpringBootApplication
* @SpringBootConfiguration - @Configuration이랑 똑같다 
   하지만 차이를 두기 위해 스프링부트 환경설정이라고 정해놨다 하지만 결국 똑같은 것
* @ComponentScan - 이 클래스가 포함 되어있는 패키지를 베이스 패키지로 포함해서
  이 패키지안에 있는 컴포넌트를 메모리에 띄우는 것이다.
  * `<context:componet-scan base-package="com.rubpaper">` 랑 똑같다
  * 이 클래스가 포함 되어있는 패키지(com.rubypaper)를 베이스 패키지로하여 포함되어있는 클래스에(하위 패키지의 것들도)
  컴포넌트가 있는 것을 메모리에 띄우는 것이다. 그래서 BoardController가 메모리에 뜨는것이다.
  * @ComponentScan(basepackages={"", "com.ruby"})를 해주면
  com.rubypaper와 com.ruby 패키지 둘다 컴포넌트 스캔 범위에 들어가서 둘 패키지 밑에 있는
  컴포넌트가 붙은 클래스들이 모두 메모리에 띄워진다.
  
* @EnableAutoConfiguration이 매우매우 중요한 개념이다.
  * 스프링 프로젝트에서 MultipartFile 객체를 컨테이너에 올리기 위해서 CommonsMultipartResolver가 필요했다.
  이  CommonsMultipartResolver를 직접 빈에 등록했는데 스프링부트에서는 @EnableAutoConfiguration을 사용하면 자동으로 빈 등록해준다.
  * 파일 업로드 클래스를 실행하기 위해서 내가 만든 클래스와 스프링에서 제공하는 클래스 두가지고 필요했는데,
  내가 직접 만든 클래스는 어노테이션을 붙여서 @ComponentScan으로 메모리에 띄울 수 있다.
  하지만 스프링에서 제공하는 클래스들은 빈으로 등록해야 메모리에 띄울 수 있었다. 그런데 문제점은 빈 등록할 때 name, id를 잘 못 작성하여
  에러가 발생하는 실수를 하게 되었다. 그래서 EnableAutoConfiguration이 스프링 부트에서 지원되었다.
  * 동작 과정은 AutoConfigure 패키지 안에 META-INF 패키지안에 spring.factories라는 파일에 자동 설정 클래스들이 정의되어 있다.
  이 클래스들을 메모리에 띄우는 것이다. 그런데 자동설정 클래스로 등록이 되어있다고 모두 메모리에 뜨는게 아니고
  각 클래스들을 들어가보면 @Conditional이라는 애노테이션이 붙어져있고, 그 뒤에 조건들이 붙여져 있는데 그 조건을 만족해야만
  메모리에 뜨게 되는 것이다.
  * 자동 설정 클래스를 직접 만들어서 META-INF 패키지 안에 파일을 만들어서 spring.factoreis파일을 만들어서 그안에 클래스를 등록하면
  자동설정 클래스로서 메모리에 띄울 수 있다.
     
   
