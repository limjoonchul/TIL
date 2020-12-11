# Spring Data JPA
* JPA를 애플리케이션에 적용하기 위해서는 JPA 구현체에 대한 다양한 의존성도 추가해야하고,
 복잡한 persistence.xml 설정도 필요하다.
* 스프링 부트는 JPA 연동에 필요한 라이브러리들과 복잡한 XML설정을 자동으로 처리하기 위해 JPA스타터를 제공한다.

## 이클립스에서 프로젝트 생성
* Spring Boot Starter를 통해서 스프링 부트 프로젝트를 생성한다.
### JPA 프로퍼티 설정
* 일반 JPA 프로젝트에서 /META-INF/persistence.xml에 설정했었던
내용들을 Spring Boot에서는 application.properties 파일에 작성한다.
```properties
## Banner Setting
spring.main.banner-mode=off

## Logging setting
logging.level.org.springframework=info

## DataSource Setting 
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:tcp://localhost/~/test
spring.datasource.username=sa
spring.datasource.password=

## JPA Setting 
spring.jpa.hibernate.ddl-auto=create
spring.jpa.generate-ddl=false
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.properties.hibernate.format_sql=true

## JPA Logging Setting 
logging.level.org.hibernate=info
```
### 엔티티 매핑
* @Entity를 줘야 이 객체가 테이블과 매핑되는 객체라는 것을 알려주는 것이다.
```java
@Data
@Entity
public class Board {
	@Id
	@GeneratedValue
	private Long seq;
	private String title;
	private String writer;
	private String content;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;

}
```
### Repository 작성
* Repository 인터페이스는 스프링에서 제공하는 Repository를 상속하여 작성하는데,
CrudRepository를 상속받으면 이 인터페이스를 구현한 crud기능이 있는 인터페이스를 쉽게 만들 수 있다.
CrudRepository만 상속하면 애가 제공하는 여러 추상 메소드들이 상속이 되고 
추상 클래스들을 구현한 구현클래스들을 만들어야 하는데, 안 만들어도 된다
* 즉 인터페이스만 정의하면 구현클래스들을 스프링부트가 자동으로 만들어 준다. (이 Repository가 DAO클래스와 같은 것)
등록 수정 삽입 상세조회 까지만 해줘서 목록조회는 따로 등록해야 한다.
목록 조회도 정의만 해 놓으면 목록 조회에 대한 구현도 자동으로 이뤄진다.

* CrudRepository<T, ID> - T : 엔티티의 클래스 타입, ID : 식별자 타입
```java
package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rubypaper.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
	List<Board> findByTitle(String searchKeyword);
	
	List<Board> findByContentContaining(String searchKeyword); // ~포함된 의미로 like연산으로 바뀐다
	
	List<Board> findByTitleContainingOrContentContaining (String title, String content); 
	// 각 조건에서 설정할 값을 세팅해줘야 한다.
	
	List<Board> findByContentContainingOrderBySeqDesc(String searchKeyword); // 내용에 특정 단어가 포함된 게시글 목록을 가져오는데 seq를 내림차순해서 역순으로 조회함
	
//	List<Board> findByTitleContaining(String searchKeyword, Pageable pageable); 
	
	Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable); //리턴타입만 page로 바꿈
}
```

### 쿼리 메소드
* 복잡한 JPQL을 메소드로 처리할 수 있도록 작성하는 메소드로 메소드명 통해서 SQL이 매핑된다.
* 쿼리 메소드에서 엔티티 이름을 생략하면 Repository 인터페이스에 선언된 타입 정보를 기준으로 자동으로 엔티티 이름이 적용된다.
* 객체가 자동으로 생성되는 것이여서 메소드 이름에 오타가 생기면 빈 생성에 에러가난다.

![쿼리메소드유형](/Java/documents/images/쿼리메소드유형.jpg)

![쿼리메소드유형2](/Java/documents/images/쿼리메소드유형2.jpg)

* Page<T> 타입은 리스트보다 페이징 처리에 필요한 다양한 정보들을 제공한다.

![page 메소드](/Java/documents/images/page메소드.jpg)

