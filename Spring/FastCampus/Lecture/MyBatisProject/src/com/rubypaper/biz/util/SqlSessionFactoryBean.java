package com.rubypaper.biz.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {
	private static SqlSessionFactory sessionFactory;
	
	// SqlSessionFactory 공장을 이렇게 만듬
	static {
		try {
			if (sessionFactory == null) { // sessionFactory 이게 null일때 딱 한번만 실행
				// MyBatis 컨테이너 공장을 메인 환경 설정파일 (sql-map-config.xml)을 읽어서 생성한다.
				Reader reader = Resources.getResourceAsReader("sql-map-config.xml"); // 마이바티스입력스트림을 한번 읽어들인다. 환경설정파일을 읽어서 만들어지는게 sessionFactory이다. 
				sessionFactory = new SqlSessionFactoryBuilder().build(reader); // 어떤 디비랑연결할지 어떤 SQL명령어를 사용할수 있는지 XML에 등록되어있어서 그걸 읽어들인다.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSessionInstance() {
		// 컨테이너 공장(SqlSessionFactory)에서 MyBatis 컨테이너(SqlSession) 하나를 생성하여 리턴한다.
		return sessionFactory.openSession(); //SqlSession 하나 리턴된다.
		
	}
	

}
