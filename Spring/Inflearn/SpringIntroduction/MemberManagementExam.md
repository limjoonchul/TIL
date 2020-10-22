# 회원 관리 예제
* 이 내용들은 인프런 김영한 강사님의 스프링 입문 강의 자료를 참고하고, 강의를 들으면서 내용을 정리한 것입니다.
* 아주 간단한 기능을 가지고 회원 관리 예제를 진행 했다.

## 진행 순서
* 비즈니스 요구사항 정리
* 회원 도메인과 리포지토리 만들기
* 회원 리포지토리 테스트 케이스 작성
* 회원 서비스 개발
* 회원 서비스 테스트

## 비즈니스 요구사항 정리
* 데이터 : 회원ID, 이름
* 기능: 회원 등록, 조회
* 아직 데이터 저장소가 선정되지 않은 가상의 시나리오를 정했다.

 <img width="400" alt="일반적인웹애플리케이션구조" src="https://user-images.githubusercontent.com/54927837/96871366-2655ff80-14ad-11eb-8a95-8bc5ccbf10b0.png">


* 컨트롤러 : 웹 MVC의 컨트롤러 역할
* 서비스 : 핵심 비즈니스 로직 구현
* 리포지토리 : 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
* 도메인 : 비즈니스 도메인 객체 예) 회원 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리된다.

 <img width="400" alt="클래스의존관계" src="https://user-images.githubusercontent.com/54927837/96871405-34a41b80-14ad-11eb-8918-dbb2ea7b30e1.png">


* 아직 데이터 저장소가 선정되지 않아서, 우선 인터페이스로 구현 클래스를 변경할 수 있게 설계했다.
* 데이터 저장소는 RDB, NoSQL 등등 다양한 저장소를 고민중인 상황으로 가정
* 개발을 진행하기 위해서 초기 개발 단계에서는 구현체로 가벼운 메모리 기반의 데이터 저장소 사용

## 회원 도메인과 리포지토리 만들기
### 회원 객체
```java
package hello.hellospring.domain;

import javax.persistence.*;

@Entity // 이 애노테이션을 적으면 jpa가 관리하는 엔티티가 된다.
public class Member {

    @Id // Primary Key라고 지정한다
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에 값을 넣을 때 ID 값을 넣지 않아도 자동으로 값이 증가하는 걸 identity라고 한다.
    // Identity전략이다. PK를 개발자가 넣어 줄 수도 있고 하지만, 이렇게 DB에서 자동으로 넣어주는걸 Identity라고 한다.
    private Long id; // 시스템이 정하는 id
    private String name;

    @Column(name="username") // DB이 Column인 username과 매핑이 되는 것이다. 이 애노테이션을 가지고 DB와 매핑을 하는 것이다.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
```

### 회원 레포지토리 인터페이스
```java
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    // 반환 값이 null일 수가 있는데 요즘에는 null을 그대로 출력하기 보다,
    // Optinal로 null을 감싸서 반환하는 방법을 많이 사용한다.
    List<Member> findAll();
}

```

### 회원 리포지토리 메모리 구현체
```java
package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // 실무에선 동시성문제가 있을 수 있어서 공유되는 변수일 때는 concurrentHashMap,AtomicLong을 사용해야한다.
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        // map을 끝까지 돌면서 찾으면 값을 반환하고, 없으면 optinal에 null이 포함되서 번환된다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //위의 Map<Long,Member>가 있을 때, store.values가 Member이다.
    }

    public void clearStore(){
        store.clear();
    }
}
```

## 회원 리조피토리 테스트 케이스 작성
* 개발한 기능을 실행해서 테스트 할 때 자바의 main 메소드를 통해서 실행하거나, 웹 애플리케이션의
컨트롤러를 통해서 해당 기능을 실행한다. 이러한 방법은 준비하고 실행하는데 오래 걸리고, 반복 실행하기 어렵고
여러 테스트를 한번에 실행하기 어렵다는 단점이 있다. 자바는 JUnit이라는 프레임워크 테스트를 실행해서 이러한 문제를 해결한다.

### 회원 리포지토리 메모리 구현체 테스트
* src/test/java 하위 폴더에 생성한다.

