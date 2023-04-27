//package com.study.board.controller;
//
//
//import com.study.board.entity.User;
//import com.study.board.service.AuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import javax.servlet.http.HttpSession;
//
//@RequiredArgsConstructor
//@Controller
//public class AuthController {
//
//    private final AuthService authService;
//
//    @GetMapping("/signin")
//    public String SigninForm() {
//        return "signin";
//    }
//
//    @GetMapping("/signup")
//    public String SignupForm() {
//        return "signup";
//    }
//
//    @PostMapping("/signup")
//    public String signUp(User user) {
//
//        User newUsexr = user; // 새로운 유저 받음
//
//        User userEntity = authService.signup(user);
//        System.out.println(userEntity);
//
//        return "signin";
//    }
//
////    @RequestMapping(value="logout", method = RequestMethod.GET)
//    @GetMapping("/logout")
//    public String logout(HttpSession session) throws Exception {
//        authService.logout(session);
//
//        return "redirect:/signin";
//    }
//}
