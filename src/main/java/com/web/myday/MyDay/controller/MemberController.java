package com.web.myday.MyDay.controller;

import com.web.myday.MyDay.dto.MemberDTO;
import com.web.myday.MyDay.entity.MemberEntity;
import com.web.myday.MyDay.entity.PostEntity;
import com.web.myday.MyDay.service.MemberService;
import com.web.myday.MyDay.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Transactional
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;

    // 회원가입 화면 보여주기
    @GetMapping("/member/join")
    public String showJoinForm() {
        return "joinForm";
    }

    // 회원가입 처리 후 로그인 화면으로 넘어가기
    @PostMapping("/member/join")
    public String join(@ModelAttribute MemberDTO memberDTO) {
        memberService.join(memberDTO);
        return "login";
    }

    // 회원가입 시 이메일 중복 체크
    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }

    // 로그인 성공 시 메인 화면으로 넘어가기
    // 로그인 실패 시 로그인 화면 그대로
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, Model model) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            List<PostEntity> postList = postService.postListByDes(loginResult.getId());
            String[] createdAtList = new String[postList.size()];
            for (int i = 0; i < postList.size(); i++) {
                createdAtList[i] = postList.get(i)
                        .getCreatedAt()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            }
            model.addAttribute("createdAtList", createdAtList);
            model.addAttribute("postList", postList);
            model.addAttribute("memberId", loginResult.getId());
            return "main";
        } else {
            return "login";
        }
    }

    // 로그아웃
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

}
