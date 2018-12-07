package com.example.springboot.restfulcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    //登陆
    //@RequestMapping(value="/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String userName,
                        @RequestParam("password") String password, Map<String,String> map, HttpSession session){
        if(!StringUtils.isEmpty(userName) && "123456".equals(password)){
            //登陆成功,防止表单重复提交 做重定向到主页
            //return "dashboard";
            session.setAttribute("userInfo",userName);
            return "redirect:/main.html";
        }else{
            //登陆失败
            map.put("msg","密码错误！");
            return "login";
        }
    }
}
