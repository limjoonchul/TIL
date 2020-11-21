package com.rubypaper.biz.common;

import java.sql.*;

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
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs = null;
        }

        try {

            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt = null;
        }

        try {

            if (!conn.isClosed() && conn != null) // 커넥션이 닫힌게 아니라면 이조건을 실행해라.
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    public static void close(PreparedStatement stmt, Connection conn) {
        try {

            if (stmt != null)
                stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stmt = null;
        }

        try {

            if (!conn.isClosed() && conn != null) // 커넥션이 닫힌게 아니라면 이조건을 실행해라.
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
}
