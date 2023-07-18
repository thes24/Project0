package com.example.Project0.services.impl;

import org.springframework.stereotype.Service;

import com.example.Project0.dto.SignUpDTO;
import com.example.Project0.entity.MemberEntity;
import com.example.Project0.repository.MemberRepository;
import com.example.Project0.services.SignUpService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    
    private final MemberRepository memberRepository;

    @Override
    public Long save(SignUpDTO signUpDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(signUpDTO);
        Long memberId = memberRepository.save(memberEntity).getId();
        return memberId;
    }
}
