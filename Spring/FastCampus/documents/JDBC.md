# JDBC(Java Database Connectivity)
## JDBC란?
* JDBC는 DBMS와의 연동을 위해 제공되는 자바 표준 API(java.sql) 이다.

![JDBC](/Java/documents/images/JDBC.jpg)


* JDBC 드라이버
  * Java.sql 패키지의 interface를 구현한 클래스를 드라이버라고 한다.
  
  ![JDBCDriver](/Java/documents/images/jdbcdriver.jpg)
  
* 데이터베이스가 달라졌을 때 SQL은 DB에 맞게 수정해야 한다. 커넥션도 DB에 따라 다를 수 밖에 없음.
나머지 자바코드는 DB와 상관 없이 재사용 가능하다.

* DB연동할 때 java.sql 패키지 클래스나 인터페이스를 이용해야한다. (패키지 안에 인터페이스가 훨씬 많다.)
* 인터페이스가 많다는건 jdbc도 내부적으로 다형성을 이용하고 있다라고 알면 된다.
* 특정 DB에 비 종속적인 자바코드를 유지할 수 있다.

* JDBC API - java.sql 패키지, 인터페이스가 많음(리모콘) 이 인터페이스를 구현한게 JDBC Driver이다.
  * 이 드라이버는 DB벤더 개발자들이 드라이버를 개발한다. 최적화된 자바코드를 이용해서 개발한다.
  * 그래서 드라이버는 DB에 따라 달라질 수 있고 성능이 달라질 수 있다.
  * 우리가 드라이버를 개발하는게 아니다. 그런데 여러 DB가 동일한 인터페이스를 상속했기 때문에 똑같은 메소드를 가지고 있다.
  * 클라이언트가 (tv유저) 리모콘이라는 인터페이스를 통해서 오라클, mysql jdbc 드라이버인 lgtv, samsungtv가 동작한다.
* jar파일을 등록을 해주면 인터페이스에 대한 구현클래스들을 확보하는 것이다(드라이버)
### JDBC 동작 원리

![JDBC동작원리](/Java/documents/images/JDBC동작원리.jpg)

### JDBC 구조

![JDBC구조](/Java/documents/images/JDBC구조.png)

### JDBC 프로그램 절차
1. Driver 로딩
   * `DriverManager.registerDriver(new org.h2.Driver())`와 `Class.forName("org.h2.Driver")` 차이
      * `DriverManager.registerDriver(new org.h2.Driver())`
         * 이것을 이용하면 이 클래스가 패키지에 없으면 아예 컴파일부터 안된다.
         * 실행하기도 전에 ClassPath 상에 존재하지 않는다고 인식한다. 그래서 h2 라이브러리를 등록을 해줘야 한다.
      * `Class.forName("org.h2.Driver")`
         * 문자열로 등록하는 방법 결과는 같음. 실제 프로젝트에선 간결해서 이걸 더 많이 쓴다.
         * 문자열로 클래스 경로를 찾아주면 똑같이 메모리 주소를 생성해서 올린다.
         * 클래스이름에 오타가 있어도 컴파일이 되어 버린다. 그래서 실행을 할 때 문제가 있다는 걸 실행할 때 알려준다.
         * 라이브러리가 없는데도 컴파일이 되버린다. 하지만 실행을 하면 h2관련 드라이버가 없으니깐 에러가 난다.
   * jar파일 안에서 org.h2 driver.class가 중요하다. new org.h2.Driver()를 등록한다.
     * 이걸 등록해서 연결할 때 h2드라이버로 연결을 해주는 것이다.
     
