# RtmpFinder
一个Nginx rtmp直播流服务发现者

## Nginx服务器配置

```
http{
	server{
		listen 80;
		server_name 127.0.0.1;
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

