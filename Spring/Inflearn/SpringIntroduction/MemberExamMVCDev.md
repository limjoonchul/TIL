# 회원 관리 예제 - 웹 MVC 개발
## 회원 웹 기능 - 홈 화면 추가
* 원래 localhost:8080으로 들어가면 static폴더의 index.html파일이 화면에 띄워지게 되었는데,
정적 콘텐츠에서 설명할 때 먼저 스프링 컨테이너에서 먼저 해당 주소가 있는지 찾고, 없을 때
정적 콘텐츠로 가서 index.html이 띄워지게 되는 것이였다. 그런데 여기서 '/'를 매핑하는 HomeController를
작성해 놨기 때문에 컨테이너에서 이 것을 먼저 찾고 home.html을 실행을 하는 것이다.

* get - URL에 창에 enter치는 면 get매핑, 조회할 때 사용
* post - 데이터를 form같은데 전달할 때 사용

### 홈 컨트롤러 추가
```java
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}
```

### 회원 관리용 홈
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
    <div>
        <h1>Hello Spring</h1>
        <p>회원 기능</p>
        <p>
            <a href="/members/new">회원 가입</a>
            <a href="/members">회원 목록</a>
        </p>
    </div>
</div> <!-- /container -->
```
* 컨트롤러가 정적 파일보다 우선순위가 높다.

## 회원 웹 기능 - 등록
* 스프링이 createMemberForm.html에 있는 input태그의 name="name"을 보고 입력받은 값을 MemberForm의 name이랑 매핑을 해줘서
여기에 입력받은 값을 넣어준다. setName을 통해서 값이 들어간다.
### 회원 등록 폼 컨트롤러
```java
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
         this.memberService = memberService;
    }
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }
}
```

### 회원 등록 폼 HTML
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
    <form action="/members/new" method="post">
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" id="name" name="name" placeholder="이름을 입력하세요">
        </div>
        <button type="submit">등록</button>
    </form>
</div> <!-- /container -->
</body>
</html>
```

## 회원 등록 컨트롤러
### 웹 등록 화면에서 데이터를 전달 받을 폼 객체
```java
package hello.hellospring.controller;
public class MemberForm {
 private String name;
 public String getName() {
 return name;
 }
 public void setName(String name) {
 this.name = name;
 }
}
```

### 회원 컨트롤러에서 회원을 실제 등록하는 기능
```java
@PostMapping(value = "/members/new")
public String create(MemberForm form) {
 Member member = new Member();
 member.setName(form.getName());
 memberService.join(member);
 return "redirect:/";
}
```

## 회원 웹 기능 - 조회
* ${...} 모델 안에 있는 값을 꺼내서 치환하는 것이다. th:each 는 타임리프의 문법 list를 돈다.
### 회원 컨트롤러에서 조회 기능
```java
@GetMapping(value = "/members")
public String list(Model model) {
 List<Member> members = memberService.findMembers();
 model.addAttribute("members", members);
 return "members/memberList";
}
```
### 회원 리스트 HTML
```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org">
<body>
<div class="container">
   <div>
      <table>
           <thead>
               <tr>
                    <th>#</th>
                    <th>이름</th>
               </tr>
           </thead>
           <tbody>
               <tr th:each="member : ${members}">
                   <td th:text="${member.id}"></td>
                   <td th:text="${member.name}"></td>
               </tr>
           </tbody>
       </table>
   </div>
</div> <!-- /container -->
</body>
</html>
```