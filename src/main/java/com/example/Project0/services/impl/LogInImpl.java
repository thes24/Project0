package com.example.Project0.services.impl;

import org.springframework.stereotype.Service;

import com.example.Project0.dto.LogInDTO;
import com.example.Project0.entity.MemberEntity;
import com.example.Project0.repository.MemberRepository;
import com.example.Project0.services.LogInService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogInImpl implements LogInService {

    private final MemberRepository memberRepository;
    
    @Override
    public boolean login(LogInDTO logInDTO) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(logInDTO.getMemberEmail());
        // 정보가 비어있으면 로그인시도를 한 email을 가진 데이터 자체가 없는 정보라는 뜻임.
        if (memberEntity != null) {
            // 로그인을 시도한 데이터의 비밀번호와 jpa에서 받아온 데이터의 비밀번호를 비교
            if (memberEntity.getMemberPassword().equals(logInDTO.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
