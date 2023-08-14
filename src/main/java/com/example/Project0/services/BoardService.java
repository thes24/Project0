package com.example.Project0.services;

import java.util.List;

import com.example.Project0.dto.BoardDetailDTO;
import com.example.Project0.dto.BoardWriteDTO;

public interface BoardService {
    
    Long write(BoardWriteDTO boardWriteDTO);

    List<BoardDetailDTO> findAll();

    BoardDetailDTO findById(Long boardId);
}
