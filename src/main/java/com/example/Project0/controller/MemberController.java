package com.example.Project0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import com.example.Project0.dto.MemberDTO;
import com.example.Project0.services.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @RequestMapping("/signup")
    public String suForm() {
        return "signup.html";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("Member Signup");
        System.out.println("MemberDTO:" + memberDTO);
        memberService.save(memberDTO);
        
        return "login.html";
    }

    //로그인 페이지 출력 요청
    @RequestMapping("/login")
    public String logForm() {
        return "login.html";
    }
}
