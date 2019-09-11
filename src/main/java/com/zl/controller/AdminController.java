package com.zl.controller;

import com.zl.Util.ResultUtil;
import com.zl.model.*;
import com.zl.service.LineService;
import com.zl.service.StationService;
import com.zl.service.ThingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private LineService lineService;
    @Autowired
    private StationService stationService;
    @Autowired
    private ThingsService thingsService;

    //跳转到线路管理页面
    @RequestMapping("/lineManage")
    public String lineManage(){
        return "/admin/lineManage";
    }
    //跳转到物体管理页面
    @RequestMapping("/thingsManage")
    public String thingsManage(HttpServletRequest request,Model model){

        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("stationId",id);
        return "/admin/thingsManage";
    }
    //跳转到管理员首页
    @RequestMapping("/index")
    public String adminIndex(){

        return "admin/index";
    }
    //跳转到站点管理页面
    @RequestMapping("/stationManage")
    public String stationManage(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        SysUser sysUser = (SysUser) authentication.getPrincipal();

        model.addAttribute("user",sysUser);

        return "admin/stationManage";
    }
    //登陆成功欢迎页面
    @RequestMapping("/hello")
    public String hello(){
        return "admin/hello";
    }
    //无权限模态框
    @RequestMapping("noAuthority")
    public String noAuthority(){
        return "modal/noAuthorityModal";
    }
    //去修改密码模态框
    @RequestMapping("/toChangePassword")
    public String toChangePassword(){
        return "modal/changePasswordModal";
    }
    /**
     * 去新增站点模态框
     * @param id 当前所选线路的ID
     */
    @RequestMapping("/toAddStation")
    public String toAddStation(String id, Model model){
        model.addAttribute("id",id);
        return "modal/addStationModal";
    }
    //去新增物体模态框
    @RequestMapping("/toAddThing")
    public String toAddThing(Integer id, Model model){
        model.addAttribute("id",id);
        return "modal/addThingModal";
    }
    //去修改线路名称模态框
    @RequestMapping("/toUpdateLine")
    public String toUpdateLine(String id, Model model){
        model.addAttribute("id",id);
        return "modal/updateLineModal";
    }
    //去修改站点名称模态框
    @RequestMapping("/toUpdateStation")
    public String toUpdateStation(Integer id, Model model){
        Station station = stationService.oneStation(id);
        model.addAttribute("station",station);
        return "modal/updateStationModal";
    }
    //去修改物体名称模态框
    @RequestMapping("/toUpdateThing")
    public String toUpdateThing(Integer id, Model model){
        Things thing = thingsService.oneThing(id);
        model.addAttribute("thing",thing);
        return "modal/updateThingModal";
    }
    //去删除线路模态框
    @RequestMapping("/toDeleteLine")
    public String toDeleteLine(String id, Model model){
        model.addAttribute("id",id);
        return "modal/deleteLineModal";
    }
    //去删除站点模态框
    @RequestMapping("/toDeleteStation")
    public String toDeleteStation(int id, Model model){
        model.addAttribute("id",id);
        return "modal/deleteStationModal";
    }
    //去删除物体模态框
    @RequestMapping("/toDeleteThing")
    public String toDeleteThing(Integer id, Model model){
        model.addAttribute("id",id);
        return "modal/deleteThingModal";
    }
    //去批量删除
    @RequestMapping("/toDeleteAll")
    public String toDeleteAll(){
        return "modal/deleteAllModal";
    }
    //去新增线路模态框
    @RequestMapping("/toAddLine")
    public String toAddLine(){
        return "modal/addLineModal";
    }
    /**
     * 新增线路接口
     * @param lineName 线路名称
     */
    @ResponseBody
    @RequestMapping("/AddLine")
    public Result addLine(String lineName,HttpServletResponse response){

        if(lineName!=null&&lineName.length()!=0){
            lineService.addLine(lineName);
            //return "新增线路成功";
            return ResultUtil.success(response.getStatus(),"新增线路成功");
        }else {
            return ResultUtil.error(0,"请输入线路名称");
        }
    }
    /**
     * 修改线路名称接口
     * @param lineName 原名称
     * @param newlineName 新名称
     */
    @ResponseBody
    @RequestMapping("/UpdateLine")
    public Result updateLine(String lineName,String newlineName,HttpServletResponse response){

        if(newlineName!=null&&newlineName.length()!=0) {
            Line line = lineService.oneLine(lineName);
            lineService.updateLine(line.getId(),newlineName);
            //return "修改线路名称成功";
            return ResultUtil.success(response.getStatus(),"修改线路名称成功");
        }
        return ResultUtil.error(0,"请输入线路名称");
    }
    /**
     * 修改站点名称接口
     * @param stationId 原名称
     * @param newStationName 新名称
     */
    @ResponseBody
    @RequestMapping("/UpdateStation")
    public String updateStation(int stationId,String newStationName,String newStationUrl){
        stationService.updateStation(stationId,newStationName,newStationUrl);

        return "修改站点信息成功";
    }
    /**
     * 修改物体名称接口
     */
    @ResponseBody
    @RequestMapping("/UpdateThing")
    public String updateThing(int id,String tname,String tposition,String tid,String tgroup,String tcamera){
        thingsService.updateThing(id,tname,tposition,tid,tgroup,tcamera);
        return "修改物体信息成功";

    }

    /**
     * 删除线路接口
     * @param lineName 线路名称
     */
    @ResponseBody
    @RequestMapping("/DeleteLine")
    public String deleteLine(String lineName){

        lineService.deleteLine(lineName);
        return "删除线路成功";
    }

    /**
     * 删除站点接口
     * @param stationId 站点名称
     */
    @ResponseBody
    @RequestMapping("/DeleteStation")
    public String deleteStation(Integer stationId){

        stationService.deleteStation(stationId);

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        SysUser sysUser = (SysUser) authentication.getPrincipal();
//
//        if(!sysUser.getUsername().equals("admin")){
//            stationService.deleteStationRole(sysUser.getId(),stationId);
//        }


        return "删除站点成功";
    }

    /**
     * 删除物体接口
     * @param id 物体ID
     */
    @ResponseBody
    @RequestMapping("/DeleteThing")
    public String deleteThing(Integer id){

        thingsService.deleteThing(id);
        return "删除物体成功";
    }

    /**
     * 批量删除
     * @param checks 被选中
     */
    @ResponseBody
    @RequestMapping("/deleteAll")
    public String deleteAll(@RequestParam("checks[]") int[] checks){

        for (int check : checks) {
            thingsService.deleteThing(check);
        }
        return "删除物体成功";
    }
    /**
     * 新增站点接口
     * @param lineName 线路名称
     * @param stationName 站点名称
     */
    @ResponseBody
    @RequestMapping("/AddStation")
    public Result addStation(String lineName, String stationName,String sceneUrl,HttpServletResponse response){

        if(stationName!=null&&stationName.length()!=0) {
            Line line = lineService.oneLine(lineName);
            stationService.addStation(line.getId(), stationName,sceneUrl);
            return ResultUtil.success(response.getStatus(),"添加站点成功");
        }else {
            return ResultUtil.error(0,"请输入站点名称");
        }
    }

    @ResponseBody
    @RequestMapping("/AddThing")
    public String addThing(String tid, String tname,String tposition,String tgroup,String tcamera,Integer stationId){

        Things things = new Things();
        things.setStationId(stationId);
        things.setTid(tid);
        things.setTname(tname);
        things.setTposition(tposition);
        things.setTgroup(tgroup);
        things.setTcamera(tcamera);

        thingsService.addThing(things);

        return "新增物品成功";
    }
    /**
     * 展示所有线路接口
     */
    @ResponseBody
    @RequestMapping("/allLine")
    public List<Line> allLine(){

        return lineService.lineList();
    }
//    @ResponseBody
//    @RequestMapping("/allLine")
//    public Result allLine(HttpServletResponse response){
//
//        return ResultUtil.success(response.getStatus(),lineService.lineList());
//    }

    /**
     * 展示站点物体接口
     * @param stationId 站点ID
     */
    @ResponseBody
    @RequestMapping("/stationThings")
    public List<Things> stationThings(int stationId){

        return thingsService.allThing(stationId);
    }

    /**
     * 展示该管理员管理的站点接口
     * @param userId 管理员ID
     */
    @ResponseBody
    @RequestMapping("/adminStation")
    public Result adminStation(int userId, HttpServletResponse response){

        try {
            stationService.adminStations(userId);
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtil.error(0,e.getMessage());
        }
        return ResultUtil.success(response.getStatus(),stationService.adminStations(userId));
    }
}
