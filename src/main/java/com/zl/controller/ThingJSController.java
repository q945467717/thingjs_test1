package com.zl.controller;


import com.zl.model.Line;
import com.zl.model.Station;
import com.zl.model.ThingJsUser;
import com.zl.model.Things;
import com.zl.service.LineService;
import com.zl.service.ThingJSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/thingJS")
public class ThingJSController {

    @Autowired
    private LineService lineService;

    @Autowired
    private ThingJSService thingJSService;

    /**
     * 线路及其所包含站点信息
     * @param response
     * @return
     */
    @RequestMapping("/lineInfo")
    public List<Line> lineInfo(HttpServletResponse response){

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials","true");
        return lineService.lineList();
    }

    /**
     * 进出人员信息（卡号，时间）
     * @param response
     * @param doorId 需要显示该信息的闸机的ID
     * @return
     */
    @RequestMapping("/doorPeopleInfo")
    public List doorPeopleInfo(HttpServletResponse response, @RequestParam("doorId[]") String[] doorId){

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials","true");

        List<ThingJsUser> list2 = new ArrayList<ThingJsUser>();
        for (String s : doorId) {
            List<ThingJsUser> list = thingJSService.peopleInfo(s);
            list2.addAll(list);
        }
        System.out.println(list2);
        return list2;
        // return map;
    }

    /**
     * 闸机进出人数
     * @param doorNum 需要显示该信息的闸机的ID
     */
    @RequestMapping("/doorPeopleNum")
    public List doorPeopleNum(HttpServletResponse response, @RequestParam("doorNum[]") String[] doorNum){

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials","true");

        List<Map> list = new ArrayList<Map>();

        for (String s : doorNum) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id", s);
            map.put("num", thingJSService.peopleNum(s));
            list.add(map);
        }
        return list;
        // return map;
    }

    /**
     * 刷卡测试
     * @param machineId 闸机ID
     * @param cardId 卡号
     * @return
     */
    @RequestMapping("/doorSimulation")
    public String doorSimulation(String machineId,String cardId){
        ThingJsUser thingJsUser = new ThingJsUser();
        thingJsUser.setMachineId(machineId);
        thingJsUser.setCardId(cardId);
        thingJsUser.setTime(new Date());
        thingJSService.addPeople(thingJsUser);
        return  "刷卡成功 卡号:"+cardId;
    }

    @RequestMapping("/stationJump")
    public Station stationJump(HttpServletResponse response,int stationId){

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials","true");

        return thingJSService.oneStation(stationId);
    }

    @RequestMapping("/thingInfo")
    public List<Things> thingInfo(HttpServletResponse response,Integer stationId){

        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials","true");

        return thingJSService.thingList(stationId);
    }



}
