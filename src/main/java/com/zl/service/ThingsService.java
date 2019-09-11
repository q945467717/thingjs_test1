package com.zl.service;

import com.zl.model.Things;

import java.util.List;

public interface ThingsService {

    List<Things> allThing(int stationId);

    void addThing(Things things);

    void deleteThing(Integer id);

    void updateThing(int id,String tname,String tposition,String tid,String tgroup,String tcamera);

    Things oneThing(Integer id);
}
