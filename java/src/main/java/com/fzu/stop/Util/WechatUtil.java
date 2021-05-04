package com.fzu.stop.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;


import java.io.IOException;


/**
 * 微信Util
 * @author 梁达毅
 */
public class WechatUtil {
    private static final String APPID = "wxd26114514c386951";
    private static final String SECRET = "1b8c5d5017ab11f4f4f24e10ea5b1475";
    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String GRANT_TYPE="client_credential";

    public static JSONObject getCode2Session(String code) throws IOException {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("appid",APPID)
                .add("secret",SECRET)
                .add("js_code",code)
                .add("grant_type",AUTHORIZATION_CODE).build();
        Request request=new Request.Builder()
                .url(requestUrl)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response=call.execute();
        JSONObject jsonObject= (JSONObject) JSON.parse(response.body().string());
        return  jsonObject;
    }

    public static JSONObject getAccessToken() throws IOException {
        String requestUrl ="https://api.weixin.qq.com/cgi-bin/token";
        OkHttpClient okHttpClient=new OkHttpClient();
        HttpUrl.Builder httpBuilder=HttpUrl.parse(requestUrl).newBuilder();
        httpBuilder.addQueryParameter("grant_type",GRANT_TYPE);
        httpBuilder.addQueryParameter("appid",APPID);
        httpBuilder.addQueryParameter("secret",SECRET);
        Request request=new Request.Builder()
                .url(httpBuilder.build())
                .build();
        Response response = okHttpClient.newCall(request).execute();
        JSONObject jsonObject= (JSONObject) JSON.parse(response.body().string());
        return jsonObject;
    }

    public static JSONObject getUserPortrait(String begin_date,String end_date) throws IOException {
        String accessToken =  WechatUtil.getAccessToken().getString("access_token");
        String requestUrl="https://api.weixin.qq.com/datacube/getweanalysisappiduserportrait";
        OkHttpClient okHttpClient=new OkHttpClient();
        HttpUrl.Builder httpBuilder=HttpUrl.parse(requestUrl).newBuilder();
        httpBuilder.addQueryParameter("access_token",accessToken);
        JSONObject data=new JSONObject();
        data.put("begin_date",begin_date);
        data.put("end_date",end_date);
        /*需要将数据格式设置为application/json,否则会出现47001----data format error rid: 6084cf6b-7c22f070-136ea237*/
        MediaType mediaType=MediaType.parse("application/json;charset=utf-8");
        RequestBody body=RequestBody.create(mediaType,String.valueOf(data));
        Request request=new Request.Builder()
                .url(httpBuilder    .build())
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response=call.execute();
        JSONObject jsonObject= (JSONObject) JSON.parse(response.body().string());
        return jsonObject;
    }





}
