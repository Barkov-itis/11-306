package ru.itis.filters;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Boolean isAthenticated = false;
        Boolean sessionExist = session != null;
        Boolean isLogin = request.getRequestURI().equals("/signIn");

        if (sessionExist) {
            isAthenticated = (Boolean) session.getAttribute("aythenticated");
            if (isAthenticated == null) {
                isAthenticated = false;
            }
        }

        if (isAthenticated && !isLogin || !isAthenticated && isLogin) {
            filterChain.doFilter(request, response);
        } else if (isAthenticated && isLogin) {
            response.sendRedirect("/");
        } else {
            response.sendRedirect("/signIn");
        }
    }

    @Override
    public void destroy() {

    }
}
