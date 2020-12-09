# JPA(Java Persistance API)
## JPA에 앞서 ORM과 JDBC같은 기술들을 비교
## 스프링과 JPA
* ORM 프레임워크는 DB연동 기능 코드뿐만 아니라 SQL까지 제공한다.
* 하이버네이트를 시작으로 많은 ORM 프레임워크가 등장했으며, 이런 ORM들을
통합하여 표준화시킨 것이 JPA이다.
* 스프링 데이터 JPA는 스프링 부트에서 JPA를 쉽게 사용할 수 있도록 지원한다.
* JPA만을 이용해서 순수하게 개발이 가능하지만, SQL설정과 많은 라이브러리 다운로드를 해야한다.
  그래서 스프링과 JPA를 연동해서 사용하지만 좀 편해지긴 하지만 여전히 연동에 필요한 라이브러리 관리와 XML설정이 복잡하다.
  이런 복잡한 문제를 스프링부트에서 최소화 시켜준다.
  
## DB연동 기술 구분
* SQL을 직접 다루는 기술 : JDBC, 스프링 JDBC, MyBatis, IBatis,
   * IBatis, MyBatis 같은 기술들은 SQL을 XML파일에 구현해서 사용한다.
* SQL을 직접 다루지 않는 기술 : Hibernate
   * 하이버네이트 같은 ORM프레임워크는 자체에서 SQL을 생성한다.
   * ORM은 객체의 상태(변수의 값)를 데이터베이스에 저장하는 것이 아니다.
   ORM은 마치 객체 통째로 Map과 같은 컬렉션에 저장하는것과 유사하다.
   * 객체를 데이터베이스가 아닌 컬렉션에 저장하면 복잡한 SQL이 필요없고, 데이터 관리가 매우 쉬워진다.
   
### SQL을 직접 다루는 예
* SQL을 직접 다루는 예에서 테이블에 값을 저장하기 위해서는 VO클래스를 이용해서
SQL을 사용해서 값을 매핑시켜서 넣어야 한다.
#### 그런데 만약 다른 값이 추가 된다면?
* VO객체에 변수를 추가해야하고, Board에도 컬럼을 추가해야하고, SQL에서는 검색 수정 삽입 삭제 등의
기능에 따라 다시 구현을 해야 한다. 하나가 추가되면 관련된 사항들을 다 수정해야 한다. 그래서 너무 불편하다  
```java
public Class BoardVO{
   private Long seq;
   private String title;
   private String wirter;
   private String content;
   private Date createDate;
   private Long cnt;
}
```
```roomsql
CREATE TABLE BOARD(
        SEQ NUMBER(5) PRIMARY KEY,
        TITLE VARCHAR2(200),
        WRITER VARCHAR2(20),
        CONTENT VARCHAR2(2000),
        CREATEDATE DATE DEFAULT SYSDATE,
        CNT NUMBER(5) DEFAULT 0
);
```
```roomsql
INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES(?, ?, ?, ?)

UPDATE BOARD SET TITLE=?, WRITER=?, CONTENT=? WHERE SEQ=?

DELETE BOARD WHERE SEQ=?

SELECT SEQ, TITLE, WRITER, CONTENT, CREATEDATE, CNT WHERE SEQ=?
```
  
### SQL을 직접 다루지 않는 예
* 진짜 컬렉션에 넣어서 사용한다는 것이 아니라 마치 컬렉션과 같이 비슷한 형태로 데이터를 저장하는 것이다.

![ORM](/Java/documents/images/ORMExample.jpg)

## JPA란?
### JPA 등장 과정
* 하이버네이트는 EJB의 엔티티 빈이 가지는 여러 문제들을 해결하면서 등장한 오픈소스 프레임워크이다.
* 하이버네이트 개발자들이 중심이 되어 만든 ORM 표준이 바로 JPA이다.
* EJB라는 이전의 자바 표준이 있었는데, 너무 느렸다 그러다가 하이버네이트라는 오픈 소스 프레임워크가 등장했는데
배우기 쉽고 빨라서 자바표준으로 만들기 위해서 하이버네이트를 더 표쥰적으로 가공해서 만든것이 JPA이다.

