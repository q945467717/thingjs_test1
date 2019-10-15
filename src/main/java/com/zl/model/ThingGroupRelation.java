package com.zl.model;

public class ThingGroupRelation {

    private int thing_id;
    private int group_id;

    public int getThing_id() {
        return thing_id;
    }

    public void setThing_id(int thing_id) {
        this.thing_id = thing_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    @Override
    public String toString() {
        return "ThingGroupRelation{" +
                "thing_id=" + thing_id +
                ", group_id=" + group_id +
                '}';
    }
}
