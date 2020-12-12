# Spring Security
## 인증(Authentication)과 권한(Authorization)
* 인증을 통해 사용자를 식별하고 권한을 통해 시스템 자원에 대한 접근을 통제한다.
* 예를들어 사원증을 갖고 있는 직원만 사원증을 찍고 인증을 통해서 회사 건물에 들어갈 수 있다.
직급과 직무에 따라서 회사의 문서를 열람할 수 있는 권한이 다르다.

## Spring Security(스프링 시큐리티)란?
* 스프링 기반의 어플리케이션의 보안(인증,권한)을 담당하는 스프링 하위 프레임워크이다.
* 보안은 서블릿의 필터라는 개념을 이용해서 클라이언트의 요청이 컨트롤러에 전달되기 전에
필터를 통해서 클라이언트에 대한 보안을 필터링하는 것이다.
* 내부적으로 보안로직들이 구현되어 제공되기 때문에 개발자들은 구현된 객체들을 이용해서
어플리케이션을 개발하면된다.

### 시큐리티 사용자
* 시큐리티 스타터를 추가하면 기본 설정들이 자동으로 설정 되는데
시큐리티 스타터를 추가하고 어플리케이션을 실행하면, 시큐리티 사용자가 자동으로 생성되서
로그인을 걸쳐 인증이 된 사용자에게만 요청한 화면을 보여줄 수 있게 강제 설정된다.

* 시큐리티 스타터에 User 클래스가 작성되어 있어서 기본적인 ID는 `user` 비밀번호는
로그에  `Using generated security password :`로 출력되어 진다.

* 물론 자동으로 설정되어 있는 시큐리티 사용자의 아이디와 비밀번호를 
개발자가 원하는 상태로 변경할 수 있다. 

### 시큐리티 커스터마이징(개발자 설정) 
* 설정에 대한 클래스를 만들고 `@EnableWebSecurity` 와 `WebSecurityConfigureAdapter` 를 적용해주면
시큐리티 설정을 개발자가 직접 설정한 대로 사용할 수 있다.
  * @EnableWebSecurity - 시큐리티 적용에 필요한 객체들을 생성한다.
  * WebSecurityConfigureAdapter의 configure()를 통해서 시큐리티 설정을 커스터마이징 할 수 있다.
  * WebSecurityConfigureAdapter를 상속만 해도 로그인을 해야 원하는 요청화면을 보여주는 강제 설정이
  없어진다.
  
   ```java
   
   // 자동으로 메모리에 뜬 @configuration이 포함되어 있음
   // WebSecurityConfigurerAdapter이걸 상속받고 애노테이션을 추가하면 스프링시큐리티가 제공하는 것들을 커스터마이징 할 수 있다.
   @EnableWebSecurity 
  
   // 로그인 화면, 로그인실패화면을 원하는 페이지로 만들 수 있다.
   public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
   @Override // http security를 오버라이딩 하는 것으로 한다, 우리가 커스터마이징하겠다라는 의미다. 자동설정 클래스들이 동작중
   // 오버라이딩해서 자동설정클래스 로그인화면이 동작하지 않는다.
   protected void configure(HttpSecurity security) throws Exception {
   	
   }
  ```
  ![HttpSecurity주요메소드](/Java/documents/images/HttpSecurity주요메소드.jpg)
  * loginPage("/login")하고 .defaultSucessUrl("/loginSuccess",true)를 주면
  로그인이 성공했을 때 ()안에 페이지로 이동한다.
  
  * 접근 권한 없을 때 페이지 처리
     * exceptionHandling().accessDenigedPage("/...")를 사용하면
     접근 권한이 없는 사용자에게 요청한 페이지를 보여줄 수 없을 때 특정 페이지를 보여주게 처리하는 것이다.
  * 로그아웃 처리
     * invalidateHttpSession(true)를 하면 세션을 끊겠다는 의미이고,
     logoutSueccessUrl("/...")를 주면 로그아우이 성공했을 때 ()안에 페이지로 이동하겠다는 의미이다.
     
### 시큐리티 동작 원리
* 서블릿 필터는 클라이언트의 요청을 가로채서 전처리와 후처리를 수행하거나 요청을 리다이렉트하는 용도로 사용한다.
* 필터 하나가 하나의 기능을 수행하고 여러 기능이 필요할 때 여러 개의 필터를 만들고 연쇄적으로
이뤄지도록 필터 체인을 형성하여서 사용한다.

