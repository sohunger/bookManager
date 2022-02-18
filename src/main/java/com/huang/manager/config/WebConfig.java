package com.huang.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    MyInterceptor myInterceptor;
    //视图控制器
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/adminLogin").setViewName("adminLogin");
        registry.addViewController("/userFindBook").setViewName("user/findBook");
        registry.addViewController("/userBorrowingBook").setViewName("user/borrowingBook");
        registry.addViewController("/personalCenter").setViewName("user/personalCenter");
        registry.addViewController("/userModifyInfo").setViewName("/user/modifyInfo");
        registry.addViewController("/adminAddBook").setViewName("/admin/addBook");
        registry.addViewController("/adminDeleteBook").setViewName("/admin/deleteBook");
        registry.addViewController("/adminFindBook").setViewName("/admin/findBook");
        registry.addViewController("/adminAddCategory").setViewName("/admin/addCategory");
        registry.addViewController("/userIndex").setViewName("user/index");
        registry.addViewController("/adminIndex").setViewName("admin/index");
        registry.addViewController("/userBorrowRecordPage").setViewName("user/borrowingBookRecord");
        registry.addViewController("/amdinSelectUserPage").setViewName("admin/userManage");
        registry.addViewController("/adminSelectRecordPage").setViewName("admin/userRecord");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").
                excludePathPatterns("","/userLogin","/adminLogin","/adminLoginPage");
    }
//
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
