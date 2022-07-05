package com.jicoder.leethub;

import com.jicoder.leethub.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LeetHubApplication implements WebMvcConfigurer {

    @Autowired
    private UserInterceptor userInterceptor;

    public static void main(String[] args) {
        SpringApplication.run(LeetHubApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login", "/user/register");
    }
}
