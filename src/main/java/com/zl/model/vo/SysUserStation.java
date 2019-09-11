package com.zl.model.vo;

import com.zl.model.Station;
import com.zl.model.SysUser;

import java.util.List;

public class SysUserStation {

    private int user_id;
    private int station_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStation_id() {
        return station_id;
    }

    public void setStation_id(int station_id) {
        this.station_id = station_id;
    }

    @Override
    public String toString() {
        return "SysUserStation{" +
                "user_id=" + user_id +
                ", station_id=" + station_id +
                '}';
    }
}
