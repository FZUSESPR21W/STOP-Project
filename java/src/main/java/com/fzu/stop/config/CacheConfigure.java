package com.fzu.stop.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.HashMap;

/**
 * @author 林浩然
 */
@Configuration
public class CacheConfigure {
	/**
	 * 用代码配置缓存器
	 * @param connectionFactory redis连接工厂
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory){
		HashMap<String , RedisCacheConfiguration> configMap = new HashMap<>();
		//为不同的数据设置不同的ttl，在性能和数据时效性之间保持一个平衡
//		configMap.put("similarWord",getMyConfigure().entryTtl(Duration.ofMinutes(20)));
//		configMap.put("searchPaper",getMyConfigure().entryTtl(Duration.ofMinutes(20)));
//		configMap.put("top10",getMyConfigure().entryTtl(Duration.ofDays(1)));
//		configMap.put("sunburst",getMyConfigure().entryTtl(Duration.ofDays(1)));
		//用构造器构造CacheManager
		RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(getMyConfigure())
				.withInitialCacheConfigurations(configMap)
				.build();
		return redisCacheManager;
	}

	/**
	 * 返回一个自定义的默认配置
	 * @return RedisCacheConfiguration类
	 */
	private RedisCacheConfiguration getMyConfigure(){
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		//将存入redis缓存时使用的序列化器改为支持json的序列化器，提升缓存数据的可读性
		config=config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()));
		config=config.entryTtl(Duration.ofMinutes(5));
		return config;
	}
}
