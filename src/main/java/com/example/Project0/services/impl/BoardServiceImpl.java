package com.example.Project0.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Project0.dto.BoardDetailDTO;
import com.example.Project0.dto.BoardWriteDTO;
import com.example.Project0.entity.BoardEntity;
import com.example.Project0.repository.BoardRepository;
import com.example.Project0.services.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Long write(BoardWriteDTO boardWriteDTO) {
        BoardEntity boardEntity = BoardEntity.toBoardEntity(boardWriteDTO);
        Long boardId = boardRepository.save(boardEntity).getId();
        return boardId;
    }

    @Override
    public List<BoardDetailDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDetailDTO> boardDetailDTOList = BoardDetailDTO.toBoardDetailDTOList(boardEntityList);
        return boardDetailDTOList;
    }

    @Override
    public BoardDetailDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);
        BoardDetailDTO boardDetailDTO = null;
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardDetailDTO = BoardDetailDTO.toBoardDetailDTO(boardEntity);
        }
        return boardDetailDTO;
    }
}
