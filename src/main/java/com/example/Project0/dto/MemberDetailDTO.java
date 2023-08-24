package com.example.Project0.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.Project0.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetailDTO {

    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

    // MemberEntity -> MemberDetailDTO
    // 리턴타입 : MemberDetailDTO
    // 매개변수타입 : MemberEntity
    public static MemberDetailDTO toMemberDetailDTO(MemberEntity memberEntity) {
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setMemberId(memberEntity.getId());
        memberDetailDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDetailDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDetailDTO.setMemberName(memberEntity.getMemberName());

        return memberDetailDTO;
    }
    
    // Entity타입의 List를 DTO타입의 List로 변환하는 메서드.
    // 위에 있는 Entity를 DTO로 변환해주는 메서드를 사용해 더 간단하게 구현
    public static List<MemberDetailDTO> change(List<MemberEntity> memberEntityList) {
        List<MemberDetailDTO> memberDetailDTOList = new ArrayList<>();
        for (MemberEntity me : memberEntityList) {
            memberDetailDTOList.add(toMemberDetailDTO(me));
        }
        
        return memberDetailDTOList;
    }
}
