package com.zl.mapper;

import com.zl.model.Station;
import com.zl.model.StationGroupRelation;
import com.zl.model.vo.SysUserStation;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationMapper {

    //@Insert("insert into stationinfo(stationName,lineId,sceneId) values(#{stationName},#{lineId},#{sceneId})")
    int addStation(Station station);

    @Delete("delete from stationinfo where stationId=#{stationId}")
    void deleteStation(Integer stationId);

    @Update("update stationinfo set stationName=#{newStationName},sceneId=#{sceneId} where stationId=#{stationId}")
    void updateStation(int stationId,String newStationName,String sceneId);

    //@Select("select * from stationinfo where stationId=#{stationId}")
    Station oneStation(Integer stationId);

    @Select("select * from stationinfo where stationName=#{stationName}")
    Station oneStationByName(String stationName);

    @Select("select * from user_station where user_id=#{userId}")
    List<SysUserStation> oneSysUserStation(int userId);

    @Select("select * from stationinfo")
    List<Station> allStation();

    @Select("select * from stationinfo where lineId=#{lineId}")
    List<Station> findStationByLineId(Integer lineId);

    @Insert("insert into station_group_relation(station_id,group_id) values(#{station_id},#{group_id})")
    void addStationGroupRelation(Integer station_id,Integer group_id);

    @Select("select * from station_group_relation where group_id=#{group_id}")
    List<StationGroupRelation> findStationGroupRelationList(Integer group_id);

    @Select("select * from station_group_relation where group_id=#{group_id}")
    StationGroupRelation findStationGroupRelation(Integer group_id);

    @Select("select * from stationinfo where stationName = '${stationName}' and lineId=#{lineId}")
    Station findByNameAndLineId(String stationName,Integer lineId);

    @Select("select * from stationinfo where stationName = #{stationName}")
    List<Station> findStationListByName(String stationName);

    @Select("select * from stationinfo where stationName != #{stationName}")
    List<Station> findStationNoId(String stationName);
}
