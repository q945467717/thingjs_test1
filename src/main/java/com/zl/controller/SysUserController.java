package com.zl.controller;

import com.zl.Util.ResultUtil;
import com.zl.exception.UserNotExistException;
import com.zl.model.Result;
import com.zl.model.SysUser;
import com.zl.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/sys")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    //添加管理员接口
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
    //修改密码
    @ResponseBody
    @RequestMapping("/changePassword")
    public String changePassword(String password){
        sysUserService.changePassword(password);
        return "ok";
    }

    //去添加管理员模态框
    @RequestMapping("/toAddAdmin")
    public String toAddAdmin(){
        return "/modal/addAdminModal";
    }

    //去删除管理员模态框
    @RequestMapping("/toDeleteAdmin")
    public String toDeleteAdmin(Integer id , Model model){

        model.addAttribute("userId",id);

        return "/modal/deleteAdminModal";
    }
    //去修改管理员模态框
    @RequestMapping("/toUpdateAdmin")
    public String toUpdateAdmin(Integer id , Model model){

        model.addAttribute("userId",id);

        return "/modal/updateAdminModal";
    }

    //删除管理员接口
    @ResponseBody
    @RequestMapping("/DeleteAdmin")
    public Result DeleteAdmin(Integer userId ,HttpServletResponse response){

        try {
            sysUserService.deleteAdmin(userId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }
        return ResultUtil.success(response.getStatus(),"删除成功");
    }

    //修改管理员接口
    @ResponseBody
    @RequestMapping("/updateAdmin")
    public Result updateAdmin(@RequestParam("stationName[]")String[] stationName,Integer userId, HttpServletResponse response){

        try {
            sysUserService.updateAdmin(stationName,userId);
        } catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }
        return ResultUtil.success(response.getStatus(),"修改成功");
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
