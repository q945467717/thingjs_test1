package com.zl.service;

import com.zl.model.Station;

public interface StationService {

    void addStation(int lineId,String stationName,String sceneUrl);

    void deleteStation(Integer stationId);

    void updateStation(int stationId,String newStationName,String sceneId);

    Station oneStation(int stationId);

}