![JPA등장과정](/Java/documents/images/JPA등장과정.jpg)

* JPA가 제공하는 인터페이스를 이용하여 데이터를 처리하면 실제로는 JAP 구현체가 동작한다.
* JPA구현체로 하이버네이트, EclipaseLink, DataNucleus등 여러가지가 있는데, 
스프링 부트는 하이버네이트를 기본 JPA를 구현체로 사용한다.

### JPA 구조
![JPA구조](/Java/documents/images/JPA구조.jpg)
* JPA는 DAO를 개발하려고 하는데 하이버네이트라는 클래스를 직접 이용하다, 다른 ORM 프레임워크 바꾸려고 하면 소스를 다 고쳐야 했다.
각자 다른 API여서 그래서 중간에 JPA를 만든 것이다. 
* 어떤 ORM 프레임워크를 이용하더라도 공통의 인터페이스를 이용해서 DB연동을 구현하면 자바소스를 수정할 필요가 없다.

### JPA 동작원리
![JPA동작원리](/Java/documents/images/JPA동작원리.jpg)

* JPA는 자바 어플리케이션과 JDBC 사이에서 컬렉션에 저장된 자바 객체를 테이블의 로우와 매핑한다.
* 클래스 이름을 테이블과 매핑하고, 변수 이름과 컬럼 이름과 매핑하면 SQL을 자동으로 생성해서
값을 넣이 들어간다.

### 엔티티 매핑

* 모든 애노테이션을 다 javax.persistance 패키지로 써야한다 
이유는 직접 ORM걸로 사용하면 ORM을 바꿀수 없기 때문에 직접 그 ORM을 사용하는 것이니 자유롭게 바꿀 수 없기 때문이다.

* @Entity - 이 애노테이션이 설정된 클래스를 엔티티라고 하고, 기본적으로 클래스 이름과 동일한 테이블과 매핑된다.
  * @Entity는 클래스상에 이게 붙어 있으면 자동으로 이 클래스들을 persistance.xml에 등록한 것처럼 인지한다.
  이게 있으면 JPA가 인식하는 클래스를 지정하는 것이다.
* @Table - 엔티티 이름과 매핑될 테이블의 이름이 다른 경우, name 속성을 사용하여 매핑한다. 동일하면 생략 가능하다. (@Table(name = "NEW_BOARD2"))
* @ID - 테이블의 기본 키를 매핑한다. 변수 seq 위에 이 애노테이션을 적어 놓으면, 테이블의 PK컬럼인 SEQ컬럼과 매핑되는 것이다.
* @GeneratedValue - @ID가 선언된 필드에 기본 키 값을 자동으로 할당한다.
  다양한 옵션들이 있다, Sequence, Identity 하지만 AUTO로 설정해두면 dialect로 선언된 DB에 맞게 설정된다
  (H2, ORCLE은 시퀀스, mySQL은 Identity)
* @Temporal - Date타입의 날짜 데이터를 매핑할 때 사용한다.
* @Transient - 엔티티 클래스 내의 특정 변수를 영속 필드에서 제외할 때 사용한다.
### persistence.xml 설정
#### pesistence-unit
  * JPA를 이용하여 데이터베이스를 연동하기 위해서는 EntityManager가 필요하다.
  * EntityManager는 EntityManagerFactory로부터 획득한다.
  * 애플리케이션에서는 persistence.xml의 persistence-unit name을 참조하여 EntityManagerFactory를 생성한다.
  ![persistenceUnit](/Java/documents/images/persistenceunit.jpg)
#### DataSource 설정
* 하이버네이트 같은 JPA구현체는 데이터소스 설정을 참조하여 특정 데이터베이스와 커넥션을 연결한다.
```xml
<property name="javax.persistence.jdbc.driver"
          value="org.h2.Driver"/>
<property name="javax.persistence.jdbc.user" value="sa"/>
<property name="javax.persistence.jdbc.password" value=""/>
<property name="javax.persistence.jdbc.url"
          value="jdbc:h2:tcp://localhost/~/test"/>
<property name="hibernate.dialect"
          value="org.hibernate.dialect.H2Dialect"/>
```  
#### JPA구현체 설정

