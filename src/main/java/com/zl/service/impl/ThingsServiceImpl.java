package com.zl.service.impl;

import com.zl.mapper.LineMapper;
import com.zl.mapper.StationMapper;
import com.zl.mapper.ThingsMapper;
import com.zl.model.*;
import com.zl.service.ThingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.server.csrf.CsrfServerLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThingsServiceImpl implements ThingsService {

    @Autowired
    private ThingsMapper thingsMapper;
    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private LineMapper lineMapper;

    @Override
    public List<Things> allThing(int stationId) {
        return thingsMapper.allThing(stationId);
    }

    @Override
    public void addThing(String tid, String tname,String tposition,String tgroup,String tcamera,Integer stationId) {

        Station station = stationMapper.oneStation(stationId);

        List<Station> stationList = stationMapper.findStationListByName(station.getStationName());

        for(Station station1:stationList){

        Things things = new Things();
        things.setStationId(station1.getStationId());
        things.setTid(tid);
        things.setTname(tname);
        things.setTposition(tposition);
        things.setTgroup(tgroup);
        things.setTcamera(tcamera);

        thingsMapper.addThing(things);

        }
    }

    @Override
    public void deleteThing(Integer id) {

        Things things = thingsMapper.oneThing(id);

        Station station = stationMapper.oneStation(things.getStationId());

        List<Station> stationList = stationMapper.findStationListByName(station.getStationName());

        for(Station station1:stationList){
            thingsMapper.deleteThing(station1.getStationId(),things.getTid());
        }

    }

    @Override
    public boolean updateThing(int id,String tname,String tposition,String tid,String tgroup,String tcamera) {

        Things things = thingsMapper.oneThing(id);

        Station station = stationMapper.oneStation(things.getStationId());

        List<Station> stationList = stationMapper.findStationListByName(station.getStationName());

        for(Station station1:stationList){

            List<Things> thingsList1 = thingsMapper.allThingNoTid(station1.getStationId(), things.getTid());

            for(Things things1:thingsList1){

                if(things1.getTid().equals(tid)){
                    return false;
                }

            }

            List<Things> thingsList = thingsMapper.allThingByTid(station1.getStationId(),things.getTid());

            for(Things things1:thingsList){
                thingsMapper.updateThing(things1.getId(),tname,tposition,tid,tgroup,tcamera);
            }
        }

        return true;
    }

    @Override
    public Things oneThing(Integer id) {
        return thingsMapper.oneThing(id);
    }

    @Override
    public void addThingGroup(String tg_name,String stationName,String lineName) {

        ThingGroup thingGroup = new ThingGroup();

        thingGroup.setTg_name(tg_name);

        int group_id = thingsMapper.addGroup(thingGroup);

        Line line = lineMapper.oneLine(lineName);

        Station station = stationMapper.findByNameAndLineId(stationName,line.getId());


        stationMapper.addStationGroupRelation(station.getStationId(),thingGroup.getId());

    }

    @Override
    public List<Map<String, Object>> thingGroupList() {


        List<ThingGroup> thingGroups = thingsMapper.thingGroupList();

        List<Map<String, Object>> thingGroupList = new ArrayList<>();

        for(ThingGroup thingGroup:thingGroups){

            Map<String,Object> map = new HashMap<>();

            map.put("groupName",thingGroup.getTg_name());
            StationGroupRelation stationGroupRelation = stationMapper.findStationGroupRelation(thingGroup.getId());
            Station station = stationMapper.oneStation(stationGroupRelation.getStation_id());
            map.put("stationName",station.getStationName());
            String id =Integer.toString(thingGroup.getId());

            map.put("id",id);

            List<Things> things = thingGroup.getThings();

            map.put("things",things);

            thingGroupList.add(map);

        }

        return thingGroupList;

    }

    @Override
    public void setThingGroup(String[] thingList,Integer groupId) {

        for(String tname:thingList){
            Things thing = thingsMapper.findThingByName(tname);




            thingsMapper.thingGroupRelation(thing.getId(),groupId);

        }
    }

    @Override
    public List<ThingGroup> test() {

        return thingsMapper.findAllGroup();

    }

    @Override
    public List<Things> showThingsInGroup(Integer groupId) {

        StationGroupRelation stationGroupRelation = stationMapper.findStationGroupRelation(groupId);

        return thingsMapper.allThing(stationGroupRelation.getStation_id());


    }
}
