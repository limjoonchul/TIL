# JDBC(Java Database Connectivity)
## JDBC란?
* JDBC는 DBMS와의 연동을 위해 제공되는 자바 표준 API(java.sql) 이다.

![JDBC](/Java/documents/images/JDBC.jpg)


* JDBC 드라이버
  * Java.sql 패키지의 interface를 구현한 클래스를 드라이버라고 한다.
  
  ![JDBCDriver](/Java/documents/images/jdbcdriver.jpg)
  
* 데이터베이스가 달라졌을 때 SQL은 DB에 맞게 수정해야 한다. 커넥션도 DB에 따라 다를 수 밖에 없음.
나머지 자바코드는 DB와 상관 없이 재사용 가능하다.

* DB연동할 때 java.sql 패키지 클래스나 인터페이스를 이용해야한다. 인터페이스가 훨씬 많다.
* 인터페이스가 많다는건 jdbc도 내부적으로 다형성을 이용하고 있다라고 알면 된다.
* 특정 DB에 비 종속적인 자바코드를 유지할 수 있다.

* JDBC API - java.sql 패키지, 인터페이스가 많음(리모콘) 이 인터페이스를 구현한게 JDBC Driver이다.
  * 이 드라이버는 DB벤더 개발자들이 드라이버를 개발한다. 최적화된 자바코드를 이용해서 개발한다.
  * 그래서 드라이버는 db에 따라 달라질 수 있고 성능이 달라질 수 있다.
  * 우리가 드라이버를 개발하는게 아니다. 그런데 여러 DB가 동일한 인터페이스를 상속했기 때문에 똑같은 메소드를 가지고 있다.
  * 클라이언트가 (tv유저) 리모콘이라는 인터페이스를 통해서 오라클, mysql jdbc 드라이버인 lgtv, samsungtv가 동작한다.
* jar파일을 등록을해주면 인터페이스에 대한 구현클래스들을 확보하는 것이다(드라이버)
### JDBC 동작 원리

![JDBC동작원리](/Java/documents/images/JDBC동작원리.jpg)

### JDBC 구조

![JDBC구조](/Java/documents/images/JDBC구조.png)

### JDBC 프로그램 절차
1. Driver 로딩
   * `DriverManager.registerDriver(new org.h2.Driver())`와 `Class.forName("org.h2.Driver")` 차이
      * `DriverManager.registerDriver(new org.h2.Driver())`
         * 이것을 이용하면 이 클래스가 패키지에 없으면 아예 컴파일부터 안된다.
         * 실행하기도 전에 클래스패스상에 존재하지 않는다고 인식한다. 그래서 h2 라이브러리를 등록을해줘야 한다.
      * `Class.forName("org.h2.Driver")`
         * 문자열로 등록하는 방법 결과는 같음. 실제 프로젝트에선 간결해서 이걸 더 많이 쓴다.
         * 문자열로 클래스경로를 찾아주면 똑같이 메모리 주소를 생성해서 올린다.
         * 클래스이름에 오타가 있어도 컴파일이 되 버린다. 그래서 실행을 할 때 문제가 있다는걸 실행할 때 알려준다.
         * 라이브러리가 없는데도 컴파일이 되버린다. 하지만 실행을 하면 h2관련 드라이버가 없으니깐 에러가 난다.
   * jar파일 안에서 org.h2 driver.class가 중요하다. new org.h2.Driver()를 등록한다.
     * 이걸 등록해서 연결할 때 h2드라이버로 연결을 해주는 것이다.
     
2. Connection 연결
   * 커넥션 획득할 때는 url, 아이디, 비밀번호가 일치해야한다.
      * 다른사람의 DB에 연결하고 싶을 때, 다른 사람의 IP주소를 세팅하면 된다.
      * IP주소는 유니크하기 때문에 로컬호스트는 유니크한것이다. 위에 url은 내 컴퓨터에 있는 h2데이터베이스와 연결한다는 것이다.
      * JDBC URL은 드라이버마다 다르다. JDBC URL은 DB 설치한 사람(DB관리자)한테 물어보면 된다.
      
3. Statement 생성

4. SQL 명령어 전송
   * SQL을 담아서 DBMS로 보내는 작업이다.
   * INSERT, DELETE, UPDATE는 전송하는 메소드가 `executeUpdate()`로 같다.
   * SELECT는 `executeQuery()` 이다.
   
