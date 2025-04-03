package com.example.schedulehub.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST_POST = {"/login", "/user"};

    private static final String[] WHITE_LIST_GET = {"/schedule", "/schedule/*", "/user", "/user/find", "/user/find/*" };

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpRequest.getSession(false);

        if ("GET".equalsIgnoreCase(httpRequest.getMethod())){

            if(!isWhiteListGet(requestURI)){

                if(session == null || session.getAttribute("sessionKey") == null){
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }

            }

        }else if("POST".equalsIgnoreCase(httpRequest.getMethod())){

            if(!isWhiteListPost(requestURI)){

                if(session == null || session.getAttribute("sessionKey") == null){
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
                }

            }

        }else{
            if(session == null || session.getAttribute("sessionKey") == null){
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isWhiteListPost(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST_POST, requestURI);
    }

    private boolean isWhiteListGet(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST_GET, requestURI);
    }
}
