package com.example.Project0.dto;

import com.example.Project0.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberPublicDTO {

    private Long memberId;
    private String memberEmail;
    private String memberName;

    public static MemberPublicDTO toMemberPublicDTO(MemberEntity memberEntity) {
        MemberPublicDTO memberPublicDTO = new MemberPublicDTO();
        memberPublicDTO.setMemberId(memberEntity.getId());
        memberPublicDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberPublicDTO.setMemberName(memberEntity.getMemberName());

        return memberPublicDTO;
    }
}