```groovy
List<Board> findByTitle(String searchKeyword);
	
List<Board> findByContentContaining(String searchKeyword); // ~포함된like연산으로 바뀐다
	
List<Board> findByTitleContainingOrContentContaining (String title, String content); 
// 각 조건에서 설정할 값을 세팅해줘야 한다.
	
List<Board> findByContentContainingOrderBySeqDesc(String searchKeyword); // 내용에 특정 단어가 포함된 게시글 목록을 가져오는데 seq를 내림차순해서 역순으로 조회함
	 
Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable); //리턴타입만 page로 바꿈
``` 
```groovy
@Before // 테스트전에 실행되서 초기화시킨다.
	public void dataPrepare() {
		for (int i = 1; i < 200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 : " + i);
			board.setWriter("테스트");
			board.setContent("테스트 내용" + i);
			board.setCreateDate(new Date());
			board.setCnt(0L);
			boardRepo.save(board);
		}
	}
	
	@Test 
	public void testFindByTitleContaining() { 
		// 리턴타입을 page로하면 실제 글 목록도 들어가 있지만 더 다양한 정보들이 들어있다
		Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "seq"); //타이틀 컬럼에 17이들어간걸 조회하되 첫페이지에 해당하는 10개만 가져옴, 내림 차순 정렬
		// 페이징 처리 쿼리 자동으로 갯수를 정해서 가져온다  정렬을 할때 pageable하는 순간 이때할 수 있다,
		// page 번호는 0부터 시작한다. 따라서 1페이지를 보고싶으면 0
        // size는 몇 건의 데이터를 가져올 것인지 지정한다.
        // 이것만 지정하면 검색키워드에 해당하는 값을 가져올 때 편하게 가져올 수 있음
		Page<Board> pageInfo = boardRepo.findByTitleContaining("17",pageable);
		System.out.println("Page Size : " + pageInfo.getSize());
		System.out.println("Total pages : " + pageInfo.getTotalPages());
		System.out.println("Total Count : " + pageInfo.getTotalElements());
		System.out.println("Next Page : " + pageInfo.nextPageable());
		
		List<Board> boardList =pageInfo.getContent();// 페이지결과로 이루어진 목록결과
		System.out.println("검색 결과");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}

//	@Test // sorting 내림차순 정렬
	public void testFindByContentContainingOrderBySeqDesc() { // 내용에 17이 들어있는 값을 조회하는데 내림 차순 sorting
		List<Board> boardList = boardRepo.findByContentContainingOrderBySeqDesc("17");
		System.out.println("검색 결과");
		
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
//	@Test // reop에 저장된 목록의 메소드명과 같게 해줘야 한다. like연산이 수행된다. 17이들어간 모든 목록을 조회함 like 연산이 or로연산되서 다른 조건을 줄 수 있다
	public void testFindByTitleContainingOrContentContaining() { // 제목에는 15, 내용엔 17이 들어가 있는 내용들을 검색
		List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("15","17");
		System.out.println("검색 결과");
		
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
	
//	@Test // reop에 저장된 목록의 메소드명과 같게 해줘야 한다. like연산이 수행된다. 17이들어간 모든 목록을 조회함
	public void testFindByContentContaining() {
		List<Board> boardList = boardRepo.findByContentContaining("17");
		System.out.println("검색 결과");
		
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
//	@Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepo.findByTitle("테스트 제목 : 10");
		System.out.println("검색 결과");
		
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
```

### @Query 어노테이션
* JPQL이나 성능성 특정 DB에 종속적인 네이티브 쿼리를 사용한다.
* JPQL은 SQL과 거의 비슷한데 검색대상이 테이블이 아니라, 1차 캐시에있는 영속상태의 엔티티 객체이다. 그래서 메모리사용량이 많다..
  * JPQL 문법 구조는 똑같지만 1차 캐시의 엔티티들이 객체상태로 있어야만 검색가능하다 그래서 메모리사용량이 많다.
  * JPQL을 엔티티대상으로 작성해놓으면 SQL로 변환이되서 동작한다. 서블릿이 JSP를 만들면 JSP가 실행될 때 복잡한 서블릿크랠스로 변환되서 실행되는 구조와 비슷하다
  * 그리고 컬럼이 사용되는게 아니라 엔티티 객체의 변수이름이 사용 된다.(SELECT, WHERE, ORDER BY, GROUP BY)
* JPQL은 나중에 SQL로 변경되서 동작이이 이루어진다. JSP가 서블릿으로 변형되서 동작하는 것처럼
* @Query로 등록한 SQL은 프로젝트가 로딩되는 시점에 파싱되기 때문에 그 시점에 오류가 있으면 예외가 발생한다.
  오류가 없는지 잘 확인해야 한다.
