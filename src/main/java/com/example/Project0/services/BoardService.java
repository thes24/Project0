package com.example.Project0.services;

import java.util.List;

import com.example.Project0.dto.BoardDetailDTO;
import com.example.Project0.dto.BoardUpdateDTO;
import com.example.Project0.dto.BoardWriteDTO;

public interface BoardService {
    
    Long write(BoardWriteDTO boardWriteDTO);

    List<BoardDetailDTO> findAll();

    BoardDetailDTO findById(Long boardId);

    BoardUpdateDTO findById2(Long boardId);

    Long update(BoardUpdateDTO boardUpdateDTO);

    void deleteById(Long memberId);
}
