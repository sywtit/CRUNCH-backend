package com.crunch.crunch_server.configuration;

import com.crunch.crunch_server.util.JwtInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    private final String[] EXCLUDE_PATHS = {
        "","/","/api/user/account/signup", "/api/user/account/auth",
        "/project/search/**","/user","/user/**","/api/**","/login","/api/user/**",
    };

    private final String[] INCLUDE_PATHS = { 
        "/users/**", "/project/work/**", "/notice/**"
    };

    @Autowired
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
						.addPathPatterns("/api/**")
						.excludePathPatterns(EXCLUDE_PATHS);
    }

    @Bean 
    public MultipartResolver multipartResolver() 
    { 
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(); 
        multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10 = 10 MB 
        return multipartResolver; 
    }

}