2. Connection 연결
   * 커넥션 획득할 때는 url, 아이디, 비밀번호가 일치해야한다.
      * 다른사람의 DB에 연결하고 싶을 때, 다른 사람의 IP주소를 세팅하면 된다.
      * IP주소는 유니크하기 때문에 로컬호스트는 유니크한것이다.  url("jdbc:h2:tcp://localhost/~/test";)은 내 컴퓨터에 있는 h2데이터베이스와 연결한다는 것이다.
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
* 어플리케이션이 있고 DB가 있는데 문자열로 된 SQL을 DBMS로 보내주는건데 어플리케이션을 서울이라고 보고 DB를 부산이라고 했을 때
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
* 리턴타입이 boolean이다. 읽을 데이터가 있으면 true가 되고 출력하고 아래로 내려간다.
* 그리고 또 데이터를 출력한다. 그리고 커서가 아래로 이동한다 그럼 A.L로 이동해서
* 읽을 값이 없어서 false 가 되서 반복문을 벗어난다.
*
* 반복문을 없애고 각 커서를 읽으려고 하면 데이터가 없는 영역이라고 에러가 뜬다.
* rs.next가 실행되어야 커서가 아래로 내려가는 것이다. 그래야 하나의 row정보를 읽을 수 있음.
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
* 입출력 스트림을 할 때 다 끝나면 입출력스트림을 꼭 닫아줘야한다.
* 이 커넥션은 굉장히 중요하다. 안 그러면 다른 어플리케이션에서 커넥션을 못 맺는다.
* 안 끊는다고 실행이 안되는건 아닌데 웹에서 여러명이 DB연동을 하기 때문에 중요함.
* 커넥션은 커넥션 statement, resultset 순으로해서 close()를 할 때도 역순으로 닫아야 한다.
* 등록 수정 삭제는 resultset을 사용하지 않기 때문에 stmt, conn만 닫아주면 된다.

### PreparedStatement
* Statement보다 SQL처리 속도가 5배 이상 빨라서 이걸 사용 하면 된다.
* DB에서 SQL이 들어올 때 가장 먼저 확인하는 것이 문법체크와 오브젝트를 확인하는 것이다.
SQL이 들어올 때마다 이러한 작업들을 계속해서 수행해야 하나는데 `PreparedStatement`를 사용 하게 되면
한 번 SQL을 사용해서 보내 놓으면 그 다음부터는 문법체크와 오브젝트 확인에 대한 작업들을 실행하지 않는다.
그래서 일단 ?로 값을 채워 넣고 동적으로 `setXXX()`를 사용하여 값을 넣어서 사용할 수 있다.
문법체크와 오브젝트를 확인하는 일에 대해서 한번 이후에 계속해서 실행시키지 않기 때문에 속도가 더 빠른 것이다. 

* Statement와 차이점
  * `String sql = "select * from users where id = ? and password = ?";` - 파라미터를 ?로 작성한다.
  * `stmt = conn.prepareStatement(sql);` - 여기서 ()안에 sql을 넣어줘야 한다.
  * `stmt.setString(1, "admin")`, `stmt.setString(2, "admin")` - 파라미터 ? 에 순서대로 1,2 번호를 넣고 해당하는 값을 넣는다.
  * `rs = stmt.executeQuery();` - sql을 빼고 작성하면 된다.
    			
```java
package com.rubypapper.biz.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.util.JdbcUtils;

import com.rubypapper.biz.common.JDBCUtil;

public class GetUserTest {
	public static void main(String[] args) {
		// JDBC API 선언
		Connection conn = null; // 고속도로
//		Statement stmt = null; // 자동차 
		PreparedStatement stmt = null; // 자동차(페라리) statement보다 sql처리속도가 5배 이상 빠르다. 무조건 써라.
		ResultSet rs = null; // 검색 결과 저장
		
		
		try {
			// 2.Connection 객체를 획득한다.
			conn = JDBCUtil.getConnection(); // static 메소드니깐 바로 접근이 가능하다.
			
			// 3. Statement 객체를 획득한다.
			String sql = "select * from users where id = ? and password = ?";
			stmt = conn.prepareStatement(sql);// 프리페얼드는 여기에 sql이 들어감 그리고 파라미터가 ?로 바뀐다. exe여기에 sql이 빠짐
			
			// ? 파라미터에 값  설정
			stmt.setString(1, "admin");
			stmt.setString(2, "admin");  // 결과가 달라지는게 아니라 성능이 더 좋아진다.
			
			// 4. SQL 구문을 DB에 전송한다.
			
			rs = stmt.executeQuery();
			
			// 5. 검색 결과 처리
			if(rs.next()) {
				System.out.println("아이디 : " + rs.getString("ID"));
				System.out.println("비번 : " + rs.getString("PASSWORD"));
				System.out.println("이름 : " + rs.getString("NAME"));
				System.out.println("권한 : " + rs.getString("ROLE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}
}
```

