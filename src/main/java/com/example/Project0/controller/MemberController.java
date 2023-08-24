package com.example.Project0.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Project0.dto.LogInDTO;
import com.example.Project0.dto.MemberDetailDTO;
import com.example.Project0.dto.MemberUpdateDTO;
import com.example.Project0.dto.SignUpDTO;
import com.example.Project0.entity.MemberEntity;
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
        memberService.signup(signUpDTO);
        
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
            MemberEntity member = memberService.getMemberbyEmail(logInDTO.getMemberEmail());
            session.setAttribute("member_id", member.getId());

            // MemberEntity loginMember = memberService.getMemberbyId();
            return "member/mypage";
        } else {
            return "member/login";
        }
    }

    //회원목록 조회
    @GetMapping("/")
    public String findAll(@SessionAttribute(name = "member_id", required = false) Long memberId, Model model) {
        MemberEntity memberEntity = memberService.getMemberbyId(memberId);

        if (memberEntity == null) {
            model.addAttribute("memberList", null);
            return "member/findAll";
            // List<MemberDetailDTO> memberList = memberService.;
        }

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

    /*
    // 회원삭제(/member/delete/5)
    // 삭제해야 함 RESTFUL하지 않음
    @GetMapping("/delete/{memberId}")
    public String deleteById(@PathVariable("memberId") Long memberId) {
        memberService.deleteById(memberId);
        return "redirect:/member/";
    }
    */

    @DeleteMapping("/{memberIdForDelete}")
    public ResponseEntity<?> deleteById2(@SessionAttribute(name = "member_id", required = false) Long memberId, @PathVariable Long memberIdForDelete) {
        MemberEntity memberEntity = memberService.getMemberbyId(memberId);

        if (memberEntity == null || memberId != memberIdForDelete) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        memberService.deleteById(memberIdForDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/update/{memberId}")
    public String updateForm(Model model, HttpSession session) {
        String memberEmail = (String) session.getAttribute("loginEmail");
        MemberUpdateDTO memberUpdateDTO = memberService.findByEmail(memberEmail);
        model.addAttribute("member", memberUpdateDTO);
        return "member/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute MemberUpdateDTO memberUpdateDTO) {
        memberService.update(memberUpdateDTO);
        return "redirect:/member/" + memberUpdateDTO.getMemberId();
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> update2(@SessionAttribute(name = "member_id", required = false) Long memberId, @RequestBody MemberUpdateDTO memberUpdateDTO) {
        memberService.update(memberUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
