# 스프링 빈과 의존관계
* 화면으로 출력하기 위해서 컨트롤러랑 뷰템플릿이 필요하다 .
* 회원가입하고 가입한 화면을 html로 해서 화면에 출력해주고 그럴려면 멤버컨트롤러를 만들어야하는데 멤버 컨트롤러가 멤버서비스를 통해서 회원 가입을하고
멤버서비스를 통해서 데이터를 조회 할 수 있어야하는데 이런게 서로 의존관계가 있다 라고 표현하는 것이다.
* 멤버 컨트롤러가 멤버서비스, 멤버서비스가 멤버리포지토리를 의존한다.
* 클래스를 만들면 스프링에서 컨테이너를 만드는데 @Controller를 해놓으면 해당 클래스 객체를 생성해서 넣어두고 관리한다.
이런 것이 스프링 컨테이너에서 스프링 빈이 관리된다고 표현한다.
* 각각의 클래스에 Controller, Service, Repository 애노테이션을 써준다. 정현화된 패턴이다.
  * Contrller - 외부의 요청을 받는다.
  * Service - 비즈니스로직을 만든다.
  * Repository - 데이터를 저장한다.

## 스프링 빈을 등록하는 2가지 방법
### 컴포넌트 스캔과 자동 의존관계 설정(애노테이션을 써주는 방법이 컴포넌트 스캔이다.)
* @Component가 있으면 스프링이 실행이 될 때 객체로 생성해서  컨테이너에 등록한다.
* 원래는 @Component를 사용해줘야 하는데 코드상에서 @Service, @Controller, @Repository 등으로 사용이 되어있다
이유는 @Service, @Controller, @Repository에 이미 @Coponent가 등록이 되어있기 때문이다. Doc페이지를 들어가서 확인해보면 알 수 있다.
 
* 컴포넌트를 사용하는 상황은 @SpringBootApplication이 있는 클래스부터 시작해서 그 클래스가 속해있는 패키지와
하위 패키지에 대해서만 자동으로 스프링이 뒤져서 스프링 빈에 등록한다.
그래서 다른 패키지에서 만들어서 컴포넌트를 사용한다해도 컴포넌트 스캔의 대상이 되지 않는다.
@ComponentScan이 있어야 찾아서 들어온다. 

### 컴포넌트 스캔 스프링 빈 등록하기
* 회원 서비스 스프링 빈 등록 
   * 참고 : 생성자 `@Autowired`를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입한다.
   생성자가 1개만 있으면 `@Autowired`는 생략할 수 있다.
   * 멤버컨트롤러 클래스가 멤버서비스 클래스를 포함해서 사용하는데 스프링이 관리하면 스프링 컨테이너에 등록하고 스프링컨테이너에서 받아쓰도록 바꿔야한다.
   * 여러 컨트롤러에서 멤버서비스를 갔다써서 새로운 객체들이 생성될 수도 있고,
   * 많은 기능들이 구현되지 않은 멤버서비스를 여러 인스턴스를 생성할 필요없고 공용으로 사용하면 되기 때문에
   * 멤버 컨트롤러가 생성이 될 때 컨테이너에서 생성자를 호출하는데 @AutoWired가 붙어있으면 컨테이너에 있는
   멤버 서비스를 파라미터 멤버서비스와 매핑을 해준다.
```java
@Service
public class MemberService {

   private final MemberRepository memberRepository;

   @Autowired
   public MemberService(MemberRepository memberRepository) {
       this.memberRepository = memberRepository;
   }
}
```

* 회원 리포지토리 스프링 빈 등록
```java
@Repository
public class MemoryMemberRepository implements MemberRepository {}
```
```text
참고!
 * 스프링이 관리를 하게 되면 스프링 컨테이너에 등록을 하고 컨테이너에서 받아서 쓰도록 바꿔야 한다.
왜냐하면 만약 MemberService를 new로 객체를 생성해서 사용할 수 있지만, 그렇게 되면 MemberController 말고
다른 콘트롤러들이 객체를 가져다가 쓸 수 있게 된다. 그리고 new 해서 객체를 생성해도 별다른 기능이 없고, 여러개의 객체를 생성해서
사용할 필요가 없을 수 있다. 그래서 객체를 공용으로 쓰는게 좋다.

* 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때 기본적으로 싱글톤으로 등록을 한다.(유일하게 하나만 등록해서 공유한다.)
따라서 같은 스프링 빈이면 모두 같은 인스턴스이다.
```

### 자바 코드로 직접 스프링 빈 등록하기
* @Service, @Repository, @Autowired 애노테이션을 제거하고 진행한다.
* SpringConfig라는 클래스를 만들고 설정한다는 @Configuration을 붙여서 @Bean이 붙여진 메소드들의 객체들이 빈으로 등록이 된다.
```java
package hello.hellospring;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig { 
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean // 스프링 빈을 등록할거라는 의미이다.
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```

* XML로 설정하는 방식도 있지만 최근에는 잘 사옹하지 않는다.
* DI에는 필드 주입, setter 주입, 생성자 주입 3가지 방법이 있다.
의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.
```text
* 필드 주입은 별로 좋지 않음 왜냐하면 바꿀 수 있는 방법이 없다 스프링 뜰 때만 넣어 주고 중간에 바꿀 수 있는 방법이 없다.
* setter 주입은 단점은 누군가가 멤버컨트롤을 호출했을 때 public으로 열려 있어야 한다.
setMemberService를 중간에 바꿔치기 할 이유가 없다. 그런데 얘가 public하게 노출이 되는 것이다. 
그래서 중간에 잘 못 바꾸면 문제가 생기게 된다. 애플리케이션 로딩할 때 조립할 때 바꾸는 것이지, 한번 세팅을 하고 나면 바꿀 일이 없다.
* 생성자 주입 사용을 권장한다, 처음에 애플리케이션 조립된다고 표현하는데 스프링 컨테이너에 올라가고 세팅이 되는 시점에 파라미터로 들어오고 끝난다.
처음 생성할때 세팅하고 그 후에 막을 수 가 있다.
* 실행중에 동적으로 변경된다는 것은 서버가 떠있는데 중간에 바꿔치기가 되는 것이다
 그런데 그런 일이 거의 없어서 바꿀일이 없다라고 말한 것  그럴 일이 있을 때는 config 파일을 수정하고 서버에 다시 올린다.
* 상황에 따라 구현 클래스를 변경해야 하는 것은 이 강의에서 설정상 저장소를 아직 정하지 않은 가상 시나리오를 통해서 진행이 되는데
 나중에 Repository를 DB로 바꾼던가 하는 상황을 진행할 것인데 나머지 코드에 손대는 것이 없이 바꿔치기 할 수 있다. 그런 상황을 만들기 위해서이다.
그래서 컴포넌트 스캔을 사용할 때는 여러 코드를 바꿔야 하는데 자바 코드로 직접 스프링 빈에 등록하는 방법은 설정 파일만 바꿔주면 된다. 직접 설정파일을 운영할 때 의 장점이다.
```
* `@Autowired`를 통한 DI는 `helloController`,`memberService`등과 같이 스프링이 관리하는 객체에서만 동작한다.
스프링 빈으로 등록하지 않고 내가 직접 생성한 객체(new로 생성하는 객체)에서는 동작하지 않는다.



