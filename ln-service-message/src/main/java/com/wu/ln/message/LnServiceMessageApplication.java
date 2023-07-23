package com.wu.ln.message;

import com.wu.ln.annotations.EnableGatewayCommunication;
import com.wu.ln.annotations.EnableServiceExceptionHandler;
import com.wu.ln.annotations.EnableValidAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableGatewayCommunication
@EnableValidAutoConfiguration
@EnableServiceExceptionHandler
public class LnServiceMessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(LnServiceMessageApplication.class, args);
    }
}
