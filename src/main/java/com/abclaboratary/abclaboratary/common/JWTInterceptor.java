package com.abclaboratary.abclaboratary.common;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptor implements HandlerInterceptor {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("request.getHeader(\"Authorization\")"+request.getHeader("Authorization"));
		
		System.out.println("Request Method: " + request.getMethod());

	    // Print request URL
	    System.out.println("Request URL: " + request.getRequestURL());

	    // Print request headers
	    Enumeration<String> headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String headerName = headerNames.nextElement();
	        System.out.println("Header: " + headerName + " = " + request.getHeader(headerName));
	    }

	    // Print request parameters
	    Map<String, String[]> parameters = request.getParameterMap();
	    for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
	        String paramName = entry.getKey();
	        String paramValue = Arrays.toString(entry.getValue());
	        System.out.println("Parameter: " + paramName + " = " + paramValue);
	    }

	    // Print request body (if applicable)
	    BufferedReader reader = request.getReader();
	    StringBuilder requestBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        requestBody.append(line);
	    }
	    System.out.println("Request Body: " + requestBody.toString());
	    
	    
	    
	    
	    
		
        String auth = request.getHeader("authorization");
        if(auth == null|| !JwtUtils.validateToken(auth)) {
        	response.setStatus(HttpStatus.UNAUTHORIZED.value());
        	JwtUtils.TokenVerification(auth);
            return false;
        }
        
//        try {
//            if (authorizationHeader == "" || authorizationHeader == null) {
//                throw new AuthorizationAlertException("Authorization token required.", ENTITY_NAME, "error");
//            }
//            String[] parts = authorizationHeader.split(" ");
//            String token;
//            try {
//                if (parts.length < 2) {
//                    token = parts[0];
//                } else {
//                    token = parts[1];
//                }
//            } catch (Exception e) {
//                throw new AuthorizationAlertException("Invalid Token", ENTITY_NAME, "error");
//            }
//            Algorithm algorithm = Algorithm.HMAC256(SECRET);
//            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
//            verifier.verify(token);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new AuthorizationAlertException(e.getMessage(), ENTITY_NAME, "error");
//        }
    
        
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
