package com.example.Project0.dto;

import java.time.LocalDateTime;

import com.example.Project0.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateDTO {

    private Long boardId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime updateDateTime;

    public static BoardUpdateDTO toBoardUpdateDTO(BoardEntity boardEntity) {
        BoardUpdateDTO boardUpdateDTO = new BoardUpdateDTO();
        boardUpdateDTO.setBoardId(boardEntity.getId());
        boardUpdateDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardUpdateDTO.setBoardPassword(boardEntity.getBoardPassword());
        boardUpdateDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardUpdateDTO.setBoardContent(boardEntity.getBoardContent());
        boardUpdateDTO.setUpdateDateTime(boardEntity.getUpdateDateTime());

        return boardUpdateDTO;
    }
}