```groovy
@Query("SELECT b FROM Board AS b WHERE b.title like %:keyword% ORDER BY b.seq DESC") // 컬럼이나 테이블 이름이들어가는게 아니라 엔티티 클래스 이름(vo 객체)이 들어간다 대소문자 구분해야한다.
// select뒤에 오는건 알리아스 as생략가능 where은 컬럼에 대한 제약조건  서리키워드고 1번파라미터에 매핑된다 b board seq는 변수이름 대소문자 구분해야함 %?1% 위치기반 파라미터
List<Board> queryAnnotationTest1(@Param("keyword") String keyword);
	
@Query("SELECT b.seq, b.title, b.writer, b.createDate FROM Board AS b WHERE b.title like %:keyword% ORDER BY b.seq DESC")
List<Object[]> queryAnnotationTest2(@Param("keyword") String keyword);
// 특정 컬럼들만 조회하니 board 엔티티를 리턴할 수없고 오브젝트로 리스트 컬렉션으로 담아서 리턴한다.
	
@Query("SELECT b FROM Board AS b ORDER BY b.seq DESC") // 컬럼이나 테이블 이름이들어가는게 아니라 엔티티 클래스 이름(vo 객체)이 들어간다 대소문자 구분해야한다.
List<Board> queryAnnotationTest3(Pageable pagable);
```
```groovy
@Test 
public void testQueryAnnotationTest3() { 
	Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
	List<Board> boardList =boardRepo.queryAnnotationTest3(pageable);
	System.out.println("검색 결과");
	for (Board board : boardList) {
		System.out.println("---> " + board.toString());
	}
}

@Test 
public void testQueryAnnotationTest2() { 
	
	List<Object[]> boardList =boardRepo.queryAnnotationTest2("테스트 제목 : 17");
	System.out.println("검색 결과");
	for (Object[] row : boardList) {
		System.out.println("---> " + Arrays.toString(row));
	}
}

@Test 
public void testQueryAnnotationTest1() { 
	
	List<Board> boardList =boardRepo.queryAnnotationTest1("테스트 제목 : 17");
	System.out.println("검색 결과");
	for (Board board : boardList) {
		System.out.println("---> " + board.toString());
	}
}
```
### 연관관계 매핑 - JPA에서 엔티티 객체를 통해서 테이블을 만든다는 기본 전제를 알고 있어야 한다.
* 관계형 데이터베이스에서는 관련된 데이터를 여러 테이블에 나누어 저장하고 조인을 통해 조회한다.
* 엔티티 역시 다른 엔티티와 관계를 맺고 있으며, 참조 변수를 통해 연관된 데이터를 조회할 수 있다.
* 즉 테이블의 관계와 객체의 관계는 정확하게 일치하지 않는데 이를 패러다임의 불일치라고 한다.
  * 테이블은 Foreign key를 통해서 연결하고, 객체는 참조 변수를 이용해서 연결한다 
  그래서 관계가 일치하지 않는다고 표현한 것이다.

#### 단방향 연관관계 설정
* 엔티티는 참조를 통해서 관계를 맺고, 테이블은 Foreign key를 통해서 관계를 맺는다.
* 연관관계 매핑 고려사항
   * 방향 - 단방향과 양뱡항이 있는데, 방향은 객체에만 적용이 된다. 참조변수가 있는 쪽에서 반대쪽의 객체를 참조하는 것이 방향이다. 
   테이블은 Foregin key로 연결하면 조인을 통해서 하나의 테이블이 되는거니깐 결국 각각의 테이블에서 서로 조회를 할 수 있다
   * 다중성 - 다 대 일(N:1), 일 대 다(1:N), 일 대 일(1:1), 다 대 다(N:M)이 있다.
   * 연관관계 주인 - 객체를 양방향 연관관계로 매핑하려면 연관관계의 주인을 정해야 한다.
   일반적으로 연관관계의 주인은 다(N)쪽이 된다.
   테이블은 원래 양방향이니깐 상관없지만, 객체를 양뱡향으로 만들려할 때 한쪽을 주인으로 정해야 한다.

![연관관계](/Java/documents/images/객체와테이블의연관관계.jpg)

