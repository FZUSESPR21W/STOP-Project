package com.fzu.rtmpdiscovery.service;

import com.fzu.rtmpdiscovery.pojo.HlsLive;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class RtmpService {
    @Autowired
    HttpClient client;
    @Value("${rtmp.status.url}")
    String statusUrl;

    @Value("${rtmp.live.url}")
    String liveUrl;

    Logger log = LoggerFactory.getLogger(RtmpService.class);

    public JSONObject getRtmpJsonObject(){
        HttpGet httpGet = new HttpGet(statusUrl);
        try {
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String res = EntityUtils.toString(entity, "utf-8");
            JSONObject jsonObject = XML.toJSONObject(res);
            return jsonObject;
        } catch (IOException e) {
            log.error("连接rtmp服务："+ statusUrl +" 失败");
            e.printStackTrace();
        }
        return null;
    }
    public Map<String,Object> getRtmpInfo(){
        return  getRtmpJsonObject().toMap();

    }
    public List<HlsLive> getHlsList(){
        JSONObject jsonObject = getRtmpJsonObject();
        List<HlsLive> resList = new ArrayList<>();
        try {
            jsonObject=jsonObject.getJSONObject("rtmp").getJSONObject("server");
            JSONArray apps = jsonObject.getJSONArray("application");
            List<Object> appList=apps.toList();
            for (Object appItem : appList) {
                String appName = (String) ((Map) appItem).get("name");
                HashMap live = (HashMap) ((Map) appItem).get("live");
                Object streams = live.get("stream");
                if(streams==null)continue;
                List<Object>streamList;
                if(streams.getClass()==HashMap.class){
                    streamList= Collections.singletonList(streams);
                }else{
                    streamList= (List<Object>) streams;
                }
                for (Object streamItem : streamList) {
                    String liveName = (String) ((Map) streamItem).get("name");
                    HashMap meta = (HashMap) ((Map) streamItem).get("meta");
                    HashMap video = (HashMap) meta.get("video");
                    String height = ((Integer) video.get("height")).toString();
                    String width = ((Integer) video.get("width")).toString();
                    resList.add(new HlsLive(appName,liveName,height,width,liveUrl+appName+"/"+liveName+".m3u8"));
                }
            }
        }catch (NullPointerException e){
            log.error("xml无法解析为json");
            e.printStackTrace();
        }
        return resList;
    }
    public Map getHlsLiveMap(){
        List<HlsLive> hlsList = getHlsList();
        Map<String, Object> res = new HashMap<>();
        for (HlsLive hlsLive : hlsList) {
            res.put(hlsLive.getLiveName(),hlsLive);
        }
        return res;
    }
}
