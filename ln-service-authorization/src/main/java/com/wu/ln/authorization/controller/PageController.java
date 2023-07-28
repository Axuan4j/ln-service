package com.wu.ln.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login_2";
    }

    @GetMapping("/page/access-application")
    public String accessApplication() {
        return "access_application";
    }

    @GetMapping("/page/email-login")
    public String emailLogin() {
        return "login";
    }

}
