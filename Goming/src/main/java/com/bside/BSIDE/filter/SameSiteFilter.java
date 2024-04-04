package com.bside.BSIDE.filter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SameSiteFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            chain.doFilter(httpRequest, httpResponse);

            // JSESSIONID 쿠키 설정
            Cookie[] cookies = httpRequest.getCookies();
            System.out.println("@##@!#!@#!@getgetgetgetgeteget#!@#!@#!@#!@##@");
            System.out.println(httpRequest.getRequestURI());

            System.out.println("@##@!#!@#!@#!@#!@#!@#!@##@");
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("JSESSIONID".equals(cookie.getName())) {
                        String sameSiteValue = "None"; // 또는 "Lax" 또는 "Strict" 등을 사용
                        String cookieHeaderValue = String.format("%s=%s; Secure; SameSite=%s", cookie.getName(), cookie.getValue(), sameSiteValue);
                        httpResponse.setHeader("Set-Cookie", cookieHeaderValue);
                    }
                }
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 로직
    }

    @Override
    public void destroy() {
        // 파기 로직
    }
}