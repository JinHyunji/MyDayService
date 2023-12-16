package com.web.myday.MyDay.controller;

import com.web.myday.MyDay.dto.MemberDTO;
import com.web.myday.MyDay.dto.PostDTO;
import com.web.myday.MyDay.entity.MemberEntity;
import com.web.myday.MyDay.entity.PostEntity;
import com.web.myday.MyDay.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Transactional
public class PostController {

    private final PostService postService;

    // 게시글 작성 폼 보여주기(회원 정보 넘기기)
    @PostMapping("/postForm")
    public String showPostForm(@RequestParam("memberId") Long memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "postForm";
    }

    // 게시글 작성 후 완료한 게시글 보여주기
    @PostMapping("/post/save")
    public String savePost(@ModelAttribute PostDTO postDTO, @RequestParam("memberId") Long memberId, Model model) {
        PostEntity postEntity = postService.savePost(memberId, postDTO);
        model.addAttribute("post", postEntity);
        model.addAttribute("memberId", memberId);
        return "detailPost";
    }

    // 메인 화면 보여주기
    @GetMapping("/main/{memberId}")
    public String showMain(@PathVariable("memberId") Long memberId, Model model) {
        model.addAttribute("memberId", memberId);
        return "main";
    }
}
