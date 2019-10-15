package com.zl.mapper;

import com.zl.model.Line;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineMapper {

    @Insert("insert into lineinfo(lineName) values(#{lineName})")
    void addLine(String lineName);

    List<Line> lineList();

    @Delete("delete from lineinfo where lineName=#{id}")
    void deleteLine(String id);

    @Select("select * from lineinfo where lineName=#{lineName}")
    Line oneLine(String lineName);

    @Update("update lineinfo set lineName=#{newlineName} where id=#{id}")
    void updateLine(int id,String newlineName);

    @Select("select * from lineinfo where id=#{id}")
    Line findById(Integer id);

}
