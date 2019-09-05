package com.zl.controller;

import com.zl.Util.ResultUtil;
import com.zl.model.Result;
import com.zl.model.SysUser;
import com.zl.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        sysUser.setUsername("test2");
        sysUser.setPassword("123456");
        sysUserService.save(sysUser.getUsername(), bCryptPasswordEncoder.encode(sysUser.getPassword()));
        return "Success";
    }

    //跳转到权限管理页面
    @RequestMapping("/peopleManage")
    public String peopleManage(){
        return "/admin/peopleManage";
    }

    @ResponseBody
    @RequestMapping("/authorityManage")
    public Result authorityManage(HttpServletResponse response){

        try {
            sysUserService.allsysUser();
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }
        return ResultUtil.success(response.getStatus(),sysUserService.allsysUser());
    }

    @ResponseBody
    @RequestMapping("/setAddAuthority")
    public Result setAddAuthority(HttpServletResponse response,Integer id){

        try {
            sysUserService.setAdd(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }
        return ResultUtil.success(response.getStatus(),"修改权限成功");

    }
    @ResponseBody
    @RequestMapping("/setDeleteAuthority")
    public Result setDeleteAuthority(HttpServletResponse response,Integer id){

        try {
            sysUserService.setDelete(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }
        return ResultUtil.success(response.getStatus(),"修改权限成功");

    }
    @ResponseBody
    @RequestMapping("/setUpdateAuthority")
    public Result setUpdateAuthority(HttpServletResponse response,Integer id){

        try {
            sysUserService.setUpdate(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }
        return ResultUtil.success(response.getStatus(),"修改权限成功");

    }

}
