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
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 从nginx rtmp推流服务器获取rtmp推流列表
 * @author yrc
 */
@Service
public class RtmpService {
    @Autowired
    HttpClient client;
    @Autowired
    Set<String> ipSet;

    Logger log = LoggerFactory.getLogger(RtmpService.class);

    private String getStatusUrl(String ip){
        return "http://"+ip+"/stat";
    }
    private String getLiveUrl(String ip,String appName,String liveName){
        return "http://"+ip+"/"+appName+"/"+liveName+".m3u8";
    }
    /**
     * 将xml转换成JSONObject
     * @return 返回一个从服务器获取的xml对应的JSONObject
     */
    private JSONObject getRtmpJsonObject(String ip){
    	String statusUrl=getStatusUrl(ip);
        HttpGet httpGet = new HttpGet(statusUrl);
        try {
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String res = EntityUtils.toString(entity, "utf-8");
            JSONObject jsonObject = XML.toJSONObject(res);
            return jsonObject;
        } catch (IOException e) {
            log.error("连接rtmp服务："+ statusUrl +" 失败");
//            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return 原始JSONObject转换成map
     */
    public Map<String,Object> getRtmpInfo(){
        HashMap<String, Object> res = new HashMap<>();
        for (String ip : ipSet) {
        	res.put(ip,getRtmpJsonObject(ip).toMap());
        }
        return  res;

    }

    public List<HlsLive> getAllHlsList(){
        ArrayList<HlsLive> res = new ArrayList<>();
        for (String ip : ipSet) {
            List<HlsLive> hlsList = getHlsList(ip);
            res.addAll(hlsList);
        }
        return res;
    }
    /**
     * 将从服务器获取的xml转换成HlsLive
     * @return HlsLive类的列表
     */
    private List<HlsLive> getHlsList(String ip){
        JSONObject jsonObject = getRtmpJsonObject(ip);
        List<HlsLive> resList = new ArrayList<>();
        try {
            jsonObject=jsonObject.getJSONObject("rtmp").getJSONObject("server");
            //获取application数组
            if(!jsonObject.has("application")){
            	return resList;
            }
            List<Object> appList=new ArrayList<>();
            Object application = jsonObject.get("application");
            if(application.getClass()==JSONObject.class){
                appList.add(((JSONObject)application).toMap());
            }else{
                JSONArray apps = jsonObject.getJSONArray("application");
                appList=apps.toList();
            }
            //遍历application数组
            for (Object appItem : appList) {
                String appName = (String) ((Map) appItem).get("name");
                HashMap live = (HashMap) ((Map) appItem).get("live");
                Object streams = live.get("stream");
                if(streams==null) {
                    continue;
                }
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
                    resList.add(new HlsLive(appName,liveName,height,width,getLiveUrl(ip,appName,liveName),ip));
                }
            }
        }catch (NullPointerException e){
            log.error("xml无法解析为json");
//            e.printStackTrace();
        }
        return resList;
    }

    /**
     * 将HlsLive列表转换成map
     * @return HlsLive的map，key为hlsLive中的liveName，value为HlsLive类
     */
    public Map getHlsLiveMap(){
        List<HlsLive> hlsList = getAllHlsList();
        Map<String, Object> res = new HashMap<>();
        for (HlsLive hlsLive : hlsList) {
            res.put(hlsLive.getIp()+":"+hlsLive.getLiveName(),hlsLive);
        }
        return res;
    }
}
