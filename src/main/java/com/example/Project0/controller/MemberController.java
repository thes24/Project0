package com.example.Project0.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Project0.dto.LogInDTO;
import com.example.Project0.dto.MemberDetailDTO;
import com.example.Project0.dto.SignUpDTO;
import com.example.Project0.services.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    //회원가입 페이지 출력 요청
    @GetMapping("/signup")
    public String suForm() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute SignUpDTO signUpDTO) {
        System.out.println("Member Signup");
        System.out.println("MemberDTO:" + signUpDTO);
        memberService.save(signUpDTO);
        
        return "member/signup";
    }

    //로그인 페이지 출력 요청
    @RequestMapping("/login")
    public String logForm() {
        return "member/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LogInDTO logInDTO, HttpSession session) {
        if (memberService.login(logInDTO)) {
            session.setAttribute("loginEmail", logInDTO.getMemberEmail());
            return "boar";
        } else {
            return "member/login";
        }
    }

    //회원목록 조회
    @GetMapping("/")
    public String findAll(Model model) {
        List<MemberDetailDTO> memberList = memberService.findAll();
        model.addAttribute("memberList", memberList);
        return "member/findAll";
    }

    // 상세조회
    // /member/2, /member/15 => /member/{memberId}
    // @PathVariavle : 경로상에 있는 변수를 가져올 때 사용
    @GetMapping("/{memberId}")
    //public String findById(@PathVariable("memberId") Long memberId, Model model) {
    // @PathVarialbe에서 받는 값의 이름과 매개변수의 값의 이름이 같다면 아래와 같이 생략가능
    public String findById(@PathVariable Long memberId, Model model) {
        MemberDetailDTO memberDetailDTO = memberService.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "member/findById";
    }

    // 회원삭제(/member/delete/5)
    @GetMapping("/delete/{memberId}")
    public String deleteById(@PathVariable("memberId") Long memberId) {
        memberService.deleteById(memberId);
        return "redirect:/member/";
    }
}
