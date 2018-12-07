package com.example.springboot.restfulcrud.controller;

import com.example.springboot.restfulcrud.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice //异常通知，处理所有Controller中@RequestMapping标注过的方法
public class MyExceptionHandler {

    //方案一
    //浏览器、客户端返回的都是json数据
/*    @ResponseBody//不要漏掉这个注解
    @ExceptionHandler(UserNotExistException.class)
    //产生异常时，会将异常对象传递进此方法中
    public Map<String,Object> handlerException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user notexist");
        map.put("message",e.getMessage());
        return map;
    }*/

    //方案二：
    /*
    * 如果没有指定server.error.path、error.path的话BasicErrorController默认会来处理/error请求
    * 它的处理方式是自适应的
    * @RequestMapping({"${server.error.path:${error.path:/error}}"})
    * public class BasicErrorController extends AbstractErrorController {
    * */
    @ExceptionHandler(UserNotExistException.class)
    //产生异常时，会将异常对象传递进此方法中
    public String handlerException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //必须要传入我们自己的错误状态码,不传默认就是200了
        //Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user notexist");
        map.put("message","出错了！！！");
        //绑定在request域中，可以在我们自定义的MyErrorAttributes中可以获取到
        request.setAttribute("ext",map);
        return "forward:/error";
    }
}
