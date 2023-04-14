package com.study.board.controller;

import com.study.board.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j

public class LoginController {

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
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
}