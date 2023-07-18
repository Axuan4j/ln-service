package com.wu.ln.provinder.api;


import com.wu.ln.bo.R;
import com.wu.ln.provinder.service.UserService;
import com.wu.ln.util.CreateR;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userService")
public class UserServiceApi {

    private final UserService userService;

    public UserServiceApi(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sayHello")
    public R<String> sayHello(String name) {
        return userService.sayHello(name);
    }

    @GetMapping("/testError")
    public R<Integer> testError(@Valid @DecimalMax(value = "10.0") @RequestParam Integer value) {
        int a = 1 / value;
        return CreateR.createSuccessResult(a);
    }
}
