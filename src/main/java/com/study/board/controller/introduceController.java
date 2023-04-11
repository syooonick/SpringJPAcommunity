package com.study.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j

public class introduceController {

    @RequestMapping("introduce/introduce")
        public String introForm() {
        return "introduce/introduce";
    }
}
