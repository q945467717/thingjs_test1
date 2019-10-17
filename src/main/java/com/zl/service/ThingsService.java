package com.zl.service;

import com.zl.model.ThingGroup;
import com.zl.model.Things;

import java.util.List;
import java.util.Map;

public interface ThingsService {

    List<Things> allThing(int stationId);

    void addThing(String tid, String tname,String tposition,String tgroup,String tcamera,Integer stationId);

    void deleteThing(Integer id);

    boolean updateThing(int id,String tname,String tposition,String tid,String tgroup,String tcamera);

    Things oneThing(Integer id);

    void addThingGroup(String tg_name,String stationName);

    List<Map<String, Object>> thingGroupList();

    void setThingGroup(String[] thingList,Integer groupId);

    List<ThingGroup> test();

    List<Things> showThingsInGroup(Integer groupId);
}
