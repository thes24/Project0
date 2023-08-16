package com.example.Project0.entity;

import com.example.Project0.dto.BoardUpdateDTO;
import com.example.Project0.dto.BoardWriteDTO;

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
@Table(name = "board_table")
public class BoardEntity extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column
    private String boardWriter;

    @Column
    private String boardPassword;

    @Column
    private String boardTitle;

    @Column
    private String boardContent;

    public static BoardEntity toBoardEntity(BoardWriteDTO boardWriteDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardWriteDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardWriteDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardWriteDTO.getBoardTitle());
        boardEntity.setBoardContent(boardWriteDTO.getBoardContent());

        return boardEntity;
    }

    public static BoardEntity updateBoard(BoardUpdateDTO boardUpdateDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardUpdateDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardUpdateDTO.getBoardPassword());
        boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
        boardEntity.setBoardContent(boardUpdateDTO.getBoardContent());

        return boardEntity;
    }
}
