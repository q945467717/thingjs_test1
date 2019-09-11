package com.zl.mapper;

import com.zl.model.Things;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ThingsMapper {

    @Select("select * from thingsinfo where StationId = #{stationId}")
    List<Things> allThing(int stationId);

    @Insert("insert into thingsinfo(tname,tposition,tid,tgroup,StationId,tcamera) values(#{tname},#{tposition},#{tid},#{tgroup},#{StationId},#{tcamera})")
    void addThing(Things things);

    @Delete("delete from thingsinfo where id=#{id}")
    void deleteThing(Integer id);

    @Update("update thingsinfo set tname=#{tname},tposition=#{tposition},tid=#{tid},tgroup=#{tgroup},tcamera=#{tcamera} where id=#{id}")
    void updateThing(int id,String tname,String tposition,String tid,String tgroup,String tcamera);

    @Select("select * from thingsinfo where id =#{id}")
    Things oneThing(Integer id);
}
