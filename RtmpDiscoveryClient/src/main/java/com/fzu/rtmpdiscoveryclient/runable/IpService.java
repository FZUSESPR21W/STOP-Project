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
 * 获取服务器公网ip并在redis中设置当前服务器的ip
 * service端是通过在redis服务器中查询指定前缀的key来获取当前在线的服务器
 * 如果在一段时间内client没有在redis中设置key，就视为服务器下线
 * @author yrc
 */
@Service
public class IpService implements Runnable{

	Logger log= LoggerFactory.getLogger(IpService.class);

	//获取ip的服务器url
	@Value("${rtmp.service.url}")
	String serviceUrl;
	//发布的redis key前缀
	@Value("${rtmp.redis.ipKey}")
	String ipKey;
	@Autowired
	RedisTemplate<String,Object> redisTemplate;

	@Override
	public void run() {
		log.info("开始获取本机ip");
		//构建http连接
		OkHttpClient okHttpClient = new OkHttpClient();
		okHttpClient.setReadTimeout(1, TimeUnit.SECONDS);
		Request request =  new Request.Builder()
				.get()
				.url(serviceUrl)
				.build();
		Response response=null;

		//循环请求ip服务器，直到获取成功
		for(;;){
			boolean flag=true;
			try {
				Call call = okHttpClient.newCall(request);
				response= call.execute();
			} catch (IOException e) {
				log.warn("连接："+ serviceUrl +" 失败");
				flag=false;
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
		//从body获取ip
		String localIp = null;
		try {
			localIp = response.body().string();
		} catch (IOException e) {
			log.error("获取ip错误");
		}
		log.info("获取到ip为："+localIp);
		setLocalIp(localIp);
	}
	//每过一段时间，在redis中设置本机ip来实现注册
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
