package com.wu.ln.message.service;

import com.wu.ln.message.service.fail.UserServiceFailCallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ln-user-provider", fallback = UserServiceFailCallBack.class)
@Component
public interface UserService {

    @PostMapping("/userService/sayHello")
    String sayHello(@RequestParam(name = "name") String name);
}
