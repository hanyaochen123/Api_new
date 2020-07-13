package com.chen.controller;


import org.mybatis.spring.SqlSessionTemplate;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Get {
    @Autowired
    private SqlSessionTemplate template;
    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String test(@RequestBody, @ResponseBody){
        return "Spring boot";
        template.

    }
}
