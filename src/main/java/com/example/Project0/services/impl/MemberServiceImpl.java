package com.example.Project0.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Project0.dto.MemberDetailDTO;
import com.example.Project0.dto.MemberUpdateDTO;
import com.example.Project0.dto.LogInDTO;
import com.example.Project0.dto.SignUpDTO;
import com.example.Project0.entity.MemberEntity;
import com.example.Project0.repository.MemberRepository;
import com.example.Project0.services.MemberService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long signup(SignUpDTO signUpDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntity(signUpDTO);
        Long memberId = memberRepository.save(memberEntity).getId();
        return memberId;
    }

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

    @Override
    public List<MemberDetailDTO> findAll() {
        // findAll이라는 메서드 호출 및 Entity타입의 List에 호출 결과 저장
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        // Entity타입의 List를 DTO타입의 List로 변환
        // 위에서 말했다시피 반드시 변환해 줄 필요는 없음.
        List<MemberDetailDTO> memberDetailDTOList = MemberDetailDTO.change(memberEntityList);
        return memberDetailDTOList;
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {
        // findById를 하게 되면 jpa에서 Optional타입으로 줌.
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(memberId);
        // Optional 타입을 Entity타입으로 바꾸기 위해 .get()을 붙임
        MemberEntity memberEntity = memberEntityOptional.get();
        // Entity 타입을 DTO로 타입으로 바꾸기 위해 전에 DTO에 만들어 놓은 메서드를 호출해 변환
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity);
        // 변환된 DTO타입을 리턴함.
        return memberDetailDTO;
    }

    @Override
    public void deleteById(Long memberId) {
        // jpa의 deleteBy~ 메서드를 호출만 하면 끝.
        memberRepository.deleteById(memberId);
    }

    @Override
    public Long update(MemberUpdateDTO memberUpdateDTO) {
        MemberEntity existingMember = memberRepository.findById(memberUpdateDTO.getMemberId()).orElseThrow(() -> new EntityNotFoundException("Member not found"));
        existingMember.setMemberEmail(memberUpdateDTO.getMemberEmail());
        existingMember.setMemberName(memberUpdateDTO.getMemberName());
        existingMember.setMemberPassword(memberUpdateDTO.getMemberPassword());
        Long memberId = memberRepository.save(existingMember).getId();
        return memberId;
    }

    @Override
    public MemberUpdateDTO findByEmail(String memberEmail) {
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        MemberUpdateDTO memberUpdateDTO = MemberUpdateDTO.toMemberUpdateDTO(memberEntity);
        return memberUpdateDTO;
    }

    @Override
    public MemberEntity getMemberbyId(Long memberId) {
        if (memberId == null) {
            System.out.println("MemberService null");;
            return null;
        }
        System.out.println("memberId" + memberId);
        Optional<MemberEntity> optMember = memberRepository.findById(memberId);
        if (optMember.isEmpty()) {
            return null;
        }
        return optMember.get(); // If a value is present, returns the value, otherwise throws NoSuchElementException.
    }

    @Override
    public MemberEntity getMemberbyEmail(String memberEmail) {
        if (memberEmail == null) {
            return null;
        }
        MemberEntity memberEntity = memberRepository.findByMemberEmail(memberEmail);
        if (memberEntity == null) {
            return null;
        }
        return memberEntity;
    }
}
