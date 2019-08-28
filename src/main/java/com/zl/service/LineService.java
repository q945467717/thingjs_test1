package com.zl.service;

import com.zl.model.Line;

import java.util.List;

public interface LineService {

    void addLine(String lineName);

    List<Line> lineList();

    void deleteLine(String id);

    Line oneLine(String lineName);

    void updateLine(int id,String newlineName);

}
