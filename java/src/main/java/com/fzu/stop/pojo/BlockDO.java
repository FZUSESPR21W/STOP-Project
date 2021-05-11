package com.fzu.stop.pojo;

import java.util.List;

/**
 * @author 梁达毅
 */
public class BlockDO {
    Integer id;
    Integer strokeWidth;
    String strokeColor;
    String fillColor;
    Integer deviceId;
    List<PointDO> points;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(Integer strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public List<PointDO> getPoints() {
        return points;
    }

    public void setPoints(List<PointDO> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "BlockDO{" +
                "id=" + id +
                ", strokeWidth=" + strokeWidth +
                ", strokeColor='" + strokeColor + '\'' +
                ", fillColor='" + fillColor + '\'' +
                ", deviceId=" + deviceId +
                ", points=" + points +
                '}';
    }
}
