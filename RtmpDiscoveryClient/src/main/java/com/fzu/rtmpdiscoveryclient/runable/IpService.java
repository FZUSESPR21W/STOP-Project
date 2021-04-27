package com.fzu.rtmpdiscoveryclient.runable;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author haora
 */
@Service
public class IpService implements Runnable{

	@Value("${rtmp.service.url}")
	String serviceUrl;
	@Autowired
	RedisTemplate<String,Object> redisTemplate;
	@Value("${rtmp.redis.ipKey}")
	String ipKey;

	Logger log= LoggerFactory.getLogger(IpService.class);
	@Override
	public void run() {
		log.info("开始获取本机ip");
		OkHttpClient okHttpClient = new OkHttpClient();
		okHttpClient.setReadTimeout(1, TimeUnit.SECONDS);
		Request request =  new Request.Builder()
				.get()
				.url(serviceUrl)
				.build();
		Response response=null;

		for(;;){
			boolean flag=true;
			try {
				Call call = okHttpClient.newCall(request);
				response= call.execute();
			} catch (IOException e) {
				log.warn("连接："+ serviceUrl +" 失败");
				flag=false;
//				e.printStackTrace();
			}
			if(flag){
				break;
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		String localIp = null;
		try {
			localIp = response.body().string();
		} catch (IOException e) {
			log.error("获取ip错误");
//			e.printStackTrace();
		}
		log.info("获取到ip为："+localIp);
		setLocalIp(localIp);
	}
	private void setLocalIp(String localIp){
		for(;;){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			redisTemplate.opsForValue().set(ipKey+localIp,localIp,9, TimeUnit.SECONDS);
		}
	}
}
