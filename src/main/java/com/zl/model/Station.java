package com.zl.model;

import java.util.List;

public class Station {

    private String stationName;
    private int stationId;
    private int lineId;
    private String sceneId;
    private List<Things> things;

    public List<Things> getThings() {
        return things;
    }

    public void setThings(List<Things> things) {
        this.things = things;
    }

    public String getSceneId() {
        return sceneId;
    }

    public void setSceneId(String sceneId) {
        this.sceneId = sceneId;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationName='" + stationName + '\'' +
                ", stationId=" + stationId +
                ", lineId=" + lineId +
                ", sceneId='" + sceneId + '\'' +
                ", things=" + things +
                '}';
    }
}
