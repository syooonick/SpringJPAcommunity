package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUpController {

    @RequestMapping("/signup")
    public String signupForm() {
        return "signup_form";
    }
}
