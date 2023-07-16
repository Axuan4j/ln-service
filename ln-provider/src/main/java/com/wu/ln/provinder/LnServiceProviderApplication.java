package com.wu.ln.provinder;

import com.wu.ln.annotations.EnableGatewayCommunication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients({"com.wu.ln.provinder.service"})
@EnableGatewayCommunication
public class LnServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(LnServiceProviderApplication.class, args);
    }

}
