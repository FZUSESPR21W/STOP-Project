package com.fzu.rtmpdiscovery.pojo;

public class HlsLive implements Comparable {
    String appName;
    String liveName;
    String url;
    String height;
    String width;

    public HlsLive(){}
    public HlsLive(String appName, String liveName, String height, String width, String url) {
        this.appName = appName;
        this.liveName = liveName;
        this.url = url;
        this.height = height;
        this.width = width;
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

    @Override
    public int compareTo(Object o) {
        HlsLive hlsLive = (HlsLive) o;
        if(this.appName.compareTo(hlsLive.appName)==0&&
            this.liveName.compareTo(hlsLive.liveName)==0&&
            this.url.compareTo(hlsLive.url)==0&&
            this.height.compareTo(hlsLive.height)==0&&
            this.width.compareTo(hlsLive.width)==0){
            return 0;
        }
        return 1;
    }
}
