package com.lingfei.admin.config;

import com.lingfei.admin.interceptor.AdminInterceptor;
import com.lingfei.admin.interceptor.FrontInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //后台拦截
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/getExcel1", "/getExcel2", "/getExcel1")
                .addPathPatterns("/admin/form", "/admin/addContent", "/admin/announce")
                .addPathPatterns("/admin/editContent", "/admin/deleteContent", "/admin/updateContent")
                .addPathPatterns("/admin/index", "/admin/toEmail", "/admin/addEmail")
                .addPathPatterns("/admin/selectUser", "/admin/getUser")
                .addPathPatterns("/table1", "/user/edit", "/user/delete", "/user/update")
                .addPathPatterns("/table2", "/competition/edit", "/competition/update", "/competition/delete")
                .addPathPatterns("/es/user");

        //前台拦截
        registry.addInterceptor(new FrontInterceptor())
                .addPathPatterns("/", "/index/about","/index/competition","/index/announce","/index/message");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
