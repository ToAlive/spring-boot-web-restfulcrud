package com.example.springboot.restfulcrud.controller;

import com.example.springboot.restfulcrud.exception.UserNotExistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    //默认访问页
    /*@RequestMapping({"/","/index.html"})
    public String index(){
        return "index";
    }*/

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){
        if("aaa".equals(user)){
            throw new UserNotExistException();
        }
        return "HELLO WORLD";
    }

    //查出一些数据，在页面上显示
    @RequestMapping("/success")
    public String success(Map<String,Object> maps){
        //默认找的路径是classpath:/templates/success.html
        maps.put("hello","你好");//默认会被放在请求域中
        maps.put("helloHtml","<h1>hello</h1>");
        maps.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }


}
