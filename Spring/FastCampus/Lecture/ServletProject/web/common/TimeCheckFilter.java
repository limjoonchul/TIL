package com.rubypaper.web.common;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "TimeCheckFilter")
public class TimeCheckFilter implements Filter {

    public TimeCheckFilter() {
        System.out.println("TimeCheckFilter 객체 생성!!!");
    }

    public void init(FilterConfig config) throws ServletException {

    }
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));


        long start = System.currentTimeMillis();
        chain.doFilter(req, resp);
        long end = System.currentTimeMillis();
        System.out.println(path + "실행 시간 : " +(end - start));
    }



}
