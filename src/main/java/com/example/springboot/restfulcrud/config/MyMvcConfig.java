package com.example.springboot.restfulcrud.config;

import com.example.springboot.restfulcrud.component.LoginHandlerInterceptor;
import com.example.springboot.restfulcrud.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩张Spring MVC的功能
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/webcrud").setViewName("success");
    }



    //在spring boot中所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean //必须要将该组件添加到Spring容器中
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter webMvcConfigurerAdapter = new WebMvcConfigurerAdapter(){
            //视图映射控制器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //发送/请求时若没人处理默认会去请求/index.html,这里将index.html请求映射到login视图
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //spring boot已经做好了静态资源的映射，所以可以不用管静态资源
                /*registry.addInterceptor(new LoginHandlerInterceptor())
                        .addPathPatterns("/**") //拦截哪些请求
                        .excludePathPatterns("/","/index.html","/user/login"); //排除不拦截的请求路径
            */
            }
        };

        return webMvcConfigurerAdapter;
    }



    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
