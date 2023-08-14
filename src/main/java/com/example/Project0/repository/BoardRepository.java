package com.example.Project0.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project0.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    
}
