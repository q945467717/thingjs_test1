package com.zl.model;

public class StationGroupRelation {

    private int station_id;
    private int group_id;

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "StationGroupRelation{" +
                "station_id=" + station_id +
                ", group_id=" + group_id +
                '}';
    }
}
