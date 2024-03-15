package com.abclaboratary.abclaboratary.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptor implements HandlerInterceptor {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String auth = request.getHeader("authorization");
        if(auth == null|| !JwtUtils.validateToken(auth)) {
        	response.setStatus(HttpStatus.UNAUTHORIZED.value());
        	JwtUtils.TokenVerification(auth);
            return false;
        }
        
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