5. 검색 결과 처리(INSERT, UPDATE, DELETE 작업에서는 생략)

6. Connection 연결 해제
* 어디에 적용해도 1,2,3 번은 똑같다.

#### INSERT, DELETE, UPDATE 구현 예
```java
package com.rubypapper.biz.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertBoardTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            // 1. 드라이버 객체를 메모리에 로딩한다.
            // DriverManager.registerDriver(new org.h2.Driver()); // 애를이용하면 이 클래스가 패키지에 없으면 아예 컴파일부터 안된다.

            // 드라이버 매니저를 통해 특정 드라이버를 메모리에 올려야한다.
            // 우린 h2 드라이버를 메모리 로딩해야한다 그래야 이 드라이버로부터 연결을 얻을 수 있고
            // 연결로부터 스테이트먼트 클래스를 얻을 수 있다.
            // jar파일안에서 org.h2 driver.class가중요하다. new org.h2.Driver()를 등록한다

            // 위아래는 기능적으로는 결과는 똑같지만, 특징들을 작성한 차이가 있다.
            Class.forName("org.h2.Driver"); // 문자열로 등록하는 방법 결과는 같음. 실제프로젝트에선 이걸 더 많이 씀 간결해서
           
            // 2. 커넥션을 획득한다.
            String url = "jdbc:h2:tcp://localhost/~/test";
            conn = DriverManager.getConnection(url, "sa", "");// 연결 객체를 반환받는다.
           
            // 3. SQL전달 객체(Statement)를 생성한다.
            stmt = conn.createStatement();

            // 4. SQL을 전송한다.
            String sql = "insert into board(seq, title, writer, content) values(3, '테스트 제목', '테스터', '테스트 내용...')";
            // String sql = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0) + 1 from board), '테스트 제목', '테스터', '테스트 내용...')";
            // null 에대한 비교연산은 false이다 비교를 못함
            // select nvl(max(seq), 0) + 1 from board; nvl은 nonvalue에 약자 최대값이 null이 나오면 0 으로 바꾸라는 의미

            int cnt = stmt.executeUpdate(sql); // dbms에 전송한다
            System.out.println(cnt + "건의 데이터 처리 성공!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           // 등록 수정 삭제는 resultset을 사용하지 않기 때문에 stmt, conn만 닫아주면 된다.
            try{

              if(stmt != null)
                 stmt.close();

            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                stmt = null;
            }

            try{

              if(!conn.isClosed() && conn != null) // 커넥션이 닫힌게 아니라면 이조건을 실행해라.
                 conn.close();

            } catch (SQLException e){
                e.printStackTrace();
            } finally {
                conn = null;
            }  
        }
    }
}
```
#### SELECT 구현 예
```java
package com.rubypapper.biz.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetBoardListTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. 드라이버 객체를 메모리에 로딩한다.
            // DriverManager.registerDriver(new org.h2.Driver()); // 애를 이용하면 이 클래스가 패키지에 없으면 아예 컴파일부터 안된다.

            Class.forName("org.h2.Driver"); // 문자열로 등록하는 방법 결과는 같음. 실제프로젝트에선 이걸 더 많이 씀 간결해서

            // 2. 커넥션을 획득한다.
            String url = "jdbc:h2:tcp://localhost/~/test";
            conn = DriverManager.getConnection(url, "sa", "");// 연결 객체를 반환받는다.

            // 3. SQL전달 객체(Statement)를 생성한다.
            stmt = conn.createStatement();

            // 4. SQL을 전송한다.
            String sql = "select * from board order by seq desc";
            rs = stmt.executeQuery(sql); // select 전용 메소드 rs으로 반환함. 결과 집합

            // 5. 검색 결과 처리(SELECT 구문에 한하여 해당된다.)
            System.out.println("[ BOARD LIST ]");
            while (rs.next()){
                System.out.println(rs.getInt("SEQ") + " : ");
                System.out.println(rs.getString("TITLE") + " : ");
                System.out.println(rs.getString("WRITER") + " : ");
                System.out.println(rs.getString("CONTENT") + " : ");
                System.out.println(rs.getDate("REGDATE") + " : ");
                System.out.println(rs.getInt("CNT"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
              if(rs != null)
                  rs.close();

            }catch (SQLException e){
                e.printStackTrace();

            }finally {
                rs = null;
            }

           try{
              if(stmt != null)
                 stmt.close();

           }catch (SQLException e){
               e.printStackTrace();

           }finally {
               stmt = null;
           }
           try{
             if(!conn.isClosed() && conn != null) // 커넥션이 닫힌게 아니라면 이조건을 실행해라.
                conn.close();

           }catch (SQLException e){
               e.printStackTrace();

           }finally {
               conn = null;
           }
        }
    }
}
```
* 어플리케이션이 있고 DB가 있는데 문자열로된 SQL을 DBMS로 보내주는건데 어플리케이션을 서울이라고 보고 DB를 부산이라고 했을 때
* 이 SQL이 사람이라고 치면 사람이 부산까지 걸어갈 수 없으니 고속도로를 뚫어야한다 이 도로가 커넥션 서울과 부산이 연결되어 있다는 것
* 커넥션이 될려면 URL, ID, PW가 있어야 한다.  대전이랑 연결할려면 연결이 달라져야한다.
* 고속도로가 뚫려도 자동차가 필요하다 자동차가 Statement이다.
* 자동차에 사람(sql)을 태워서 부산으로 보내는게  stmt.executeUpdate(sql)이 작업이다. 이것이 jdbc이다.
* 고속도로 뚫는다 자동차를 만든다 자동차에 사람을 태워 부산으로 보낸다.
       

