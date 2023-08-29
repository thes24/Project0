package com.example.Project0.services;

import java.util.List;

import com.example.Project0.dto.LogInDTO;
import com.example.Project0.dto.MemberDetailDTO;
import com.example.Project0.dto.SignUpDTO;
import com.example.Project0.entity.MemberEntity;

public interface MemberService {

    Long signup(SignUpDTO signUpDTO);

    boolean login(LogInDTO logInDTO);

    List<MemberDetailDTO> findAll();

    MemberDetailDTO findById(Long memberId);

    void deleteById(Long memberId);

    Long update(MemberDetailDTO memberDetailDTO);

    MemberDetailDTO findByMemberEmail(String memberEmail);

    MemberEntity getMemberbyId(Long memberId);

    MemberEntity getMemberbyEmail(String memberEmail);

    boolean checkEmailDuplicate(String loginEmail);
}
