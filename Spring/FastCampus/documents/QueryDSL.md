# QueryDSL를 이용항 동적 쿼리 적용하기
### 동적 쿼리란?
* 실행시에 쿼리문장이 만들어져 실행되는 쿼리문을 말한다.
* MyBatis는 SQL의 유연성과 재사용성을 높이기 위해 SQL구문에서 동적 쿼리를 지원한다.

## QueryDSL이란?
* @Query로 등록한 쿼리는 프로젝트가 로딩되는 시점에 파싱되기 때문에 고정된 SQL만 사용할 수 있다.
* MyBatis같은 동적 쿼리를 사용하려면 QueryDSL을 이용해야한다.
* QueryDSL은 자바 코드로 쿼리를 작성할 수 있는 JPQL 빌더다.

### QueryDSL 설정
* Querydsl JPA와 Querydsl APT라이브러리를 추가해야 한다.

```xml
<!-- QueryDSL Setting -->
<dependency>
	<groupId>com.querydsl</groupId>
	<artifactId>querydsl-jpa</artifactId>
</dependency>

<dependency>
	<groupId>com.querydsl</groupId>
	<artifactId>querydsl-apt</artifactId>
</dependency>
```

* QueryDSL을 사용하려면 엔티티 클래스를 기반으로 쿼리 타입 클래스를 생서앻야 하며,
이를 위한 플러그인이 필요하다

![QueryDSL](/Java/documents/images/QueryDSL플러그인.jpg)

* QuerydslPredicateExecutor 인터페이스를 추가로 상속 받아야 한다.
```java
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.rubypaper.domain.Board;

public interface DynamicBoardRepository extends CrudRepository<Board, Long>, QuerydslPredicateExecutor<Board>{

}
```

* QuerydslPredicateExecutor의 메소드

![QuerydslPredicateExecutor](/Java/documents/images/QuerydslPredicateExecutor메소드.jpg)

### 동적 쿼리 작성
```java
@RunWith(SpringRunner.class) //스프링 테스트
@SpringBootTest
public class QueryDSLTest {
	@Autowired 
	private DynamicBoardRepository boardRepo;
	

	@Test
	public void testDynamicQuery() {
		// 검색 관련 정보 설정
		String condition = "Title";
		String keyword = "도우너";
		
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qBoard = QBoard.board;
		// 동적쿼리전용 클래스
		
		if(condition.equals("TITLE")) {
			// 쿼리를 동적으로 생성한다.
			builder.and(qBoard.title.like("%" + keyword + "%"));
			// title.을 찍으면 여러개가 나오는데 그것들아 다 연산자이다.
		} else if(condition.equals("CONTENT")) {
			builder.and(qBoard.content.like("%" + keyword + "%"));
		}
		// 위의 것들이 없었으면 그냥 * 로했을 때처럼 모든 목록이 조회되는 것이다.
		// 동적쿼리를 하려고 불린빌더와 큐보드를 통해서 하면 다양한 연산자를 메소듷여태로 지원한다. 
		
		// 모든 글 목록 조회
		Pageable pageable = PageRequest.of(0, 5);
		Page<Board> boardList = (Page<Board>) boardRepo.findAll(builder, pageable);
		// builder가 없으면 기본 셀렉트이고, 이걸넣어야 동적으로 쿼리가 가능하다.
		
		System.out.println("검색결과");
		for (Board board : boardList.getContent()) {
			System.out.println("---> " + board.toString());
		}	
	}
}
```