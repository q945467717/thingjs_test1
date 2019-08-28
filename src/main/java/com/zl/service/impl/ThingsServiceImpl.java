package com.zl.service.impl;

import com.zl.mapper.ThingsMapper;
import com.zl.model.Things;
import com.zl.service.ThingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThingsServiceImpl implements ThingsService {

    @Autowired
    private ThingsMapper thingsMapper;

    @Override
    public List<Things> allThing(int stationId) {
        return thingsMapper.allThing(stationId);
    }

    @Override
    public void addThing(Things things) {
        thingsMapper.addThing(things);
    }

    @Override
    public void deleteThing(Integer id) {
        thingsMapper.deleteThing(id);
    }

    @Override
    public void updateThing(int id,String tname,String tposition,String tid,String tgroup,String tcamera) {
        thingsMapper.updateThing(id,tname,tposition,tid,tgroup,tcamera);
    }

    @Override
    public Things oneThing(int id) {
        return thingsMapper.oneThing(id);
    }
}
