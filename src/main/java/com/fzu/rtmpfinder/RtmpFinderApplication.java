package com.fzu.rtmpfinder;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RtmpFinderApplication {
    @Bean
    public HttpClient getHttpClient(){
        HttpClient client = HttpClientBuilder.create().build();
        return client;
    }

    public static void main(String[] args) {
        SpringApplication.run(RtmpFinderApplication.class, args);
    }

}
