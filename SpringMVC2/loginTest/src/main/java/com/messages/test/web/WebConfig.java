package com.messages.test.web;

import com.messages.test.web.interceptor.LogInterceptor;
import com.messages.test.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    String[] whiteList = {"/css/**", "/*.ico", "/error"};
    String[] LoginWhiteList = {"/css/**", "/*.ico", "/error", "/",
                                "/login","/logout", "/member"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(whiteList);

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(LoginWhiteList);
    }

}
