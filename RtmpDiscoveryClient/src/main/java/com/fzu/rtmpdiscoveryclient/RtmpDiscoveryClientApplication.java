package com.fzu.rtmpdiscoveryclient;

import com.fzu.rtmpdiscoveryclient.runable.IpService;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在redis中设置key来注册服务器上线
 * @author yrc
 */
@SpringBootApplication
@EnableAsync
public class RtmpDiscoveryClientApplication {

	Logger log = LoggerFactory.getLogger(RtmpDiscoveryClientApplication.class);
	@Autowired
	IpService ipService;


	public static void main(String[] args) {
		SpringApplication.run(RtmpDiscoveryClientApplication.class, args);
	}

	/**
	 * 启动服务
	 */
	@PostConstruct
	public void init(){
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1
				,3
				, 10
				, TimeUnit.SECONDS
				, new ArrayBlockingQueue<>(2)
				, new DefaultThreadFactory("test"));
		threadPool.execute(ipService);

	}


}
