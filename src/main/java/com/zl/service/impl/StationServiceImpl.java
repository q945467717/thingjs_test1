package com.zl.service.impl;

import com.zl.mapper.StationMapper;
import com.zl.model.Station;
import com.zl.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationMapper stationMapper;

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

}
