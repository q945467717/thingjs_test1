package com.zl.service.impl;

import com.zl.mapper.StationMapper;
import com.zl.mapper.SysUserMapper;
import com.zl.model.Station;
import com.zl.model.vo.SysUserStation;
import com.zl.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationMapper stationMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void addStation(int lineId, String stationName, String sceneUrl) {

        stationMapper.addStation(lineId,stationName,sceneUrl);
    }

    @Override
    public void deleteStation(Integer stationId) {

        stationMapper.deleteStation(stationId);
    }

    @Override
    public void updateStation(int stationId, String newStationName, String sceneId) {
        stationMapper.updateStation(stationId,newStationName,sceneId);

    }

    @Override
    public Station oneStation(int stationId) {
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
    public void deleteStationRole(int userId, Integer stationId) {
        sysUserMapper.deleteStationRole(userId,stationId);
    }

}
