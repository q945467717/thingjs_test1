package com.zl.service.impl;

import com.zl.mapper.LineMapper;
import com.zl.mapper.StationMapper;
import com.zl.mapper.SysUserMapper;
import com.zl.mapper.ThingsMapper;
import com.zl.model.Line;
import com.zl.model.Station;
import com.zl.model.StationGroupRelation;
import com.zl.model.Things;
import com.zl.model.vo.SysUserStation;
import com.zl.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ThingsMapper thingsMapper;

    @Override
    public void addStation(int lineId, String stationName, String sceneId) {

        Station station = new Station();

        station.setStationName(stationName);
        station.setLineId(lineId);
        station.setSceneId(sceneId);

        int stationId=stationMapper.addStation(station);

        List<Station> stationList = stationMapper.findStationListByName(stationName);

        for(Station station1:stationList){

            stationMapper.updateStation(station1.getStationId(),stationName,sceneId);

            if(station.getStationId()==station1.getStationId()){
                break;
            }
            if(station.getStationId()!=station1.getStationId()){

                List<Things> thingsList = thingsMapper.allThing(station1.getStationId());

                for(Things things:thingsList){

                    Things things1 = new Things();

                    things1.setTid(things.getTid());
                    things1.setTname(things.getTname());
                    things1.setTposition(things.getTposition());
                    things1.setTgroup(things.getTgroup());
                    things1.setStationId(station.getStationId());
                    things1.setTcamera(things.getTcamera());
                    thingsMapper.addThing(things1);
                }
                break;
            }
        }


    }

    @Override
    @Transactional
    public void deleteStation(Integer stationId) {

        stationMapper.deleteStation(stationId);

        sysUserMapper.deleteUserStationRoles(stationId);
    }

    @Override
    public boolean updateStation(int stationId, String newStationName, String sceneId) {


        Station station = stationMapper.oneStation(stationId);

//        List<Station> stations = stationMapper.findStationListByName(station.getStationName());
//
//        for(Station station2:stations){
//
//            List<Station> list = stationMapper.findStationNoId(station.getStationId(),station2.getLineId());
//
//            for(Station station1:list){
//                if(station1.getStationName().equals(newStationName)){
//                    return false;
//                }
//            }
//
//            stationMapper.updateStation(station2.getStationId(),newStationName,sceneId);
//
//        }

        List<Station> stationList = stationMapper.findStationListByName(station.getStationName());

        for(Station station1:stationList){

            List<Station> stationNoIdList = stationMapper.findStationNoId(station1.getStationName());

            for(Station station2:stationNoIdList){
                if(station2.getStationName().equals(newStationName)){
                    return false;
                }
            }

            List<Station> stationListByName = stationMapper.findStationListByName(station1.getStationName());

            for(Station station2:stationListByName){
                stationMapper.updateStation(station2.getStationId(),newStationName,sceneId);
            }
            break;
        }

        return true;

    }

    @Override
    public Station oneStation(Integer stationId) {
        return stationMapper.oneStation(stationId);
    }

    @Override
    public List<Station> adminStations(int userId) {

        List<SysUserStation> sysUserStations = sysUserMapper.adminStations(userId);

        List<Station> stations = new ArrayList<>();

        for(SysUserStation sysUserStation:sysUserStations){

            Station station = stationMapper.oneStation(sysUserStation.getStation_id());
            stations.add(station);

        }

        return stations;
    }

    @Override
    public List<Station> selectLineStation(Integer lineId) {
        return stationMapper.findStationByLineId(lineId);
    }


}
