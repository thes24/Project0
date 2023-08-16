package com.example.Project0.dto;

import com.example.Project0.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberUpdateDTO {
    
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    public static MemberUpdateDTO toMemberUpdateDTO(MemberEntity memberEntity) {
        MemberUpdateDTO memberUpdateDTO = new MemberUpdateDTO();
        memberUpdateDTO.setMemberId(memberEntity.getId());
        memberUpdateDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberUpdateDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberUpdateDTO.setMemberName(memberEntity.getMemberName());

        return memberUpdateDTO;
    }
}
