package com.rubypapper.biz.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteBoardTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        // 외우지 않아도 된다 사용하는 방법은 똑같다 정형화되어있음. 절차를 알고 있는게 중요 어떤 드라이버가 연결되는지가 중요하다!
        try {
            // 1. 드라이버 객체를 메모리에 로딩한다.
            DriverManager.registerDriver(new org.h2.Driver()); // 애를이용하면 이 클래스가 패키지에 없으면 아예 컴파일부터 안된다.
            // 실행하기도 전에 클래스패스상에 존재하지 않는다고 인식한다. 그래서 h2 라이브러리를 등록을해줘야 한다.

            // 위아래는 기능적으로는 결과는 똑같지만, 특징들을 작성한 차이가 있다.

//            Class.forName("org.h2.Driver"); // 문자열로 등록하는 방법 결과는 같음. 실제프로젝트에선 이걸 더 많이 씀 간결해서
            // 문자열로 클래스경로를 찾아주면 똑같이 메모리 주소를 생성해서 올린다.
            // 클래스이름에 오타가있어도 컴파일이 되버린다. 그래서 실행을 할 때 문제가 있다는걸 실행할 때 알려준다.
            // 라이브러리가 없는데도 컴파일이 되버린다. 하지만 실행을 하면 에러가남 h2관련 드라이버가 없으니깐
            /**
             *jar파일안에서 org.h2 driver.class가 중요하다. new org.h2.Driver()를 등록한다.
             * 이걸 등록해서 연결할때 h2드라이버로 연결을 해주는 것이다.
             */

            // 2. 커넥션을 획득한다.
            String url = "jdbc:h2:tcp://localhost/~/test";
            conn = DriverManager.getConnection(url, "sa", "");// 연결 객체를 반환받는다.
            // h2가  연결객체가 반환이 되는건 Class.forName("org.h2.Driver()"); 이걸 먼저 로딩해서 그렇다.
            /**
             * 커넥션 획득할 대는 url, 아이디, 비밀번호가 일치해야한다.
             * 다른사람의 db에 연결하고 싶을 때  다른사람의 ip주소를 세팅하면 된다.
             * ip주소는 유니크하기 때문에 로컬호스트는 유니크한것이다. 위에 url은 내 컴퓨터에있는 h2데이터베이스와 연결한다는 것이다.
             * jdbc url은 드라이버마다 다르다. jdbc url은 db 설치한 사람한테 물어보면 된다. db관리자한테 물어보면됨.
             */

            // 3. SQL전달 객체(Statement)를 생성한다.
            stmt = conn.createStatement();

            // 4. SQL을 전송한다.
            String sql = "delete board where seq = 3";
            int cnt = stmt.executeUpdate(sql); // dbms에 전송한다 몇개가 실행이 됬는지 정수로 반환됨. executeUpdate이 하나로 insert delet update를 모두 전송시킨다.
            System.out.println(cnt + "건의 데이터 처리 성공!");

            /**
             * 어플리케이션이 있고 DB가 있는데 문자열로된 SQL을 DBMS로 보내주는건데 어플리케이션을 서울이라고 보고 DB를 부산이라고 했을 때
             * 이 SQL이 사람이라고 치면 사람이 부산까지 걸어갈 수 없으니 고속도로를 뚫어야한다 이 도로가 커넥션 서울과 부산이 연결되어 있다는 것
             * 커넥션이 될려면 URL, ID, PW가 있어야 한다.  대전이랑 연결할려면 연결이 달라져야한다.
             *
             * 고속도로가 뚤려도 자동차가 필요하다 자동차가 Statement이다.
             * 자동차에 사람(sql)을 태워서 부산으로 보내는게  stmt.executeUpdate(sql)이 작업이다.
             * 이것이 jdbc이다.
             * 고속도로 뚫는다 자동차를 만든다 자동차에 사람을 태워 부산으로 보낸다.
             */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
 // 등록 수정 삭제는 resultset을 사용하지 않기 때문에 stmt, conn만 닫아주면 된다.
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
