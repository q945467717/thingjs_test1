package com.zl.mapper;

import com.zl.model.Station;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StationMapper {

    @Insert("insert into stationinfo(stationName,lineId,sceneId) values(#{stationName},#{lineId},#{sceneUrl})")
    void  addStation(int lineId ,String stationName,String sceneUrl);

    @Delete("delete from stationinfo where stationId=#{stationId}")
    void deleteStation(Integer stationId);

    @Update("update stationinfo set stationName=#{newStationName},sceneId=#{sceneId} where stationId=#{stationId}")
    void updateStation(int stationId,String newStationName,String sceneId);

    @Select("select * from stationinfo where stationId=#{stationId}")
    Station oneStation(int stationId);


}
