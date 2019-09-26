package com.zl.model;

public class ThingGroup {


    private int id;
    private String tg_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return tg_name;
    }

    public void setGroupName(String groupName) {
        this.tg_name = groupName;
    }

    @Override
    public String toString() {
        return "ThingGroup{" +
                "id=" + id +
                ", groupName='" + tg_name + '\'' +
                '}';
    }
}
