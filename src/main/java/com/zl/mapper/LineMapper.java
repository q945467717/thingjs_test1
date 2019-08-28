package com.zl.mapper;

import com.zl.model.Line;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
}
