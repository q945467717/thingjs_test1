package com.zl.service.impl;

import com.zl.mapper.LineMapper;
import com.zl.model.Line;
import com.zl.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineServiceImpl implements LineService {

    @Autowired
    private LineMapper lineMapper;


    @Override
    public void addLine(String lineName) {

        lineMapper.addLine(lineName);
    }

    @Override
    public List<Line> lineList() {

        return lineMapper.lineList();
    }

    @Override
    public void deleteLine(String id) {

        lineMapper.deleteLine(id);
    }

    @Override
    public Line oneLine(String lineName) {
        return lineMapper.oneLine(lineName);
    }

    /**
     * 修改线路名称
     * @param id
     * @param newlineName
     */
    @Override
    public void updateLine(int id, String newlineName) {
        lineMapper.updateLine(id,newlineName);
    }
}
