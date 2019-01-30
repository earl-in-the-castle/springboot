package com.example.firstspringboot.controller;

import com.example.firstspringboot.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${girl.name}")
    private String name;

    @Value("${girl.content}")
    private String content;

    @Autowired
    private GirlProperties girlProperties;
    //访问/hello或者/hi任何一个地址，都会返回一样的结果
    @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
    public String say(){

        System.out.println(name);
        System.out.println(girlProperties.getAge()+girlProperties.getName()+content);

        return "hi you!!!";
    }

}
