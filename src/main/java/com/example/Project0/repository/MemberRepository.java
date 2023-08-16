package com.example.Project0.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project0.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    MemberEntity findByMemberEmail(String memberEmail);
}