* 이처럼 동작하는 스프링 시큐리티에서 제공하는 필터들이 있다.
![시큐리티필터](/Java/documents/images/시큐리티필터.jpg)

#### 스프링 시큐리티 동작 원리
  * `UsernamePasswordAuthenticationFilter`는 사용자가 입력한 인증 정보를 이용해서 인증을 처리한다.
  * `FilterSecurityInterceptor`는 인증에 성공한 사용자가 요청에 접근할 권한이 있는지 검증한다.
  
  ![시큐리티동작](/Java/documents/images/시큐리티동작원리.jpg)
  
### JPA 연동
* 인증 관리 필터가 인증을 처리하기 위해서는 사용자 정보가 저장된 UserDetails객체와
UserDetails객체에 실제 데이터베이스에서 검색한 사용자 정보를 저장하는 UserDetailsService 객체가 필요하다.
* 인증 관리자는 UserDetailsService객체를 통해 UserDetails 객체를 획득하고 이 UserDetails 객체에서 인증과 권한에
필요한 정보들을 추출하여 사용한다.
* UserDetailsService를 커스터마이징 하고 싶으면 UserDetailsService를 구현한 클래스를 작성하여 등록하면 된다.  

```java
@Service
public class BoardUserDetailsService implements UserDetailsService{

	@Autowired
	private MemberRepository memberRopo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// MemberRepository 객체를 이용하여 Member 정보를 조회한다.
		Optional<Member> findMember = memberRopo.findById(username);
		if (!findMember.isPresent()) {
			// 검색 결과가 없다면
			throw new UsernameNotFoundException(username + "사용자가 없네요");
		} else {
			Member member = findMember.get();
			return new SecurityUser(member);
		}
	}
}
```

```java
// 스프링 시큐리티 스타터를 추가하면 유저가 자동으로 생성된다 로그인 화면이 자동으로생성되서
// user랑 로그에 뜨는 비밀번호를 쳐서 들어갔었잖아 그게 이 USER 클래스가 있어서 그래
// 그래서 USER객체를 커스터마이징하는 것이다.
public class SecurityUser extends User{
	private static final long serialVersionUID = 1L;
	
	public SecurityUser(Member member) {
		// 아이디, 비밀번호(암호화하지 않은), 권한을 부모 생성자쪽에 전달한다. "{noop}" 이걸뺌
		super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString())); // role을 리시트형태로 변환
		
	}

}
```
### 비밀번호 암호화
* 비밀번호를 암호화하지 않고 평문으로 저장하고 사용하면 보안상 심각한 문제를 초래할 수 있다.
그래서 DB에 저장될 때도 암호화된 값으로 저장될 수 있도록 시큐리티를 통해서 비밀번호를 암호화 할 수 있다.

#### BCryptPasswordEncoder
* 스프링 시큐리티는 패스워드를 암호화할 수 있도록 PasswordEncoder 인터페이스를 구현한 다양한 클래스들을 제공한다.
PasswordEncoderFactories의 createDelegatingPasswordEncoder()를 호출하면 BCryptPasswordEncoder 객체가 반환된다.
* BCryptPasswordEncoder는 패스워드 암호화에 특화되어 있으면서 가장 안전한 해시 알고리즘인
BCrypt를 사용한다.

```groovy
@Bean // 비빌번호를 암호화하는  객체가 리턴이된다. 공장에서 인코더하나를 얻어냈다 빈등록했으니 자동으로 메모리에 등록됨
	public PasswordEncoder PasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
```
```java
@RunWith(SpringRunner.class)
@SpringBootTest
@Commit // 테스트메소드가 실행되고 db연동을 처리했다면 커밋하라는 것임
public class PasswordEncoderTest {
	@Autowired
	private MemberRepository memberRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Test //비밀번호를 암호화헤서 테이블에 저장함
	public void testInsert() {
		Member member = new Member();
		member.setId("manager2");
		member.setPassword(encoder.encode("manager456"));
		member.setName("매니저2");

		member.setRole(Role.ROLE_MANAGER);
		member.setEnabled(true);
		memberRepo.save(member);
	}
}
```
#### 결과

![비밀번호 암호화](/Java/documents/images/비밀번호암호화.png)
  
  
  
