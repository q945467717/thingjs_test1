package com.zl.service;

import com.zl.model.Station;

import java.util.List;

public interface StationService {

    void addStation(int lineId,String stationName,String sceneUrl);

    void deleteStation(Integer stationId);

    void updateStation(int stationId,String newStationName,String sceneId);

    Station oneStation(Integer stationId);

    List<Station> adminStations(int userId);

    void  deleteStationRole(int userId,Integer stationId);

}
