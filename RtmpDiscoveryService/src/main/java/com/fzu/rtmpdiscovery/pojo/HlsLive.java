package com.fzu.rtmpdiscovery.pojo;

import java.util.Objects;

/**
 * @author yrc
 */
public class HlsLive{
    String appName;
    String liveName;
    String url;
    String height;
    String width;
    String ip;

    public HlsLive(){}

    public HlsLive(String appName, String liveName, String url, String height, String width, String ip) {
        this.appName = appName;
        this.liveName = liveName;
        this.url = url;
        this.height = height;
        this.width = width;
        this.ip = ip;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLiveName() {
        return liveName;
    }

    public void setLiveName(String liveName) {
        this.liveName = liveName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HlsLive hlsLive = (HlsLive) o;
        return appName.equals(hlsLive.appName) && liveName.equals(hlsLive.liveName) && url.equals(hlsLive.url) && height.equals(hlsLive.height) && width.equals(hlsLive.width) && ip.equals(hlsLive.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appName, liveName, url, height, width, ip);
    }
}