## Refactoring
* 처음엔 정말 그대로 각 클래스에서 기능을 처리하도록 만들었었는데, 이렇게 작성했을 때 유지보수성이 안 좋았다.
그래서 다음으로 JDBCUtil로 공통적으로 사용하는 코드들을 새로운 클래스에 만들어서 호출하는 방식으로 해서 유지보수성을 높였는데,
거기서 더 나아가서 데이터베이스에 접근할 수 있는 DAO 클래스와 전달되는 객체 VO를 만들어서 한번 더 리팩토링을 했다.
* 순서대로 Refactoring 해가는 과정 코드들을 적었다.

### 1. JDBCUtil 클래스를 만들어서 리팩토링 
* InsertBoardTest 클래스
```java
package com.rubypapper.biz.board;

public class InertBoardTest {

	public static void main(String[] args) {
		Connection conn = null; PreparedStatement stmt = null;
        		  
        try {
         // JDBUCUtil에 1,2 번 과정이 들어가있어서 호출해서 사용.
         conn = JDBCUtil.getConnection();
        
        // 3. SQL 전달 객체를 생성한다.
        // String sql = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0) + 1 from board), ?, ?, ?)";
        stmt = conn.prepareStatement(sql);
        
        // 파라미터 세팅 stmt.setString(1, "JDBC 제목 "); stmt.setString(2, "테스터");
        stmt.setString(3, "JDBC 내용...");
        
        // 4. SQL 전송        
        int cnt = stmt.executeUpdate(); 
        System.out.println(cnt + "건의 데이터 처리 성공!");
        
        } catch (Exception e) { 
          e.printStackTrace();
        
        } finally { 
          JDBCUtil.close(stmt, conn); 
        }		
	}
}
```
* JDBCUtil 클래스
```java
package com.rubypapper.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

	// 자원 연결
	public static Connection getConnection() {
		try {
			// 1. 드라이버 객체를 메모리에 로딩한다.
			Class.forName("org.h2.Driver");
			
			// 2.Connection 객체를 획득한다.
			return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

			
		return null;
	}

	// select 기능의 자원 해제
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		// 6. Connection 연결 해제 close 순서 ResultSet-> Statement -> Connection
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
	
	
	// select외의 기능의 자원 해제
		public static void close(PreparedStatement stmt, Connection conn) {
			// 6. Connection 연결 해제 close 순서 Statement -> Connection

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
```

### DAO, VO를 이용한 리팩토링
* 만약 인서트쿼리를 처리하고 하고 바로 조회를 하거나, 업데이트를 하고 바로 조회를 할려고 구현을 할 때
그냥 코드를 직접 넣으면 코드가 길어지고 지저분해 보이게 되고, JDBCUtil을 사용해도 번거로워지는 부분이 생긴다. 이 때 DAO를 만들면 된다.
* boardDao만 작성 했을 때 문제점 
  * 문제1. 처음 볼 때 들어가는 값이 제목인지 컨텐트인지 모른다. 이 메소드를 들어가봐야 의미를 파악할 수 있다.
  * 문제2. 파라미터의 타입과 순서만 맞으면 데이터가 잘 못 입력되어도 들어가게 된다. 
  * 에러가 뜨고 컴파일 오류가 뜨면 다행인데 안뜨고 잘 못 들어가면 문제가 된다.
  * 그래서 VO도 함께 만들어서 데이터를 처리하면 된다.
