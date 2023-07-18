package com.example.Project0.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

import com.example.Project0.dto.LogInDTO;
import com.example.Project0.dto.SignUpDTO;
import com.example.Project0.services.LogInService;
import com.example.Project0.services.SignUpService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final SignUpService signUpService;
    private final LogInService logInService;

    //회원가입 페이지 출력 요청
    @RequestMapping("/signup")
    public String suForm() {
        return "signup.html";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignUpDTO signUpDTO) {
        System.out.println("Member Signup");
        System.out.println("MemberDTO:" + signUpDTO);
        signUpService.save(signUpDTO);
        
        return "login.html";
    }

    //로그인 페이지 출력 요청
    @RequestMapping("/login")
    public String logForm() {
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LogInDTO logInDTO, HttpSession session) {
        if (logInService.login(logInDTO)) {
            session.setAttribute("loginEmail", logInDTO.getMemberEmail());
            return "good.html";
        } else {
            return "login.html";
        }
    }
}
