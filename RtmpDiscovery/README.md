# RtmpDiscovery

一个可动态拓展的nginx rtmp流发现服务

## 具体功能

部署完毕后，可以动态注册nginx rtmp 服务器，并将服务器信息发布到指定的redis上

## 组件图

![image-20210428163321969](https://gitee.com/lin_haoran/Picgo/raw/master/img/image-20210428163321969.png)

## 配置图

![image-20210428163213463](https://gitee.com/lin_haoran/Picgo/raw/master/img/image-20210428163213463.png)

## 实现思路

### 概述

​	通过Rtmp推流发现服务客户端（rtmpDiscoveryClient）将nginx rtmp服务器的ip设置到redis中。之后Rtmp推流发现服务服务端（rtmpDiscoveryService）从redis中获取所有在线的服务器ip，通过这些ip去对应的nginx服务器轮询其rtmp流的信息。每次轮询都会将新获取到的信息与redis中已发布的信息对比，如果不相同就将*服务器变更*的信息发布到redis的channel中。

### RtmpDiscoveryClient

### RtmpDiscoveryService

## 部署方式

### Nginx服务器配置

```
http{
	server{
		listen 80;
		server_name 127.0.0.1;
		#开启rtmp模块中自带的
		location /stat {
			rtmp_stat all;
		}
		location /live{
			#Cors配置
			add_header 'Access-Control-Allow-Origin' '*';
			add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS, PUT, DELETE';
			add_header 'Access-Control-Allow-Headers' 'Content-Type';
			if ($request_method = 'OPTIONS') {
				add_header 'Access-Control-Allow-Origin' '*';
				add_header 'Access-Control-Max-Age' 1728000;
				add_header 'Content-Type' 'text/plain charset=UTF-8';
				add_header 'Content-Length' 0;
				return 204;
			}
			alias /dev/shm/hls;
			#自动生成index
			autoindex on;
			types {
				application/dash+xml mpd;
				application/vnd.apple.mpegurl m3u8;
				video/mp2t ts;
			}
			add_header Cache-Control no-cache;
		}
	}
}
rtmp{
	server{
		listen 1953;
		application rtmp{
			live on;
		}
		application hls{
			live on;
			#启用hls
			hls on;
			#缓存地址
			hls_path /dev/shm/hls/;
		}
	}
}
```

### RtmpDiscoveryClient配置

### 启动

​	直接通过java -jar启动即可

#### 自定义配置文件

```properties
#redis服务器中用来存储当前服务器ip的key前缀
rtmp.redis.ipKey=rtmp:ip:
#获取当前服务器公网ip的地址，可以使用Service服务器的部署地址的/get_client_ip方法
rtmp.service.url=http://...
#还要配置自己的redis
spring.redis.host=...
spring.redis.password=...
```

### RtmpDiscoveryService配置

### 启动

​	直接通过java -jar启动即可

#### 自定义配置文件

```properties
#获取的所有rtmp推流服务信息存储在redis中的key
rtmp.redis.key=rtmp:device
#在redis中发布服务器状态改变信息的channel
rtmp.redis.channel=deviceChannel
#获取所有在线的服务器ip的key前缀，与client的rtmp.redis.ipKey对应
rtmp.redis.ipKey=rtmp:ip:*
#还要配置自己的redis
spring.redis.host=...
spring.redis.password=...
```



