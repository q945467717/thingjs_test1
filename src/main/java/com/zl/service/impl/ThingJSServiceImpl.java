package com.zl.service.impl;

import com.zl.mapper.ThingJSMapper;
import com.zl.model.Station;
import com.zl.model.ThingJsUser;
import com.zl.model.Things;
import com.zl.service.ThingJSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThingJSServiceImpl implements ThingJSService {

    @Autowired
    private ThingJSMapper thingJSMapper;

    @Override
    public void addPeople(ThingJsUser thingJsUser) {
        thingJSMapper.addPeople(thingJsUser);
    }

    @Override
    public int peopleNum(String machineId) {
        return thingJSMapper.peopleNum(machineId);
    }

    @Override
    public List<ThingJsUser> peopleInfo(String machineId) {
        return thingJSMapper.peopleInfo(machineId);
    }

    @Override
    public Station oneStation(int stationId) {
        return thingJSMapper.oneStation(stationId);
    }

    @Override
    public List<Things> thingList(Integer stationId) {
        return thingJSMapper.thingList(stationId);
    }
}
