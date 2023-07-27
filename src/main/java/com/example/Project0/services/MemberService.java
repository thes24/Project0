package com.example.Project0.services;

import java.util.List;
import com.example.Project0.dto.LogInDTO;
import com.example.Project0.dto.MemberDetailDTO;
import com.example.Project0.dto.SignUpDTO;

public interface MemberService {
    
    Long save(SignUpDTO signUpDTO);

    boolean login(LogInDTO logInDTO);

    List<MemberDetailDTO> findAll();

    MemberDetailDTO findById(Long memberId);

    void deleteById(Long memberId);
}