#### 다대일 단방향 매핑
* @ManyToOne
   * Optional - 연관 엔티티가 반드시 설정되어있어야 하는지를 나타내는 것이다.
   true - Null을 허용하겠다는 의미이고, false - 다른 객체를 참조하고 있어야 한다.
   * Fetch - 패치 전략을 설정한다. eager는 즉시 조회, lazy는 늦은 조회 라는 의미에서 사용되는데,
   ManyToOne에서 Many쪽에서 패치전략을 설정할 때 연관된 객체가 하나밖에 없으니 EAGER가 기본으로 설정되어 있고,
   OneToMany에서는 One에 연관된 객체가 여러개가 있어서 바로 조회하기 보다는 실제로 사용할 때 참조하는 객체를 가져오는 것으로
   Lazy가 기본 값이다.
   * Cascade - 영속성 전이 기능을 설정한다. 연관 엔티티를 같이 저장하거나 삭제할 때 사용한다.
   
* @JoinColumn
   * name 속성을 통해 참조하는 테이블의 외래 키 컬럼을 매핑한다.
* 내부조인으로 변경하기
   * 다대일 매핑을 처리할 때, 외부 조인 보다 내부 조인이 성능이 좋다.
   * 반드시 참조 키에 값이 설정되는 상황이면 외부조인을 내부조인으로 변경하는 것이 좋다.   
```java
@Getter
@Setter
@ToString(exclude="member") // 이렇게 해야 양방향 조회할 때 toString에서 참조변수를 계속 참조하는 무한루프가 안생긴다.
@Entity
public class Board {
	@Id
	@GeneratedValue
	private Long seq;
	private String title;
//	private String writer;
	private String content;
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	private Long cnt;

	@ManyToOne(fetch= FetchType.EAGER) // fetch= FetchType.Eager// 여러개의 게시글이 하나의 회원과 매핑될 수 있다.
	@JoinColumn(name="MEMBER_ID", nullable = false) // FK로 사용할 컬럼을 지정한다.
	private Member member; // 회원 객체를 참조하기 위함
}
```

#### 양방향 연관관계 설정
* 객체는 양방향으로 매핑하려면 각각의 객체가 서로 참조변수를 가지고 있어야 한다.
* mappedBy
   * mappedBy는 양방향 연관관계에서 연관관계의 주인과 관련된 속성이다.
   * 객체는 서로를 참조하는 단방향 관계 두 개가 필요하지만 테이블은 외래키 하나로 양방 조회가 가능하다.
   * 엔티티를 양방향으로 매핑하려면 매핑에 참여하는 참조변수가 두개인데 외래키는 하나이기 때문에 둘 사이에 차이가 발생한다.
   * 보통 연관관계 주인은 테이블에 외래 키가 있는 엔티티로 지정한다.
   그리고 연관관계 `주인이 아닌 쪽에 mapppedBy`를 작성하여 주인이 아님을 설정한다.

![양뱡향연관관계](/Java/documents/images/양뱡향객체연관관계.jpg)

```groovy
@Entity
@Data
public class Member {
	@Id
	@Column(name = "MEMBER_ID") // 참조되는 컬럼을 설정
	private String id;
	private String password;
	private String name;
	private String role;
	
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	private List<Board> boardList = new ArrayList<Board>();
}
```

* 양뱡향 매핑시 주의 사항
  * lombok의 @ToString으로 순환 참조에 빠질 수 있는데 이때, @ToString어노테이션을 따로 써줘야 하는데
  `@ToString(exclude="member")` 이렇게 작성하면 된다.
  ![양방향매핑주의사항](/Java/documents/images/양방향매핑주의.jpg)
  
#### 영속성 전이
* 특정 엔티티를 영속 상태로 만들거나 삭제 상태로 만들 때 연관된 엔티티도 같이 처리할 경우 영속성 전이를 사용한다.
```java
// mappedBy는 양방향 매핑에서 연관관계의 주인이 아닌 쪽 변수에 선언한다. 속성 값은 연관관계 주인 변수 이름이다.
	@OneToMany(mappedBy="member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Board> boardList = new ArrayList<Board>();
```
* 예를들어서 Member 객체와 Board객체가 있을 때 Board에서 Member의 id를 외래키로 가지고 있을 때,
Member객체를 삭제할려고 하면 Board객체의 Member의 id를 참조하고 있는 ROW를 제거하고
Member객체를 삭제해야 한다. 이런걸 한번에 처리할 수 있게 속성을 정의하는 것이다.
 


