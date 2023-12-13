package com.web.myday.MyDay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 홈 & 로그인 페이지
    @GetMapping("/")
    public String home() {
        return "login";
    }

}