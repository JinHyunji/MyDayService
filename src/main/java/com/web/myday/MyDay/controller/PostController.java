package com.web.myday.MyDay.controller;

import com.web.myday.MyDay.dto.PostDTO;
import com.web.myday.MyDay.entity.MemberEntity;
import com.web.myday.MyDay.entity.PostEntity;
import com.web.myday.MyDay.service.MemberService;
import com.web.myday.MyDay.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 메인 페이지
    @GetMapping("/main")
    public String showMain() {
        return "main";
    }

    // 일기 작성 폼
    @GetMapping("/postForm")
    public String postForm() {
        return "postForm";
    }

    @PostMapping("/post/save")
    public String postSave(@ModelAttribute PostDTO postDTO, Model model) {
        PostEntity postEntity = postService.postSave(postDTO);
        model.addAttribute("post", postEntity);
        return "detailPost";
    }

    // 일기 수정
    @GetMapping("/post/update")
    public String postUpdate(@RequestParam("id") Long id, Model model) {
        model.addAttribute("post", postService.postView(id));
        return "updatePost";
    }

    @PostMapping("/post/update")
    public String postUpdate(@RequestParam("id") Long id, @ModelAttribute PostDTO postDTO, Model model) {
        PostEntity postEntity = postService.postView(id);
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setContent(postDTO.getContent());
        postService.postSave(postEntity);
        model.addAttribute("post", postEntity);
        return "detailPost";
    }

    // 일기 삭제
    @GetMapping("/post/delete")
    public String postDelete(@RequestParam("id") Long id, Model model) {
        postService.postDelete(id);
        model.addAttribute("message", "삭제가 완료되었습니다.");
        model.addAttribute("searchUrl", "/main");
        return "message";
    }

//    // 일기 목록
//    @GetMapping("/post/list")
//    public String postList(Model model,
//                            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
//                            String searchKeyword) {
//
//        Page<PostEntity> list = null;
//
//        if (searchKeyword == null) {
//            list = postService.postList(pageable);
//        } else {
//            list = postService.postSearchList(searchKeyword, pageable);
//        }
//
//        int nowPage = list.getPageable().getPageNumber() + 1;
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 5, list.getTotalPages());
//
//        model.addAttribute("list", list);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "main";
//    }
//
//    // view
//    @GetMapping("/post/view") // localhost:8080/board/view?id=1
//    public String postView(@RequestParam("id") Long id, Model model) {
//        model.addAttribute("post", postService.postView(id));
//        return "detailPost";
//    }
}
