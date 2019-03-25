package com.lingfei.admin.config;

import com.lingfei.admin.interceptor.AdminInterceptor;
import com.lingfei.admin.interceptor.FrontInterceptor;
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
       //后台拦截
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/getExcel1","/getExcel2","/getExcel1")
                .addPathPatterns("/admin/form","/admin/announce","/admin/addContent")
                .addPathPatterns("/admin/editContent","/admin/deleteContent","/admin/updateContent")
                .addPathPatterns("/admin/index","/admin/toEmail","/admin/addEmail")
                .addPathPatterns("/admin/selectUser","/admin/getUser")
                .addPathPatterns("/table1","/user/edit","/user/delete","/user/update")
                .addPathPatterns("/table2","/competition/edit","/competition/update","/competition/delete");

        //前台拦截
        registry.addInterceptor(new FrontInterceptor())
                .addPathPatterns("/login","/user/delete");
    }
}
