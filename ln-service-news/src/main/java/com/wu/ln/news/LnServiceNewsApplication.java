package com.wu.ln.news;

import com.wu.ln.annotations.EnableGatewayCommunication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGatewayCommunication
public class LnServiceNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LnServiceNewsApplication.class, args);
    }

}
