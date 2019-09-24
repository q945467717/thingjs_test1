package com.zl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping("/")
    public String index(){
        return "redirect:toLogin";
    }

//    @RequestMapping("/")
//    public String index(){
//        return "a";
//    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("/login-error")
    public String loginError(){
        return "error";
    }

    @RequestMapping("/thingJStest")
    public String test(){
        return "thingJS/thingJS";
    }

}