```java
package com.rubypapper.biz.board;

public class InertBoardTest {

	public static void main(String[] args) {
		
		// 1. 글 등록 기능 처리
		BoardDAO boardDAO = new BoardDAO();
		
		BoardVO vo = new BoardVO();
		vo.setTitle("VO 테스트");
		vo.setTitle("테스터");
		vo.setTitle("VO 내용.....");
		
		boardDAO.insertBoard(vo); 

		// 2. 글 목록 검색 기능 처리
		boardDAO.getBoardList();
	}
}
```
* DAO
```java
package com.rubypapper.biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rubypapper.biz.common.JDBCUtil;

// DAO(Data Access Object) 클래스
public class BoardDAO {
	// JDBC 관련 변수 선언
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	// BOARD 테이블 관련 SQL 명령어
	private static final String BOARD_INSERT = "INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(MAX(SEQ),0)  + 1 FROM BOARD), ?, ?, ?)";
	private static final String BOARD_UPDATE = "UPDATE BOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
	private static final String BOARD_DELETE = "DELETE BOARD WHERE SEQ=?";
	private static final String BOARD_GET = "SELECT * FROM BOARD WHERE SEQ=?";
	private static final String BOARD_LIST = "SELECT * FROM BOARD ORDER BY SEQ DESC";
	
	// BOARD 테이블 관련 CRUD 기능의 메소드
	// 글 등록
	public void insertBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT); // sql이 완성되지 않았다 ?로 넣었기 때문에.
			
			stmt.setString(1, vo.getTitle()); // sql 세팅 값을 넣어줌.
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			
//			stmt.setString(1, title); // sql 세팅 값을 넣어줌.
//			stmt.setString(2, writer);
//			stmt.setString(3, content);
			
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			
			stmt.setString(1, vo.getTitle()); // sql 세팅 값을 넣어줌.
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			
//			stmt.setString(1, title); // sql 세팅 값을 넣어줌.
//			stmt.setString(2, content);
//			stmt.setInt(3, seq);
			
			
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 글 삭제  나중에 유지보수할 때 int seq로 작성했을때는 비밀번호도 필요하고 그런 필요한 상황이 생겼을 때 하나 하나 넣어줘야 한다.
	// 그래서 vo를 통째로 넣는게 일관성 면에서도 좋다. 유지보수할 때 수정할 필요가 없어진다.
	// 지금은 메모리에 대한 걱정은 없어져서 vo를 통째로 넣어도 성능적으로 문제가 되지 않는다.
	public void deleteBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			
			stmt.setInt(1, vo.getSeq());
			
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {
		BoardVO board = null;
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			
			stmt.setInt(1, vo.getSeq());
			
			rs= stmt.executeQuery();
			
			if (rs.next()) {
				// 검색 결과 한건을 BoardVO 객체를 매핑한다.
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return board;
	}
	
	// 글 목록 검색
	public List<BoardVO> getBoardList() {
		// 비어있는 리스트 컬렉션을 생성한다.
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				// 검색 결과 한 row당 하나의 BoardVO 객체를 매핑한다.				
				BoardVO  board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				// 검색 결과가 매핑된 BoardVO 객체를 리스트 컬렉션에 등록한다.
				boardList.add(board);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		
		// 검색 결과가 저장된 리스트 컬렉션을 클라이언트로 리턴한다.
		return boardList;
	}
}
```
* VO
  * VO, DTO 등의 클래스를 만들 때 새로온 변수를 추가하거나, getter/setter 메소드를 계속해서 생성해줘야 하고 변경되면 또 하나하나 변경해줘야 한다.
  이러한 번거로움을 줄이기 위해서 `Lombok` 이라는 라이브러리를 사용하면 getter/setter같은 메소드를 자동으로 생성해준다.
  * `@Data` 를 사용하면 필요한 모든 기능들을 포함해서 적용해준다.
```java
package com.rubypapper.biz.board;

import java.sql.Date;

import lombok.Data;

// VO(Value Object) 클래스
/*@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode*/

@Data
public class BoardVO {
	// 테이블의 컬럼 이름(데이터 타입까지)과 동일한 멤버변수를 private로 선언한다.
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
}
```

