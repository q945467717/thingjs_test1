package com.zl.service;

import com.zl.model.Station;
import com.zl.model.ThingJsUser;
import com.zl.model.Things;

import java.util.List;

public interface ThingJSService {

    void addPeople(ThingJsUser thingJsUser);

    int peopleNum(String machineId);

    List<ThingJsUser> peopleInfo(String machineId);

    Station oneStation(int stationId);

    List<Things> thingList(int stationId);

}
