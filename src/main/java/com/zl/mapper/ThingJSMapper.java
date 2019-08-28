package com.zl.mapper;

import com.zl.model.Station;
import com.zl.model.ThingJsUser;
import com.zl.model.Things;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ThingJSMapper {

    @Insert("insert into door(cardId,time,machineId) values(#{cardId},#{time},#{machineId})")
    void addPeople(ThingJsUser thingJsUser);

    @Select("select count(*) from door where (door.machineId=#{machineId}) and to_days(time)=to_days(now())")
    int peopleNum(String machineId);

    @Select("select * from door where (door.machineId=#{machineId}) and to_days(time)=to_days(now());")
    List<ThingJsUser> peopleInfo(String machineId);

    @Select("select * from stationinfo where stationId=#{stationId}")
    Station oneStation(int stationId);

    @Select("select * from thingsinfo where StationId=#{stationId}")
    List<Things> thingList(int stationId);

}
