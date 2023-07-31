package com.wu.ln.authorization;

import com.wu.ln.annotations.EnableServiceExceptionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableGatewayCommunication
@EnableServiceExceptionHandler
@MapperScan("com.wu.ln.authorization.dao")
@EnableRedisHttpSession
public class LnServiceAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LnServiceAuthorizationApplication.class, args);
    }

}
