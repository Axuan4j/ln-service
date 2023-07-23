package com.wu.ln.user;

import com.wu.ln.annotations.EnableGatewayCommunication;
import com.wu.ln.annotations.EnableServiceExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGatewayCommunication
@EnableServiceExceptionHandler
public class LnServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(LnServiceUserApplication.class, args);
    }

}
