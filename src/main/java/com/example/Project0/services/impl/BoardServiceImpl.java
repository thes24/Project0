package com.example.Project0.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Project0.dto.BoardDetailDTO;
import com.example.Project0.dto.BoardUpdateDTO;
import com.example.Project0.dto.BoardWriteDTO;
import com.example.Project0.entity.BoardEntity;
import com.example.Project0.repository.BoardRepository;
import com.example.Project0.services.BoardService;

import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public BoardUpdateDTO findById2(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);
        BoardUpdateDTO boardUpdateDTO = null;
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            boardUpdateDTO = BoardUpdateDTO.toBoardUpdateDTO(boardEntity);
        }
        return boardUpdateDTO;
    }

    @Override
    public Long update(BoardUpdateDTO boardUpdateDTO) {
        BoardEntity existingBoard = boardRepository.findById(boardUpdateDTO.getBoardId()).orElseThrow(() -> new EntityNotFoundException("Board not found"));
        existingBoard.setBoardWriter(boardUpdateDTO.getBoardWriter());
        existingBoard.setBoardPassword(boardUpdateDTO.getBoardPassword());
        existingBoard.setBoardTitle(boardUpdateDTO.getBoardTitle());
        existingBoard.setBoardContent(boardUpdateDTO.getBoardContent());
        Long boardId = boardRepository.save(existingBoard).getId();
        return boardId;
    }

    @Override
    public void deleteById(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
