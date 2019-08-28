package com.zl.model;

import java.util.List;

public class Line {

    private String lineName;

    private int id;

    private List<Station> station;

    public String getLineName() {
        return lineName;
    }

    public List<Station> getStation() {
        return station;
    }

    public void setStation(List<Station> station) {
        this.station = station;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Line{" +
                "lineName='" + lineName + '\'' +
                ", id=" + id +
                ", station=" + station +
                '}';
    }
}
