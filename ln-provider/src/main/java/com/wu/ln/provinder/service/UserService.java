package com.wu.ln.provinder.service;

import com.wu.ln.bo.R;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService {

    R<String> sayHello(@RequestParam String name);
}
