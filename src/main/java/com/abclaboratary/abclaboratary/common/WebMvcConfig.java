package com.abclaboratary.abclaboratary.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final JWTInterceptor jwtInterceptor;
	
	public WebMvcConfig(JWTInterceptor jwtInterceptor) {
        this.jwtInterceptor = jwtInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .excludePathPatterns(
                        "/auth/**"
                        
                )
                .addPathPatterns(
//                        "/**"
                );
    }

}
