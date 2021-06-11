package com.fzu.stop.pojo;

import java.sql.Timestamp;

/**
 * @author 宋家锐
 */
public class ParkingSituationDO {
    String id;
    String name;
    String time;
    String value;
    String volume;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "ParkingSituationDO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", value='" + value + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}
