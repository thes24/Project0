package com.example.Project0.entity;

import com.example.Project0.dto.MemberDetailDTO;
import com.example.Project0.dto.MemberUpdateDTO;
import com.example.Project0.dto.SignUpDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    public static MemberEntity toMemberEntity(SignUpDTO signUpDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(signUpDTO.getMemberEmail());
        memberEntity.setMemberPassword(signUpDTO.getMemberPassword());
        memberEntity.setMemberName(signUpDTO.getMemberName());

        return memberEntity;
    }

    public static MemberEntity toUpdateMember(MemberUpdateDTO memberUpdateDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberUpdateDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberUpdateDTO.getMemberPassword());
        memberEntity.setMemberName(memberUpdateDTO.getMemberName());

        return memberEntity;
    }
}