```java
package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// import org.junit.jupiter.api.Assertions;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메소드 실행이 끝날 때마다 어떤 동작을 하는 것이다.
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        // given
        Member member = new Member();
        member.setName("spring");
      
        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        // Optinal<Member> 로 되어있었기 때문에 반환값이 getId에 해당하는 Member객체가 반환
        System.out.println("result = " + (result == member));
        // Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
        // alt + enter 누르면 전구모양이 뜨는 옵션들의 요소들을 볼 수 있다.
    }

    @Test
    public void findByName(){

        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
        // shift + f6 누르고 이름을 바꾸면 다른 부분들도 같이 바뀜

        // when
        // Optional<Member> result =  repository.findByName("spring1");
        Member result =  repository.findByName("spring1").get();
        // 이렇게 뒤에 get을 붙이면 optional을 벗겨낸 후에 Member 객체로만 반환값을 받을 수 있다.

        assertThat(result).isEqualTo(member1);

         // then
    }

    @Test
    public void findAll(){
 
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
 
        // when
        List<Member> result = repository.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    // 전체 테스트할 시에 에러가 날 수 있다
    // 이유는 어떤 것부터 테스트할지 순서는 보장이 되지 않는다
    // 모든 테스트는 순서와 상관없이 메소드별로 따로 동작하게 설계해야 한다.
    // 그래서 테스트가 하나 끝나면 데이터를 claer해줘야한다.
    // 그래서 만든게 AfterEach이다. 하나의 메소드 테스트가 끝날 때마다
    // repository를 clear해준다.
}
```
* `@AfterEach` : 한번에 여러 테스트를 실행하면 메모리 DB에 이전 테스트의 결과가 남을 수 있다.
이렇게 되면 다음 테스트가 실행할 가능성이 있다. `@AfterEach`를 사용하면 각 테스트가 종료될 때마다 
이 기능을 실행한다. 여기서는 메모리 DB에 저장된 데이터를 삭제한다.
* 테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.

## 회원 서비스 개발
```java
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service // 애노테이션을 써야지 스프링에서 인식하고 컨테이너에 등록해준다.
@Transactional // 데이터를 저장하거나 변경할 때 transactional이 있어야 한다.
public class MemberService { // 서비스 클래스는 좀 더 비즈니스 로직에 가까운 이름들을 사용해야 한다!!
    // 서비스는 비즈니스에 의존적으로 설계한다.

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        // ctrl + alt + v 를하면 자동으로 반환받는 객체를 만들어줌.
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }); // 값이 있으면 예외를 던진다.

       validateDuplicateMember(member); // 중복 회원 검증

       // Optional로 감싸면 Optional 안에 member 객체가 있는 것이다.
       // Optional안에 여러 메소드가 있어서 사용하기 편함.
       // null이 있을 것 같으면 Optional을 사용하여 감싸서 표현을 해준다.

       memberRepository.save(member);
       return member.getId();
    }

    // 이런 로직이 생성되는 것은 따로 메소드로 만들어서 사용하는게 권장방법이다.
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); // 위의 Optional을 없애고 이렇게 바꿔서 사용할 수 도 있다.
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll(); // 핵심 관심 사항 나머진 공통 관심 사항
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
```

## 회원 서비스 테스트
### 기존에는 회원 서비스가 메모리 회원 리포지토리를 직접 생성하게 했다.
```java
public class MemberService {

 private final MemberRepository memberRepository = new MemoryMemberRepository();
}
```

### 회원 리포지토리의 코드가 회원 서비스 코드를 DI 가능하게 변경한다.
```java
public class MemberService {

 private final MemberRepository memberRepository;

 public MemberService(MemberRepository memberRepository) {
       this.memberRepository = memberRepository;
 }
 ...
}
```

### 회원 서비스 테스트
```java
package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    // new로 객체를 생성했기 때문에 여기 있는 memberRepository랑 원래 MemberService 클래스의 memberRepository랑 다른 객체이게 된다.
    // 이것이 좀 애매한 상황이 발생할 수 있는데 static Map<Long, Member> store = new HashMap<>();을 static으로 해놨기 때문에
    // 문제가 안생기지만 static을 없애면 문제가 생긴다.
    // 그래서 이런 애매한 상황을 남기지 않기 위해서 MemberService 클래스에서 생성자를 만들어 MemoryMemberRepository 클래스의 객체를 입력받도록 만들었다!

    @BeforeEach // 메소드 실행이 전에 호출된다. 서로 영향이 없도록 항상 새로운 객체를 생성하고 의존관계도 새로 맺어진다.
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    } // 이런 방법이 dependency injection 이다.

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // 테스트는 과감하게 이름을 한글로 바꿔도 된다. 영어권에서 사용하는것이 아니라면 빌드될 때 실제코드에 포함되지 않음
    @Test
    void 회원가입() {
        // given when then 형식에 맞춰서 테스트부분을 구현한다. 현업 개발자의 팁
        //given 뭔가가 주어졌을 때
        Member member = new Member();
        member.setName("hello");

        //when 이거를 실행했을 때
        Long saveId =  memberService.join(member);

        //then 결과가 이게 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 중복된 회원이 생겼을 때 예외처리를 하는 테스트
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
```
* `@BeforeEach` : 각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고,
의존관계도 새로 맺어준다.