![JPA구현체](/Java/documents/images/jpa구현체.jpg)
![JPA구현체설명](/Java/documents/images/jpa구현체설명.jpg)

### EntityManagerFactory와 EntityManager

![entityManager생성과정](/Java/documents/images/EntityManager생성과정.jpg)

#### 엔티티메니저가 제공하는 메소드

![엔티티매니저메소드](/Java/documents/images/엔티티매니저메소드.jpg)

### 영속성 컨텍스트
* 영속성 컨텍스트(== 엔티티매니저,jpa 컨테이너) - 논리적인 개념으로 엔티티매니저를 생성할 때 자동으로 만들어진다.
* 영속성 컨테스트는 엔티티들을 관리하는 컨테이너라 할 수 있으며, 엔티티 매니저를 통해서 접근할 수 있다.

![엔티티상태](/Java/documents/images/엔티티상태.jpg)
* JPA 컨테이너에 등록된 엔티티는 비영속(NEW), 영속(Managed), 준영속(Detached), 삭제(Removed) 상태로 존재한다.
* new - 생성상태
  * 비영속상태라고도 한다. 아직 jpa컨테이너에 등록되지 않은 그냥 java 객체
  * 엔티티 객체 엔티티에노테이션이 붙은 객체가 생성만 된 상태
  * 아직 persist() 를 통해서 jpa 컨테이너에 들어가지 않은 상태이다.

* detached - 준영속상태
  * 영속상태에 있던 엔티티가 영속상태에서 벗어나는 것을 의미한다.
  * jpa컨테이너의 관리영역에서 벗어나는 것이다.
  * 준 영속상태로 바뀐 엔티티는 영속상태가 아니기 때문에 값을 변경해도 update와 연결되지 않는다.
  * 준영속상태 객체는 merge를 통해서 영속상태로 바꿀 수 있다.
  
* managed. - 영속상태
  1. persistance()를 사용해서  엔티티매니저를 통해서 jpa 컨테이너에 등록된 상태
     * 영속 상태의 이 객체의 변수값을 바꾸면 자동으로 업데이트가 발생한다.
     * 객체가 테이블에 없으면 insert가 발생하는 것이다.
  2.  find()를 통해서도 JPA 컨테이너에 등록할 수 있다. 영속상태로 만들 수 있다
     * 컨테이너에 등록된 데이터를 find하면 select가 실행되지 않은데, JPA에서 가지고 있지 않은, 등록되지 않은 데이터를 find 하면 select가 실행되서 DB에서 
     해당 row를 반환해서 새로운 엔티티를 생성해서 select한 값을 채워서 JPA컨테이너에 영속상태로 존재하게 된다.
     * select는 DB와 컨테이너 사이에서이뤄지는 것이다. 
     * DB에서 검색하는 것 컨테이너에 존재하는 객체로 온 요청은 해당 객체를 반환 하지만,
     컨테이너에 없는 객체는 DB에서 select한 값을 객체에 채워서 JPA 컨테이너에 등록된다.
     select는 트랜잭션과 상관이 없는 기능이다. 알지?
  
* removed - 삭제 상태
  * 엔티티를 jpa컨테이너에서 아예 제거하는 것이다.
  * DB에 delete가 처리된다. 비영속상태랑 똑같은 상태이다.(NEW와 같다.)
  * persist를 통해 다시 영속상태로 만들 수 있다.
  * 삭제해도 메모리에서 삭제되는 건아니다 객체는 메모리에 살아있지만, 재사용하는건 좋은건 아니다.
  * remove는 persiste해서 재사용할 수있지만 가비지컬렉터 되도록 내버려두는게 좋다.
  
![엔티티상태구조](/Java/documents/images/엔티티상태구조.jpg)