#### Result Set

![Result Set](/Java/documents/images/ResultSet.jpg)
```text
* Before First(as B.F), After Last(as A.L)는 데이터가 없는 칸이다. 
* B.F에 커서가 존재한다. rs.next를 호출하면 커서를 밑으로 한칸 내려준다.
* 리턴타입이 불린이다. 읽을 데이터가 있으면 true가 되고 출력하고 아래로 내려간다.
* 그리고 또 데이터를 출력한다. 그리고 커서가 아래로 이동한다 그럼 A.L로 이동해서
* 읽을 값이 없어서 false 가 되서 반복문을 벗어난다.
*
* 반복문을 없애고 각 커서를 읽으려고 하면 데이터가 없는 영역이라고 에러가 뜬다.
* rs.next가 실행되어야 커서가 아래로 내려가는 것이다. 그래야 하나의 로우정보를 읽을 수 있음.
* 더이상 읽을 데이터가 없을 때까지 while문으로 rs.next를 사용하여 내려주는 것이다.
*
* 데이터 타입에 따라 사용하는 메소드가 달라져야한다.(getXXX()) 컬럼은 대소문자를 구분하지 않는다.
* 중요한건 컬럼이름을 모른다면, getInt(1) 처럼 컬럼위 위치를 넣을 수 있다 그런데 이러면 가독성이 떨어짐...
* 가급적 컬럼이름을 써라. 대소문자를 구분하진 않지만 대문자를 쓰는게 가독성 측면에서 좋다.
* 컬럼의 순서는 안맞춰도 되지만 가급적 맞추는게 좋다.
```

#### 리소스 닫기(Coonection close)
* 이렇게 하면 rs.close()에서 에러가 뜨면 catch 로 넘어가서 stmt, conn close()가 실행 되지 않아서 에러가 뜬다.
```java
 try{
       rs.close();
       stmt.close();
       conn.close();

 } catch (SQLException e){
     e.printStackTrace();
 }
```
* 이렇게 해야 한다.
```java
try{
    if(rs != null)
        rs.close();

}catch (SQLException e){
    e.printStackTrace();
}finally {
    rs = null;
}

try{
    if(stmt != null)
        stmt.close();

} catch (SQLException e){
    e.printStackTrace();

} finally {
    stmt = null;
}

try{
    if(!conn.isClosed() && conn != null) // 커넥션이 닫힌게 아니라면 이조건을 실행해라.
        conn.close();

} catch (SQLException e) {
    e.printStackTrace();
} finally {
    conn = null;
}
```
* 입출력 스트림을 할 때 다끝나면 입출력스트림을 꼭 닫아줘야한다.
* 이 커넥션은 굉장히 중요하다. 안그러면 다른 어플리케이션에서 커넥션을 못 맺는다.
* 안 끊는다고 실행이 안되는건 아닌데 웹에서 여러명이 db연동을 하기 때문에 중요함.
* 커넥션은 커넥션 statement, resultset 순으로해서 close()를 할 때도 역순으로 닫아야 한다.
* 등록 수정 삭제는 resultset을 사용하지 않기 때문에 stmt, conn만 닫아주면 된다.