### 중요!!!
* 글 목록 조회나, 글 상세 조회 기능 같은 SELECT 쿼리를 사용해서 보여주는 기능들은 DAO에서 바로
출력하도록 하면 안되고, 객체를 넘겨줘서 이 메소드를 사용하는 클라이언트(메소드를 호출하는 메소드,객체)에서
결정해서 사용하도록 설계를 해야 한다!!  
* select 안에서는 검색 결과를 처리하면 안된다. 메소드를 호출한 쪽에 검색 결과를 리턴 해줘야한다
그래서 클라이언트가 글 목록을 뿌릴지 데이터가 몇 개인지를 뿌릴지 결정하게 해야한다.

#### DAO의 글 상세조회, 글 목록 조회 메소드
```java
// 글 상세 조회
public BoardVO getBoard(BoardVO vo) {
	BoardVO board = null;
	try {
		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(BOARD_GET);
		
		stmt.setInt(1, vo.getSeq());
		
		rs= stmt.executeQuery();
		
		if (rs.next()) {
			
			// 검색 결과 한건을 BoardVO 객체를 매핑한다.
			board = new BoardVO();
			board.setSeq(rs.getInt("SEQ"));
			board.setTitle(rs.getString("TITLE"));
			board.setWriter(rs.getString("WRITER"));
			board.setContent(rs.getString("CONTENT"));
			board.setRegDate(rs.getDate("REGDATE"));
			board.setCnt(rs.getInt("CNT"));
		}
	}catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(rs, stmt, conn);
	}
	return board;
}

// 글 목록 검색
public List<BoardVO> getBoardList() {
	// 비어있는 리스트 컬렉션을 생성한다.
	List<BoardVO> boardList = new ArrayList<BoardVO>();
	try {
		conn = JDBCUtil.getConnection();
		stmt = conn.prepareStatement(BOARD_LIST);
		
		rs = stmt.executeQuery();
		
		while (rs.next()) {
			// 검색 결과 한 row당 하나의 BoardVO 객체를 매핑한다.
			
			BoardVO  board = new BoardVO();
			board.setSeq(rs.getInt("SEQ"));
			board.setTitle(rs.getString("TITLE"));
			board.setWriter(rs.getString("WRITER"));
			board.setContent(rs.getString("CONTENT"));
			board.setRegDate(rs.getDate("REGDATE"));
			board.setCnt(rs.getInt("CNT"));
			
			// 검색 결과가 매핑된 BoardVO 객체를 리스트 컬렉션에 등록한다.
			boardList.add(board);
		}
	}catch (Exception e) {
		e.printStackTrace();
	} finally {
		JDBCUtil.close(rs, stmt, conn);
	}
	
	// 검색 결과가 저장된 리스트 컬렉션을 클라이언트로 리턴한다.
	return boardList;
}
```
#### GetBoardTest(클라이언트)
```java
package com.rubypapper.biz.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GetBoardListTest {

	public static void main(String[] args) {

		// 1. 글 목록 조회 기능 처리
		BoardDAO boardDAO = new BoardDAO();
		
		// getBoardList() 메소드가 리턴한 글 목록을 원하는 형태로 사용한다.
		List<BoardVO> boardList =boardDAO.getBoardList();
		
		// select 기능의 메소드는 절대 return타입이 void이면 안된다 결과를 클라이언트에게 보내줘야 한다.
		// 어떻게 쓸지는 클라이언트가 결정해야지 dao가 결정하면 안된다!!! 중요!!!!!
		
        // 1. CASE 글 목록을 출력
		System.out.println("[ BOARD LIST ]");
		for (BoardVO board : boardList) {
			System.out.println("---->" + board.toString());
		}
		
		// 2. CASE
		System.out.println("검색된 게시글 수 : " + boardList.size());

	}
}
```

* 클라이언트 - 한 객체의 메소드(a)안에서 다른 객체가 가지고 있는 메소드(b)를 호출할 때 
b 입장에서 애를 사용하는 클라이언트는 a가 된다. 
  * 객체에 한에서 클라이언트는 나를 사용하는 누군가를 클라이언트라한다.        