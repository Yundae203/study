package com.messages.test.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class loginFilter implements Filter {

    private static final String[] whiteURL = {"/", "/test", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            if (isLoginCheck(requestURI)) {
                HttpSession session = httpRequest.getSession(false);

                if (session == null || session.getAttribute("loginMember") == null) {
                    httpResponse.sendRedirect("/?redirectURL=" + requestURI);
                    return;
                }

            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("로그인 필터 종료");
        }
    }

    public boolean isLoginCheck(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whiteURL, requestURI);
    }

}
