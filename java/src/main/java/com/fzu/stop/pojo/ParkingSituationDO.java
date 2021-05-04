package com.fzu.stop.pojo;

import java.sql.Timestamp;

/**
 * @author 宋家锐
 */
public class ParkingSituationDO {
    String name;
    String time;
    String value;

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

    @Override
    public String toString(){
        return "ParkingSituationDO{" + "\n" +
                "\tname = " + name + "\n" +
                "\ttime = " + time + "\n" +
                "\tvalue = " + value + "\n" +
                "}\n";
    }
}
