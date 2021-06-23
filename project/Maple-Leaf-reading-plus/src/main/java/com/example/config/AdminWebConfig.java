package com.example.config;


import com.example.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1、编写一个拦截器实现HandlerInterceptor接口
 * 2、拦截器注册到容器中（实现WebMvcConfigurer的addInterceptors）
 * 3、指定拦截规则【如果是拦截所有，静态资源也会被拦截】
 * @author yxmia
 */
//@EnableWebMvc
@Configuration
public class AdminWebConfig implements WebMvcConfigurer{



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
            //拦截什么路径//所有请求都被拦截包括静态资源
            .addPathPatterns("/**")

            //不拦截那些
            //静态资源目录+错误页
            .excludePathPatterns("/css/**","/fonts/**","/images/**","/js/**","/lib/**","/error")
            //login_logout包
            .excludePathPatterns("/","/login","/toLogin","/switchMaster","/logoutMaster","/finalLogoutMaster","/toIndex")
            //book
            .excludePathPatterns("/book/**")
            //app
            .excludePathPatterns("/APP/**")

            .excludePathPatterns("/APP/**")
            ;

    }
}
