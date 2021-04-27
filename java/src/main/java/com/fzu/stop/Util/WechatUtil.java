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
        JSONObject jsonObject= (JSONObject) JSON.parse(response.body().toString());
        return  jsonObject;
    }
}
