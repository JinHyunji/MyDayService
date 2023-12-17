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

import java.time.format.DateTimeFormatter;
import java.util.List;

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
        String createdAt = postEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        model.addAttribute("createdAt", createdAt);
        model.addAttribute("post", postEntity);
        model.addAttribute("memberId", memberId);
        return "detailPost";
    }

    // 메인 화면 보여주기
    @GetMapping("/main")
    public String showMain(@RequestParam("memberId") Long memberId, Model model) {
        List<PostEntity> postList = postService.postListByDes(memberId);
        String[] createdAtList = new String[postList.size()];
        for (int i = 0; i < postList.size(); i++) {
            createdAtList[i] = postList.get(i)
                    .getCreatedAt()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        model.addAttribute("createdAtList", createdAtList);
        model.addAttribute("postList", postList);
        model.addAttribute("memberId", memberId);
        return "main";
    }

    // 게시글 수정 폼 보여주기
    @GetMapping("/post/update")
    public String showUpdatePost(@RequestParam("memberId") Long memberId,
                                 @RequestParam("postId") Long postId,
                                 Model model) {
        model.addAttribute("memberId", memberId);
        model.addAttribute("post", postService.postView(postId));
        return "updatePost";
    }

    // 게시글 수정
    @PostMapping("/post/update")
    public String updatePost(@RequestParam("memberId") Long memberId,
                             @RequestParam("postId") Long postId,
                             PostDTO postDTO,
                             Model model) {
        PostEntity postEntity = postService.updatePost(memberId, postDTO);
        String createdAt = postEntity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        model.addAttribute("createdAt", createdAt);
        model.addAttribute("memberId", memberId);
        model.addAttribute("post", postEntity);
        return "detailPost";
    }

    // 게시글 삭제
    @GetMapping("/post/delete")
    public String deletePost(@RequestParam("memberId") Long memberId,
                             @RequestParam("postId") Long postId,
                             Model model) {
        postService.deletePost(postId);
        List<PostEntity> postList = postService.postListByDes(memberId);
        String[] createdAtList = new String[postList.size()];
        for (int i = 0; i < postList.size(); i++) {
            createdAtList[i] = postList.get(i)
                    .getCreatedAt()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        model.addAttribute("createdAtList", createdAtList);
        model.addAttribute("postList", postList);
        model.addAttribute("memberId", memberId);
        return "main";
    }

    // 게시글 보여주기
    @GetMapping("/post/view") // localhost:8088/post/view?id=1
    public String postView(@RequestParam("memberId") Long memberId,
                           @RequestParam("postId") Long postId,
                           Model model) {
        String createdAt = postService.postView(postId).getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        model.addAttribute("createdAt", createdAt);
        model.addAttribute("memberId", memberId);
        model.addAttribute("post", postService.postView(postId));
        return "detailPost";
    }
}
