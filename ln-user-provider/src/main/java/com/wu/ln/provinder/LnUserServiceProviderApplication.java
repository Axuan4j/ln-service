package com.wu.ln.provinder;

import com.wu.ln.annotations.EnableGatewayCommunication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGatewayCommunication
public class LnUserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LnUserServiceProviderApplication.class, args);
    }

}
