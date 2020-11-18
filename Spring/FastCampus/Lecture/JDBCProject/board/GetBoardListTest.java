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
//            DriverManager.registerDriver(new org.h2.Driver()); // 애를이용하면 이 클래스가 패키지에 없으면 아예 컴파일부터 안된다.

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
            /**
             * b.f al는 데이터가 없는 칸이다. b.f 커서가 존재한다. rs.next를 호출하면 커서를 밑으로 한칸 내려준다.
             * 리턴타입이 불린이다. 읽을 데이터가 있으면 true가 되고 출력하고 아래로 내려간다.
             * 그리고 또 데이터를 출력한다. 그리고 커서가 아래로 이동한다 그럼 a.l로 이동해서
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
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
