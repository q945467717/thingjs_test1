package com.zl.service;

import com.zl.model.Station;
import com.zl.model.StationGroupRelation;

import java.util.List;

public interface StationService {

    void addStation(int lineId,String stationName,String sceneUrl);

    void deleteStation(Integer stationId);

    boolean updateStation(int stationId,String newStationName,String sceneId);

    Station oneStation(Integer stationId);

    List<Station> adminStations(int userId);

    List<Station> selectLineStation(Integer lineId);

}
