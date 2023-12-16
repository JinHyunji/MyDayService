package com.web.myday.MyDay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Transactional
public class HomeController {

    // 홈 화면
    @GetMapping("/")
    public String home() {
        return "login";
    }

}
