# 스프링 프로젝프 환경설정
* 이 내용들은 인프런 김영한 강사님의 스프링 입문 강의 자료를 참고하고, 강의를 들으면서 내용을 정리한 것입니다.
## 프로젝트 생성
* 스프링 부트 스타터로 스프링 프로젝트를 만든다.
* start.spring.io : 스프링 부트기반으로 스프링관련 프로젝트를 만들어주는 사이트 스프링에서 운영하는 사이트
* maven/gradle : 필요한 라이브러리들을 가져오고 빌드하는 라이프사이클 까지 관리해주는 툴.
  * 과거에는 maven을 많이 썼지만 요새는 gradle을 많이 쓴다.

* dependencies - 스프링 부트 기반으로 프로젝트를 실행할 건데 어떤 라이브러리를 땡겨서 쓸건가하는 것이다.
   * gradle이나 maven을 써도 main디렉토리랑 test디렉토리가 나눠져있다 이것이 표준화되어 있다.
   * 그만큼 요즘 개발 트렌드에서 테스트가 중요하다.
* resources  - 자바파일을 제외한 설정파일이나 xml, html등이 들어가 있는 디렉토리

* 스프링이 예전에는 설정같은걸 일일이 해줘야해서 힘들었는데, 스프링부트가 나오면서 안에 설정파일들이 제공이 된다.

## build.gradle  
* gradle에 대해 깊이 공부하면 좋은데 나중에 해도 된다, 지금은 버전설정을하고 라이브러리를 땡겨오는구나하고 생각하면 된다.
* dependencis - 처음 스프링 설정했던 부분들이 작성되어 있다. 기본적으로 요새 테스트라이브러리가 들어간다.
* repositories - 라이브러리를 다운받아야하는데 mavenCentral()이라는 공개된 사이트가 있다. 사이트에서 다운받아라 라고 써놓은 것
```properties
plugins {
id 'org.springframework.boot' version '2.3.1.RELEASE'
id 'io.spring.dependency-management' version '1.0.9.RELEASE'
id 'java'
}

group = 'hello'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

repositories {
mavenCentral()
}

dependencies {
implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
implementation 'org.springframework.boot:spring-boot-starter-web'
testImplementation('org.springframework.boot:spring-boot-starter-test') {
   exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
 }
}

test {
useJUnitPlatform()
}
```

## 라이브러리
* external libraries - 외부에서 가져온 라이브러리들을 모아 둔 것, tomcat은 내장되서 들어가 있다.
* gradle /maven 빌드 툴들은 의존관계를 다 관리해준다.
* 스프링 부트 스타터 웹을 땡기면 tomcat, spring web 이런걸 땡긴다 의존관계를 가지고 있는 것
연관관계가 있는 라이브러리들을 다 땡겨오는 것이다.

* 라이브러리들 끝에 *가 붙은것은 똑같은것이 적혀있어서 표현상 중복을 제거해준 것이다.

* 원래는 톰캣에가서 톰캣을 깔아야하고 이랬는데 요즘은 소스 라이브러리에서 내장되어 있다 임베디드
설정이 필요 없다 라이브러리하나 빌드해서 웹서버 올리면 끝나는 것이다.

* 스프링부트와 관련된 라이브러리들을 쓰면 스프링 코어까지 다 땡겨서 스프링에 관련된것이 세팅되서 돌아간다.
* 현업에서는 System.out.println()으로 출력을 하면 안된다 log를 이용해서 출력을 해야한다.
   * 로그로 남겨야 심각한 에러만 따로 모아서 볼 수 있고 로그파일이 관리된다.
* spring start - logging을 땡기면 이 두 개도 땡겨진다. 2가지 조합을 많이 쓴다 slf4j, logback.
   * slf4j - 쉽게 말해서 인터페이스이고, 실제로그를 어떤 구현체로 출력하는 것은 logback 사용한다.

### 스프링 부트 라이브러리
* spring-boot-starter-web
   * spring-boot-starter-tomcat: 톰캣 (웹서버)
   * spring-webmvc: 스프링 웹 MVC
* spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
* spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
  * spring-boot
     * spring-core
  * spring-boot-starter-logging
     * logback, slf4j
     
### test 라이브러리
* 테스트를 할 때 junit라이브러리를 많이 쓴다 요즘 5버전을 많이 씀
   * assert가 들어간 것은 테스트를 편리하게 도와주는 것 핵심은 junit 라이브러리이다.
* spring-boot-starter-test
  * junit: 테스트 프레임워크
  * mockito: 목 라이브러리
  * assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리
  * spring-test: 스프링 통합 테스트 지원
  
## view 환경설정
* 스프링 부트는 스프링 생태계를 감싸서 편리하게 도와준다. 자바 웹에 대한 생태계 전반을 다 제공한다.
어마어마하게 크다 머리속에 다 담을 수 없다. 필요한 부분을 찾는 능력이 중요하다.

* 필요한 기능 찾는 법 : spring.io에 들어간다 project -> spring boot -> learn에 가서 자기 버전에 맞는 reference Doc
-> 여기에 쭉 나와있는데 여기서 골라서 들어가면 된다. ctrl + f 로 원하는 글자를 써서 바로가서 빨리 찾는게 방법인듯

* 템플릿 엔진은 정적페이지를 내가 원하는대로 바꿀 수 있다.(thymeleaf)
* controller - 웹 애플리케이션에서 첫번째 진입지점이다.

### Welcome Page
*  resources/static/index.html
```html
<!DOCTYPE HTML>
<html>
<head>
   <title>Hello</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
Hello
<a href="/hello">hello</a>
</body>
</html>
```

### thymeleaf 템플릿 엔진
* Controller
  * @GetMapping("hello") - get방식을 의미하고, localhost:8080/hello?name=spring으로 들어왔을 때 여기로 매핑된다.
  * Model mode - 스프링이 모델을 만들어서 넣어준다, view에 값을 넣어서 전달할 때 사용한다.
    * model.addAttribute("data", "hello!!");//model(data:hello!!) 와 같다.
  * return "hello" - 여기서 이 반환 값이 hello.html을 가리키는 것이다. 모델을 화면에 넘기면서 hello.html을 실행시키라는 것이다.
  resources:templates/ +{ViewName}+ .html 처럼 작동이 된다.
```java
@Controller
public class HelloController {

    // get방식을 의미한다.
    @GetMapping("hello") // 웹 어플리케이션에서 /hello 라고 들어오면 이 메소드를 호출한다.
    public String hello(Model model){ // model view controoler의 model이다, 스프링이 모델을 만들어서 넣어준다.
        model.addAttribute("data", "hello!!"); // model(data:hello!!) 와 같다.
        return "hello"; 
    }
}
```
* resources/templates/hello.html
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<head>
   <title>Hello</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<p th:text="'안녕하세요. ' + ${data}" >안녕하세요. 손님</p>
</body>
</html>
```
