package com.wu.ln.api;


import com.wu.ln.bo.R;
import com.wu.ln.exceptions.AuthorizedException;
import com.wu.ln.service.UserService;
import com.wu.ln.util.CreateR;
import com.wu.ln.vo.UserVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String sayHello(String name) {
        return userService.sayHello(name);
    }


    @GetMapping("/testError")
    public R<Integer> testError(@Valid UserVO userVO) {
        int a = 1 / userVO.getValue();
        if (userVO.getValue() == 6) {
            throw new AuthorizedException("authorized", 401);
        }
        return CreateR.createSuccessResult(a);
    }
}
