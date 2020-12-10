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



