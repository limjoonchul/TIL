# 스프링 웹 개발 기초
* 이 내용들은 인프런 김영한 강사님의 스프링 입문 강의 자료를 참고하고, 강의를 들으면서 내용을 정리한 것입니다.
## 정적 콘텐츠
* 정적 콘텐츠 - 서버에서 웹 브라우저에 파일을 그대로 전달해 주는 것이다.
* static 폴더 - 정적 파일을 넣으면 그대로 출력이 된다. 어떤 프로그래밍을 할 수 없다.
* template 폴더 - 동적인 프로그래밍을 하기 위한 파일들을 넣어주면 된다.

### 정적 콘텐츠 동작 순서
1. 웹브라우저에서 /hello-static.html 이라고 쳐서 요청을 한다 
2. 내장 톰캣 서버에서 요청을 받고, /hello-static.html이라고 들어왔다고 스프링부트에 알린다.
3. 스프링 부트는 먼저 controller 쪽에서 hello-static이 있는지 찾아본다 (controller에서 우선권을 먼저 갖는다)
4. 매핑되는 컨트롤러가 없으면 resources: static/hello-static.html 을 찾아서 이것을 반환해준다.

<img width="400" alt="정적콘텐츠" src="https://user-images.githubusercontent.com/54927837/96865276-b9d70280-14a4-11eb-9536-f3d045f27048.png">

```html
<!--resources/static/hello-static.html-->
<!DOCTYPE HTML>
<html>
<head>
 <title>static content</title>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
정적 컨텐츠 입니다.
</body>
</html>
```

## MVC와 템플릿 엔진
* mvc와 템플릿 엔진 - jsp, php 등이 템플릿 엔진이다 html을 서버에서 그냥 
주는게 아니라 프로그래밍에서 html을 동적으로 만들어서 전달해주는 것

* MVC : Model, View, Controller
* 과거에는 뷰랑 컨트롤러가 따로 분리되어 있지 않았다 뷰에서 모든걸 다 했다 이런 것이 modelone 방식이라고 했다.
* 뷰는 화면을 그리는데 집중을 해야 한다. 컨트롤러나 모델는 비즈니스로직과 관련있거나 내부적인걸 처리하는데 집중해야 한다.
그래서 이렇게 나누게 되었다.

* 요즘에는 컨트롤러랑 뷰를 나누는게 기본이다 뷰는 화면만 관련된 것 만 뒤에서 비즈니스 로직과 관련된 것들을 
컨트롤러에서 처리를하고 모델에다가 관련된 화면에 필요한 것들을 담아서 화면에 보내주는 패턴이다.

* template 폴더에서 템플릿 html 파일을 만들 때 xmlns:th="http://www.thymeleaf.org" 이부분을 적어줘야한다
템플릿 엔진을 사용한다고 선언하는 것과 같음 동적으로 무언가를 바꾼다는 의미이다.
* 메소드의 파라미터 정보를 확인하는 단축키 ctrl + p 이걸 사용하면 파라미터가 어떤 형태로 들어갈 수 있는지
떠서 확인할 수 있다.

### MVC와 템플릿 엔진
1. 웹 브라우저에서 /hello-mvc로 요청을 한다.
2. 내장 톰캣 서버에서 요청을 받아 spring boot에 /hello-mvc들어왔다고 전달한다.
3. 컨트롤에서 hello-mvc가 있는지 먼저 확인하고 있으면 hello-template.html로 model(name:spring)을 반환해준다.
4. viewResolver가 hello-template.html 파일을 찾아서 html 파일을 반환된 model의 값과 매핑되는
`${name}`을 spring으로 치환해서 변환 후에 브라우저에 전달해서 출력을 하게 된다.

<img width="400" alt="mvc템플릿엔진" src="https://user-images.githubusercontent.com/54927837/96865903-80eb5d80-14a5-11eb-948a-8c349d01b075.png">

