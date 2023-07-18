package com.example.Project0.entity;

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
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 50, unique = true)
    private String memberEmail;

    @Column(length = 20)
    private String memberPassword;

    @Column()
    private String memberName;

    public static MemberEntity toMemberEntity(SignUpDTO signUpDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(signUpDTO.getMemberEmail());
        memberEntity.setMemberPassword(signUpDTO.getMemberPassword());
        memberEntity.setMemberName(signUpDTO.getMemberName());

        return memberEntity;
    }
}
