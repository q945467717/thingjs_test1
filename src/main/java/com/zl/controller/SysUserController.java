package com.zl.controller;

import com.zl.Util.ResultUtil;
import com.zl.exception.UserNotExistException;
import com.zl.model.Result;
import com.zl.model.SysUser;
import com.zl.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/sys")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ResponseBody
    @RequestMapping("/register")
    public Result register(@RequestParam("stationName[]")String[] stationName, String username, String password, HttpServletResponse response){

        try{
            SysUser sysUser = sysUserService.logincheck(username);
        }catch (Exception e ){
            return ResultUtil.error(0,"用户名已存在");
        }
        try {
                sysUserService.save(stationName,username,password);
        } catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }
        return ResultUtil.success(response.getStatus(),"添加管理员成功");
    }

    //去添加管理员模态框
    @RequestMapping("/toAddAdmin")
    public String toAddAdminModel(){
        return "/modal/addAdminModal";
    }

    //跳转到权限管理页面
    @RequestMapping("/peopleManage")
    public String peopleManage(){
        return "/admin/peopleManage";
    }

    //管理员信息展示接口
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

    //修改权限
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
    @ResponseBody
    @RequestMapping("/setStationUpdateAuthority")
    public Result setStationUpdateAuthority(HttpServletResponse response,Integer id){

        try {
            sysUserService.setStationUpdate(id);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }
        return ResultUtil.success(response.getStatus(),"修改权限成功");

    }

}
