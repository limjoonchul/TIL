package com.rubypaper.biz.util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryBean {
	private static SqlSessionFactory sessionFactory;
	
	// SqlSessionFactory ������ �̷��� ����
	static {
		try {
			if (sessionFactory == null) { // sessionFactory �̰� null�϶� �� �ѹ��� ����
				// MyBatis �����̳� ������ ���� ȯ�� �������� (sql-map-config.xml)�� �о �����Ѵ�.
				Reader reader = Resources.getResourceAsReader("sql-map-config.xml"); // ���̹�Ƽ���Է½�Ʈ���� �ѹ� �о���δ�. ȯ�漳�������� �о ��������°� sessionFactory�̴�. 
				sessionFactory = new SqlSessionFactoryBuilder().build(reader); // � ������������ � SQL��ɾ ����Ҽ� �ִ��� XML�� ��ϵǾ��־ �װ� �о���δ�.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSessionInstance() {
		// �����̳� ����(SqlSessionFactory)���� MyBatis �����̳�(SqlSession) �ϳ��� �����Ͽ� �����Ѵ�.
		return sessionFactory.openSession(); //SqlSession �ϳ� ���ϵȴ�.
		
	}
	

}
