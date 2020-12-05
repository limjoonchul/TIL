package com.rubypapper.web.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class CharacterEncodingFilter implements Filter {
	private String encoding;
	private String name;
    public CharacterEncodingFilter() {
        System.out.println("===> CharacterEncodingFilter���� ");
    }
    
	public void init(FilterConfig fConfig) throws ServletException {
		// web.xml ���Ͽ� ������ ���� �Ķ���� ���� ����
		encoding = fConfig.getInitParameter("encoding");
		name = fConfig.getInitParameter("name");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// ��� ���� (*.do) �� ����Ǳ� ������ ���ڵ��� ó���Ѵ�.
//		System.out.println(name + "���� ���� ���ڵ� ���Ͱ� �����ϰ� �־��..");
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
