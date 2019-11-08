package com.zl.mapper;

import com.zl.model.ThingGroup;
import com.zl.model.ThingGroupRelation;
import com.zl.model.Things;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThingsMapper {

    @Select("select * from thingsinfo where StationId = #{stationId}")
    List<Things> allThing(int stationId);

    @Select("select * from thingsinfo where StationId = #{stationId} and tid=#{tid}")
    List<Things> allThingByTid(int stationId,String tid);

    @Select("select * from thingsinfo where StationId = #{stationId} and tid!=#{tid}")
    List<Things> allThingNoTid(int stationId,String tid);

    @Insert("insert into thingsinfo(tname,tposition,tid,tgroup,StationId,tcamera) values(#{tname},#{tposition},#{tid},#{tgroup},#{StationId},#{tcamera})")
    void addThing(Things things);

    @Delete("delete from thingsinfo where StationId=#{StationId} and tid=#{tid}")
    void deleteThing(Integer StationId,String tid);

    @Update("update thingsinfo set tname=#{tname},tposition=#{tposition},tid=#{tid},tgroup=#{tgroup},tcamera=#{tcamera} where id=#{id}")
    void updateThing(int id,String tname,String tposition,String tid,String tgroup,String tcamera);

    @Select("select * from thingsinfo where id =#{id}")
    Things oneThing(Integer id);

    //@Insert("insert into thing_group(tg_name) value (#{tg_name})")
    int addGroup(ThingGroup thingGroup);

    @Insert("insert into thing_group_relation(thing_id,group_id) values(#{thingId},#{groupId})")
    void thingGroupRelation(Integer thingId,Integer groupId);

    @Select("select * from thing_group ")
    List<ThingGroup> thingGroupList();

    @Select("select * from thingsinfo where tname = #{tname}")
    Things findThingByName(String tname);

    @Select("select * from thingsinfo where id = #{id}")
    Things findThingById(Integer id);

   // @Select("select * from thing_group")
    List<ThingGroup> findAllGroup();

    @Select("select * from thing_group_relation where group_id=#{groupId}")
    List<ThingGroupRelation> findRelationByGroupId(Integer groupId);

    @Select("select count(*) from thingsinfo where tid=#{tid} and StationId=#{stationId}")
    int count(String tid,String stationId);



}
