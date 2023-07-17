package com.wu.ln.provinder;

import com.wu.ln.annotations.EnableGatewayCommunication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGatewayCommunication
public class LnNewsServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LnNewsServiceProviderApplication.class, args);
    }

}
