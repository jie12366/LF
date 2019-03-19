package com.lingfei.admin.config;

import com.lingfei.admin.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {
        //默认到登陆页
        registry.addViewController("/").setViewName("forward:/adminLogin");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //不需要拦截的路径
        String[] excludes={"/","/adminLogin","/login","/static/**"};
        //拦截所有路径除了不需要拦截的路径
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns(excludes);
    }
}
