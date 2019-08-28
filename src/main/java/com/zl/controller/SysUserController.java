package com.zl.controller;

import com.zl.model.SysUser;
import com.zl.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys")
public class SysUserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping("/register")
    public String register(){

        SysUser sysUser=new SysUser();
        sysUser.setUsername("user");
        sysUser.setPassword("123456");
        sysUserService.save(sysUser.getUsername(), bCryptPasswordEncoder.encode(sysUser.getPassword()));
        return "Success";
    }
}
