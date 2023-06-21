package com.study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j

public class LoginController {

    @RequestMapping("/login")
    public String signinForm() {
        return "signin";
    }

//    @RequestMapping("/signup")
//    public String signupForm() {
//        return "signup";
//    }
}


//    @Autowired
//    private LoginService loginService;
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String loginId(@ModelAttribute User user) {
//        if(loginService.login(user)){
//            return "redirect:/";
//        }
//        return "login";
//    }
