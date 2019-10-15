package com.zl.model;

import java.util.List;

public class ThingGroup {

    private int id;
    private String tg_name;
    private List<Things> things;

    public List<Things> getThings() {
        return things;
    }

    public void setThings(List<Things> things) {
        this.things = things;
    }

    public String getTg_name() {
        return tg_name;
    }

    public void setTg_name(String tg_name) {
        this.tg_name = tg_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ThingGroup{" +
                "id=" + id +
                ", tg_name='" + tg_name + '\'' +
                ", things=" + things +
                '}';
    }
}
