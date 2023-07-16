package com.wu.ln;

import com.wu.ln.annotations.EnableGatewayCommunication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableGatewayCommunication
public class LnServiceConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(LnServiceConsumerApplication.class, args);
    }
}
