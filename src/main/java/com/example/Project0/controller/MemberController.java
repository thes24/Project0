package com.example.Project0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberController {

    //회원가입 페이지 출력 요청
    @RequestMapping("/signup")
    public String suForm() {
        return "signup.html";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String memberEmail, String memberPassword, String memberName) {
        System.out.println("Member Signup");
        System.out.println("Email:" + memberEmail + "Password:" + memberPassword + "Name:" + memberName);
        return "index.html";
    }

    //로그인 페이지 출력 요청
    @RequestMapping("/login")
    public String logForm() {
        return "login.html";
    }
}
