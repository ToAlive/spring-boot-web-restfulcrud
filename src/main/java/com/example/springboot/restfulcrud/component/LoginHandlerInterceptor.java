package com.example.springboot.restfulcrud.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* 登陆拦截器
* 要实现HandlerInterceptor接口
* */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //执行目标方法之前
    //登陆之后将用户信息保存到session中，拦截的时候从session中获取用户信息
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        Object userInfo = httpServletRequest.getSession().getAttribute("userInfo");
        if(userInfo == null){
            //没有登陆
            httpServletRequest.setAttribute("msg","没有权限！");
            //转发到登陆页面
            httpServletRequest.getRequestDispatcher("/index.html").forward(httpServletRequest,httpServletResponse);
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
