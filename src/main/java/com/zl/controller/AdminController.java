package com.zl.controller;

import com.zl.model.Line;
import com.zl.model.Station;
import com.zl.model.Things;
import com.zl.service.LineService;
import com.zl.service.StationService;
import com.zl.service.ThingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public String lineManage(){ return "/admin/lineManage"; }
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
    //登陆成功欢迎页面
    @RequestMapping("/hello")
    public String hello(){
        return "admin/hello";
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
    public String toAddThing(String id, Model model){
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
    public String toUpdateStation(String id, Model model){
        model.addAttribute("id",id);
        return "modal/updateStationModal";
    }
    //去修改物体名称模态框
    @RequestMapping("/toUpdateThing")
    public String toUpdateThing(int id, Model model){
        model.addAttribute("id",id);
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
    public String toDeleteThing(int id, Model model){
        model.addAttribute("id",id);
        return "modal/deleteThingModal";
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
    public String addLine(String lineName){

        if(lineName!=null&&lineName.length()!=0){
            lineService.addLine(lineName);
            return "新增线路成功";
        }else {
            return "请输入线路名称";
        }
    }
    /**
     * 修改线路名称接口
     * @param lineName 原名称
     * @param newlineName 新名称
     */
    @ResponseBody
    @RequestMapping("/UpdateLine")
    public String updateLine(String lineName,String newlineName){

        if(newlineName!=null&&newlineName.length()!=0) {
            Line line = lineService.oneLine(lineName);
            lineService.updateLine(line.getId(),newlineName);
            return "修改线路名称成功";
        }
        return "请输入线路名称";
    }
    /**
     * 修改站点名称接口
     * @param stationId 原名称
     * @param newStationName 新名称
     */
    @ResponseBody
    @RequestMapping("/UpdateStation")
    public String updateStation(int stationId,String newStationName,String newStationUrl){

        if(newStationName!=null&&newStationName.length()!=0) {
            if (newStationUrl != null && newStationUrl.length() != 0) {
                stationService.updateStation(stationId,newStationName,newStationUrl);
                return "修改站点信息成功";
            } else {
                Station station = stationService.oneStation(stationId);
                System.out.println(station);
                stationService.updateStation(stationId, newStationName, station.getSceneId());
                return "修改站点信息成功";
            }
        }else if(newStationUrl != null && newStationUrl.length() != 0){
            Station station = stationService.oneStation(stationId);
            stationService.updateStation(stationId, station.getStationName(), newStationUrl);
            return "修改站点信息成功";
        }
            return "请输入站点名称或Url";
    }
    /**
     * 修改物体名称接口
     */
    @ResponseBody
    @RequestMapping("/UpdateThing")
    public String updateThing(int id,String tname,String tposition,String tid,String tgroup,String tcamera){

        if(tname!=null&&tname.length()!=0) {
            if(tposition!=null&&tposition.length()!=0) {
                if (tid != null && tid.length() != 0) {
                    if (tgroup != null && tgroup.length() != 0) {
                        if (tcamera != null && tcamera.length() != 0) {
                            thingsService.updateThing(id,tname,tposition,tid,tgroup,tcamera);
                            return "修改物体信息成功";
                        }else {
                            Things things = thingsService.oneThing(id);
                            thingsService.updateThing(id,tname,tposition,tid,tgroup,things.getTcamera());
                            return "修改物体信息成功";
                        }
                    }else if(tcamera != null && tcamera.length() != 0){
                        Things things = thingsService.oneThing(id);
                        thingsService.updateThing(id,tname,tposition,tid,things.getTgroup(),tcamera);
                        return "修改物体信息成功";
                    }else{
                        Things things = thingsService.oneThing(id);
                        thingsService.updateThing(id,tname,tposition,tid,things.getTgroup(),things.getTcamera());
                        return "修改物体信息成功";
                    }
                }else if(tgroup != null && tgroup.length() != 0){
                    if(tcamera != null && tcamera.length() != 0){
                        Things things = thingsService.oneThing(id);
                        thingsService.updateThing(id,tname,tposition,things.getTid(),tgroup,tcamera);
                        return "修改物体信息成功";
                    }else {
                        Things things = thingsService.oneThing(id);
                        thingsService.updateThing(id,tname,tposition,things.getTid(),tgroup,things.getTcamera());
                        return "修改物体信息成功";
                    }
                }else if(tcamera != null && tcamera.length() != 0){
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,tname,tposition,things.getTid(),things.getTgroup(),tcamera);
                    return "修改物体信息成功";
                }else {
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,tname,tposition,things.getTid(),things.getTgroup(),things.getTcamera());
                    return "修改物体信息成功";
                }
            }else if(tid != null && tid.length() != 0){
                if(tgroup != null && tgroup.length() != 0){
                    if(tcamera != null && tcamera.length() != 0){
                        Things things = thingsService.oneThing(id);
                        thingsService.updateThing(id,tname,things.getTposition(),tid,tgroup,tcamera);
                        return "修改物体信息成功";
                    }else {
                        Things things = thingsService.oneThing(id);
                        thingsService.updateThing(id,tname,things.getTposition(),tid,tgroup,things.getTcamera());
                        return "修改物体信息成功";
                    }
                }else if(tcamera != null && tcamera.length() != 0){
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,tname,things.getTposition(),tid,things.getTgroup(),tcamera);
                    return "修改物体信息成功";
                }else {
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,tname,things.getTposition(),tid,things.getTgroup(),things.getTcamera());
                    return "修改物体信息成功";
                }
            }else if(tgroup != null && tgroup.length() != 0){
                if(tcamera != null && tcamera.length() != 0){
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,tname,things.getTposition(),things.getTid(),tgroup,tcamera);
                    return "修改物体信息成功";
                }else {
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,tname,things.getTposition(),things.getTid(),tgroup,things.getTcamera());
                    return "修改物体信息成功";
                }
            }else if(tcamera != null && tcamera.length() != 0){
                Things things = thingsService.oneThing(id);
                thingsService.updateThing(id,tname,things.getTposition(),things.getTid(),things.getTgroup(),tcamera);
                return "修改物体信息成功";
            }else {
                Things things = thingsService.oneThing(id);
                thingsService.updateThing(id,tname,things.getTposition(),things.getTid(),things.getTgroup(),things.getTcamera());
                return "修改物体信息成功";
            }
        }else if(tposition!=null&&tposition.length()!=0){
            if(tid != null && tid.length() != 0){
                if(tgroup != null && tgroup.length() != 0){
                    if(tcamera != null && tcamera.length() != 0){
                        Things things = thingsService.oneThing(id);
                        thingsService.updateThing(id,things.getTname(),tposition,tid,tgroup,tcamera);
                        return "修改物体信息成功";
                    }else {
                        Things things = thingsService.oneThing(id);
                        thingsService.updateThing(id,things.getTname(),tposition,tid,tgroup,things.getTcamera());
                        return "修改物体信息成功";
                    }
                }else if(tcamera != null && tcamera.length() != 0){
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,things.getTname(),tposition,tid,things.getTgroup(),tcamera);
                    return "修改物体信息成功";
                }else {
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,things.getTname(),tposition,tid,things.getTgroup(),things.getTcamera());
                    return "修改物体信息成功";
                }
            }else  if(tgroup != null && tgroup.length() != 0){
                if(tcamera != null && tcamera.length() != 0){
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,things.getTname(),tposition,things.getTid(),tgroup,tcamera);
                    return "修改物体信息成功";
                }else {
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,things.getTname(),tposition,things.getTid(),tgroup,things.getTcamera());
                    return "修改物体信息成功";
                }
            }else if(tcamera != null && tcamera.length() != 0){
                Things things = thingsService.oneThing(id);
                thingsService.updateThing(id,things.getTname(),tposition,things.getTid(),things.getTgroup(),tcamera);
                return "修改物体信息成功";
            }else {
                Things things = thingsService.oneThing(id);
                thingsService.updateThing(id,things.getTname(),tposition,things.getTid(),things.getTgroup(),things.getTcamera());
                return "修改物体信息成功";
            }
        }else if(tid != null && tid.length() != 0){
            if(tgroup != null && tgroup.length() != 0){
                if(tcamera != null && tcamera.length() != 0){
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,things.getTname(),things.getTposition(),tid,tgroup,tcamera);
                    return "修改物体信息成功";
                }else {
                    Things things = thingsService.oneThing(id);
                    thingsService.updateThing(id,things.getTname(),things.getTposition(),tid,tgroup,things.getTcamera());
                    return "修改物体信息成功";
                }
            }else if(tcamera != null && tcamera.length() != 0){
                Things things = thingsService.oneThing(id);
                thingsService.updateThing(id,things.getTname(),things.getTposition(),tid,things.getTgroup(),tcamera);
                return "修改物体信息成功";
            }else {
                Things things = thingsService.oneThing(id);
                thingsService.updateThing(id,things.getTname(),things.getTposition(),tid,things.getTgroup(),things.getTcamera());
                return "修改物体信息成功";
            }
        }else if(tgroup != null && tgroup.length() != 0){
            if(tcamera != null && tcamera.length() != 0){
                Things things = thingsService.oneThing(id);
                thingsService.updateThing(id,things.getTname(),things.getTposition(),things.getTid(),tgroup,tcamera);
                return "修改物体信息成功";
            }else {
                Things things = thingsService.oneThing(id);
                thingsService.updateThing(id,things.getTname(),things.getTposition(),things.getTid(),tgroup,things.getTcamera());
                return "修改物体信息成功";
            }
        }else if(tcamera != null && tcamera.length() != 0){
            Things things = thingsService.oneThing(id);
            thingsService.updateThing(id,things.getTname(),things.getTposition(),things.getTid(),things.getTgroup(),tcamera);
            return "修改物体信息成功";
        }else {
            return "请输入修改的信息";
        }

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
     * 新增站点接口
     * @param lineName 线路名称
     * @param stationName 站点名称
     */
    @ResponseBody
    @RequestMapping("/AddStation")
    public String addStation(String lineName, String stationName,String sceneUrl){

        if(stationName!=null&&stationName.length()!=0) {
            Line line = lineService.oneLine(lineName);
            stationService.addStation(line.getId(), stationName,sceneUrl);
            return "新增站点成功";
        }else {
            return "请输入站点名称";
        }
    }

    @ResponseBody
    @RequestMapping("/AddThing")
    public String addThing(String tid, String tname,String tposition,String tgroup,String tcamera,int stationId){

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

    /**
     * 展示站点物体接口
     * @param stationId 站点ID
     */
    @ResponseBody
    @RequestMapping("/stationThings")
    public List<Things> stationThings(int stationId){

        return thingsService.allThing(stationId);
    }
}
