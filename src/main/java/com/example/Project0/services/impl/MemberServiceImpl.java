package com.example.Project0.services.impl;

import org.springframework.stereotype.Service;

import com.example.Project0.dto.MemberDTO;
import com.example.Project0.entity.MemberEntity;
import com.example.Project0.repository.MemberRepository;
import com.example.Project0.services.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    
    private final MemberRepository memberRepository;

    @Override
    public Long save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        Long memberId = memberRepository.save(memberEntity).getId();
        return memberId;
    }
}
