package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class MainController {

    @RequestMapping ("/main") // localhost:8080/main
    public String mainForm() {
        return "main";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/main";
    }
}
