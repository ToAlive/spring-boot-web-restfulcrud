package com.example.springboot.restfulcrud.component;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;


//定义一个我们自己的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        //这个map就是json、页面中能够获取到的所有属性
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);

        map.put("author","WhoamI");
        //从请求域中我们的异常类中定义的异常信息
        Map<String,Object> ext = (Map<String, Object>) requestAttributes.getAttribute("ext", RequestAttributes.SCOPE_REQUEST);

        map.put("ext",ext);
        return map;
    }
}
