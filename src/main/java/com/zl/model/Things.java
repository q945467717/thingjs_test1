package com.zl.model;

public class Things {

    private int id;
    private String tname;
    private String tposition;
    private String tid;
    private String tgroup;
    private int StationId;
    private String tcamera;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTposition() {
        return tposition;
    }

    public void setTposition(String tposition) {
        this.tposition = tposition;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTgroup() {
        return tgroup;
    }

    public void setTgroup(String tgroup) {
        this.tgroup = tgroup;
    }

    public int getStationId() {
        return StationId;
    }

    public void setStationId(int stationId) {
        StationId = stationId;
    }

    public String getTcamera() {
        return tcamera;
    }

    public void setTcamera(String tcamera) {
        this.tcamera = tcamera;
    }

    @Override
    public String toString() {
        return "Things{" +
                "id=" + id +
                ", tname='" + tname + '\'' +
                ", tposition='" + tposition + '\'' +
                ", tid='" + tid + '\'' +
                ", tgroup='" + tgroup + '\'' +
                ", StationId=" + StationId +
                ", tcamera='" + tcamera + '\'' +
                '}';
    }
}