```java
@Controller
public class HelloController {
  @GetMapping("hello-mvc")         // value = "name" 을 넣으면 브라우저에서 get방식으로 ?name=spring! 하고 값을 넣어서 보내면 spring이 출력이 된다.
  public String helloMvc(@RequestParam(value = "name") String name, Model model){ // 외부에서 파라미터를 받을 때 @RequestParam을 사용한다.
      model.addAttribute("name",name); // 모델에 담으면 뷰에서 랜더링 할 때 사용한다.
      // 컨트롤러에서 String name이 여기서는 spring으로 바뀌고
      // model.addAttribute("name",name); 여기서 nameh spring으로 바뀐다
      // 그리고 모델에 담겨서 템플릿으로 넘어가서 ${name}의 값이 spring으로 치환되서 출력하게 된다.
      return "hello-template";
  }
}
```

```html
resources/template/hello-template.html
<html xmlns:th="http://www.thymeleaf.org">
<body>
<p th:text="'hello ' + ${name}">hello! empty</p>
</body></html>
```

## API 방식
* API - JSON이라는 데이터 구조 포맷으로 클라이언트에게 데이터를 전달하는 것이 API방식이다.
뷰, 리액트 이런 것들을 쓸 때도 API로 데이터만 내려주면 화면은 클라이언트가 정리하는 방식을 할 때도 API 방식을 사용,
서버끼리도 통신할 때 HTML을 내릴 필요가 없고 어떤 데이터가 왔다 갔다 하는 지가 중요한데 데이터가 들어올 때 API 방식이다.

* 정적 콘텐츠를 제외하고 두가지만 기억하면 된다. HTML로 내리냐 아니면 API방식으로 데이터를 바로 내리냐 하는 것이다.
템플릿 엔진과의 차이는 뷰 이런게 없고(html 형식이 없고) 문자 그대로 전달하게 되어있다.

### API방식 동작 순서 @ResponseBody 사용 원리
1. 웹 브라우저에서 /hello-api로 요청을 한다.
2. 내장 톰켓 서버에서 /hello-api 요청을 받고, 스프링부트에 알려준다.
3. 컨트롤러 hello-api 쪽으로 가고,  @ResponseBody 라는 애노테이션이 붙어 있으면 HttpMessageConverter 라는 것이 동작을 한다.
단순 문자이면 StirngConverter가 동작을 하고, 객체이면 JsonConverter가 동작을 한다.
{name: spring} 이런 형식으로 요청이 들어온 브라우저, 서버, 안드로이드 클라이언트에 전송한다.

* @ResponseBody가 있으면  http응답에 이 데이터를 그대로 넘겨야겠구나 하고 동작한다 그런데 문자가 아니고 객체이면
json 방식으로 데이터를 만들어서 반환 하겠다 라고 기본적으로 세팅이 되어 있다.

* 여기서 객체를 Json형태로 바꿔주는 라이브러리들이 있다 대표적으로 jackson, 구글에서 만든 Gson이 있다.
스프링은 jackson을 기본으로 세팅을 해놨다. 바꿔서 사용할 수 있지만 실무에선 거의 기본 세팅 그대로 사용한다.

<img width="400" alt="API방식" src="https://user-images.githubusercontent.com/54927837/96866182-eb040280-14a5-11eb-8114-7142c8a6a3d6.png">

* @ResponseBody 를 사용
  * HTTP의 BODY에 문자 내용을 직접 반환
  * viewResolver 대신에 HttpMessageConverter 가 동작
  * 기본 문자처리: StringHttpMessageConverter
  * 기본 객체처리: MappingJackson2HttpMessageConverter
  * byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음

```java
@Controller
public class HelloController {
   // 이런 방식 때문에 API 방식을 많이 쓴다  JSON 방식이다.(키와 벨류의 쌍으로 되어있는 방식)
   // <html></html> 방식은 xml방식이다.
   // 예전에는 많이 격돌을 했다가 최근에는 거의 JSON방식으로 통일이 되었다.
   // spring도 json으로 반환하는게 기본이 되었다.
   @GetMapping("hello-api")
   @ResponseBody
   public Hello helloApi(@RequestParam("name") String name){
       Hello hello = new Hello(); //ctrl + shift + enter 치면 뒤에 자동완성 됨
       hello.setName(name);
       return hello;
   }

   static class Hello{

       private String name;

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }
}
```

* TDD - 어떤걸 만들어야 하는데 먼저 검증할 수 있는 틀을 만든 다음에 개발을 하는 것