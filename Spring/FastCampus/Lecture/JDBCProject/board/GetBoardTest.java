package com.rubypapper.biz.board;

import java.sql.*;

public class GetBoardTest {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        // 뭘 해도 1,2,3은 똑같다.
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
            String sql = "select * from board where seq = 1";
            rs = stmt.executeQuery(sql); // select 전용 메소드 rs으로 반환함. 결과 집합

            // 5. 검색 결과 처리(SELECT 구문에 한하여 해당된다.)
            System.out.println("[ BOARD LIST ]");
            if (rs.next()){ // 조건식으로 하나만 나오도록 해줘서 if문으로 바꾼다.
                System.out.println("게시글 상세 정보");
                System.out.println("번호 : " + rs.getInt("SEQ") );
                System.out.println("제목 : " + rs.getString("TITLE"));
                System.out.println("작성자 : " + rs.getString("WRITER"));
                System.out.println("내용 : " + rs.getString("CONTENT"));
                System.out.println("등록일 : " + rs.getDate("REGDATE"));
                System.out.println("조회수 : " + rs.getInt("CNT"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try{

//

                if(rs != null)
                    rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                rs = null;
            }

            try{

//                rs.close();
//                stmt.close();
//                conn.close();

                if(stmt != null)
                    stmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                stmt = null;
            }

            try{

//                rs.close();
//                stmt.close();
//                conn.close();

                if(!conn.isClosed() && conn != null) // 커넥션이 닫힌게 아니라면 이조건을 실행해라.
                    conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                conn = null;
            }


        }
        /**
         * 입출력 스트림을 할 때 다끝나면 입출력스트림을 꼭 닫아줘야한다.
         * 이 커넥션은 굉장히 중요하다. 안그러면 다른 어플리케이션에서 커넥션을 못 맺는다.
         * 안 끊는다고 실행이 안되는건 아닌데 웹에서 여러명이 db연동을 하기 때문에 중요함.
         * 커넥션은 커넥션 statement, resultset 순으로해서 close()를 할 때도 역순으로 닫아야 한다.
         * 아래는 rs.close()에서 에러가 뜨면 catch 로 넘어가서 stmt, conn close()가 실행되지 않아서 에러가 뜬다. 이렇게 하면 안됨.
         * //            try{
         * ////                rs.close();
         * ////                stmt.close();
         * ////                conn.close();
         * //            } catch (SQLException e){
         * //                e.printStackTrace();
         * //            }
         *  등록 수정 삭제는 resultset을 사용하지 않기 때문에 stmt, conn만 닫아주면 된다.
         */
    }
}
