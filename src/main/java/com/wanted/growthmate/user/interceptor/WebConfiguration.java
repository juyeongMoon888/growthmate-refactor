package com.wanted.growthmate.user.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //로그인 체크
        registry.addInterceptor(loginCheckInterceptor)
                .order(1)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api//users/login",
                                             "/api/users/signup");
        //권한 체크 (강사, 수강생)
        registry.addInterceptor(authorizationInterceptor)
                .order(2)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/users/signup", "/api/users/login");
    }
}
