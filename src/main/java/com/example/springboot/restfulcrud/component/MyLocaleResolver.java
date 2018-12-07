package com.example.springboot.restfulcrud.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/*
* 区域信息解析器
* 可以在链接上切换区域信息
* */

public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String l = httpServletRequest.getParameter("l");
        //使用系统默认的区域信息
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            String[] s = l.split("_");
            //s[0]语言代码，s[1]国家代码
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
