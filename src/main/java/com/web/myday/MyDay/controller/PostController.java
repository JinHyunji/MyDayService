package com.web.myday.MyDay.controller;

import com.web.myday.MyDay.dto.PostDTO;
import com.web.myday.MyDay.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 메인 페이지
    @GetMapping("/main")
    public String showMain() {
        return "main";
    }

    // 일기 작성폼
    @GetMapping("/postForm")
    public String postForm() {
        return "postForm";
    }

    @PostMapping("/post/save")
    public String postSave(@ModelAttribute PostDTO postDTO, Model model) {
        postService.postSave(postDTO);
        model.addAttribute("message", "일기 작성 완료!");
        model.addAttribute("searchUrl", "/main");
        return "message";
    }
}
