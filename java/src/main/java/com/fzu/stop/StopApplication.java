package com.fzu.stop;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.fzu.stop.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
public class StopApplication {
	public static void main(String[] args) {
		SpringApplication.run(StopApplication.class, args);

	}
	@Autowired
	AdminDao adminDao;
	@PostConstruct
	public void init(){

	}

}
