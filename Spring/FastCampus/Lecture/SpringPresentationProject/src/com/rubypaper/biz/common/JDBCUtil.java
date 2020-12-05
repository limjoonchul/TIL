package com.rubypaper.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {

	// �ڿ� ����
	public static Connection getConnection() {
		try {
			// 1. ����̹� ��ü�� �޸𸮿� �ε��Ѵ�.
			Class.forName("org.h2.Driver");
			
			// 2.Connection ��ü�� ȹ���Ѵ�.
			return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","sa","");
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

			
		return null;
	}

	// select ����� �ڿ� ����
	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		// 6. Connection ���� ���� close ���� ResultSet-> Statement -> Connection
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

             if(!conn.isClosed() && conn != null) // Ŀ�ؼ��� ������ �ƴ϶�� �������� �����ض�.
                 conn.close();
         }catch (SQLException e){
             e.printStackTrace();
         }finally {
             conn = null;
         }
		
	}
	
	
	// select���� ����� �ڿ� ����
		public static void close(PreparedStatement stmt, Connection conn) {
			// 6. Connection ���� ���� close ���� Statement -> Connection

	         try{

	             if(stmt != null)
	                 stmt.close();
	         }catch (SQLException e){
	             e.printStackTrace();
	         }finally {
	             stmt = null;
	         }

	         try{

	             if(!conn.isClosed() && conn != null) // Ŀ�ؼ��� ������ �ƴ϶�� �������� �����ض�.
	                 conn.close();
	         }catch (SQLException e){
	             e.printStackTrace();
	         }finally {
	             conn = null;
	         }
			
		}

}


