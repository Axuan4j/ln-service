package com.wu.ln.service.fail;

import com.wu.ln.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFailCallBack implements UserService {

    @Override
    public String sayHello(String name) {
        return "fail back response: " + name + "!";
    }
}
