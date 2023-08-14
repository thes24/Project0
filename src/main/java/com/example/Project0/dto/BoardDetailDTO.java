package com.example.Project0.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.Project0.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDetailDTO {

    private Long boardId;
    private String boardWriter;
    private String boardPassword;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public static BoardDetailDTO toBoardDetailDTO(BoardEntity boardEntity) {
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardId(boardEntity.getId());
        boardDetailDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDetailDTO.setBoardPassword(boardEntity.getBoardPassword());
        boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDetailDTO.setBoardContent(boardEntity.getBoardContent());
        boardDetailDTO.setCreateDateTime(boardEntity.getCreateDateTime());
        boardDetailDTO.setUpdateDateTime(boardEntity.getUpdateDateTime());

        return boardDetailDTO;
    }

    public static List<BoardDetailDTO> toBoardDetailDTOList(List<BoardEntity> boardEntityList) {
        List<BoardDetailDTO> boardDetailDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDetailDTOList.add(toBoardDetailDTO(boardEntity));
        }

        return boardDetailDTOList;
    }
}
