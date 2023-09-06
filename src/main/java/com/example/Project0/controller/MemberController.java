package com.example.Project0.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.Project0.dto.LogInDTO;
import com.example.Project0.dto.MemberDetailDTO;
import com.example.Project0.dto.MemberPublicDTO;
import com.example.Project0.dto.SignUpDTO;
import com.example.Project0.entity.MemberEntity;
import com.example.Project0.services.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    // @GetMapping("/signup")
    // public String suForm() {
    //     return "member/signup";
    // }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDTO signUpDTO) {
        System.out.println("MemberDTO:" + signUpDTO);
        memberService.signup(signUpDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/check-email")
    public ResponseEntity<?> emailCheck(@RequestBody SignUpDTO signUpDTO) {
        String signupEmail = signUpDTO.getMemberEmail();
        if (memberService.checkEmailDuplicate(signupEmail)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @RequestMapping("/login")
    // public String logForm() {
    //     return "member/login";
    // }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogInDTO logInDTO, HttpSession session) {
        if (memberService.login(logInDTO)) {
            session.setAttribute("loginEmail", logInDTO.getMemberEmail());
            MemberEntity memberEntity = memberService.getMemberbyEmail(logInDTO.getMemberEmail());
            session.setAttribute("memberId", memberEntity.getId());
            session.setAttribute("memberName", memberEntity.getMemberName());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Session이 없으면 null return
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check-login")
    public ResponseEntity<?> loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object memberIdObj = session.getAttribute("memberId");
            if (memberIdObj != null) {
                Long memberId = (Long) memberIdObj;
                MemberEntity memberEntity = memberService.getMemberbyId(memberId);
                if (memberEntity != null) {
                    MemberPublicDTO memberPublicDTO = MemberPublicDTO.toMemberPublicDTO(memberEntity);
                    return new ResponseEntity<>(memberPublicDTO, HttpStatus.OK);
                }
            }
        }
        MemberPublicDTO guestMemberDTO = new MemberPublicDTO();
        guestMemberDTO.setMemberId(-1L);
        return new ResponseEntity<>(guestMemberDTO, HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@SessionAttribute(name = "memberId", required = false) Long memberId, Model model) {
        // MemberEntity memberEntity = memberService.getMemberbyId(memberId);
        // if (memberEntity == null) {
        //     model.addAttribute("memberList", null);
        //     return "member/findAll";
        // }
        List<MemberDetailDTO> memberList = memberService.findAll();
        // model.addAttribute("memberList", memberList);
        return new ResponseEntity<>(memberList, HttpStatus.OK);
    }

    @GetMapping("/find-id/{memberId}")
    public ResponseEntity<?> findById(@PathVariable Long memberId, HttpServletRequest request) {
        /*
         * @PathVariavle : 경로상에 있는 변수를 가져올 때 사용
         *
         * 원래는 이렇게 써야 하지만 public String findById(@PathVariable("memberId") Long memberId, Model model) {
         * @PathVarialbe에서 받는 값의 이름과 매개변수의 값의 이름이 같다면 위와 같이 생략가능
         */
        HttpSession session = request.getSession(false);

        Object sessionIdObj = session.getAttribute("memberId");
        Long sessionId = (Long) sessionIdObj;
        Object sessionEmailObj = session.getAttribute("memberEmail");
        String sessionEmail = (String) sessionEmailObj;
        Object sessionNameObj = session.getAttribute("memberName");
        String sessionName = (String) sessionNameObj;

        MemberDetailDTO memberDetailDTO = memberService.findById(memberId);

        if (sessionId != memberDetailDTO.getMemberId() && sessionEmail != memberDetailDTO.getMemberEmail() && sessionName != memberDetailDTO.getMemberName()) {
            return new ResponseEntity<>(memberDetailDTO, HttpStatus.UNAUTHORIZED);
        }
        // model.addAttribute("member", memberDetailDTO);
        return new ResponseEntity<>(memberDetailDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{memberIdForDelete}")
    public ResponseEntity<?> deleteById2(@SessionAttribute(name = "memberId", required = false) Long memberId, @PathVariable Long memberIdForDelete) {
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
        MemberDetailDTO memberDetailDTO = memberService.findByMemberEmail(memberEmail);
        model.addAttribute("member", memberDetailDTO);
        return "member/mypage";
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> update2(@SessionAttribute(name = "memberId", required = false) Long memberId, @RequestBody MemberDetailDTO memberDetailDTO) {
        memberService.update(memberDetailDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
