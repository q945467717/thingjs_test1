package com.zl.model;

import java.util.Date;

public class ThingJsUser {

    private int id;
    private String cardId;

    private Date time;
    private String machineId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cardId=" + cardId +
                ", time=" + time +
                ", machineId=" + machineId +
                '}';
    }
}
