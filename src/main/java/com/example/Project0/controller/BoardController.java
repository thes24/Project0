package com.example.Project0.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Project0.dto.BoardDetailDTO;
import com.example.Project0.dto.BoardUpdateDTO;
import com.example.Project0.dto.BoardWriteDTO;
import com.example.Project0.services.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    // @GetMapping("/mainboard")
    // public String mainboard() {
    //     return "board/mainboard";
    // }

    // @GetMapping("/write")
    // public String writeForm(Model model) {
    //     model.addAttribute("board", new BoardWriteDTO());
    //     return "board/write";
    // }

    @PostMapping("/write")
    public ResponseEntity<?> write(@RequestBody BoardWriteDTO boardWriteDTO, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object boardWriterObj = session.getAttribute("memberEmail");
        if (boardWriterObj != null) {
            String boardWriter = (String) boardWriterObj;
            boardWriteDTO.setBoardWriter(boardWriter);
        }
        boardService.write(boardWriteDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(Model model) {
        List<BoardDetailDTO> boardDetailDTOList = boardService.findAll();
        // model.addAttribute("boardList", boardDetailDTOList);
        return new ResponseEntity<>(boardDetailDTOList, HttpStatus.OK);
    }

    @GetMapping("/find-id/{boardId}")
    public ResponseEntity<?> findById(@PathVariable Long boardId, Model model) {
        BoardDetailDTO boardDetailDTO = boardService.findById(boardId);
        // model.addAttribute("boardDetailDTO", boardDetailDTO);
        return new ResponseEntity<>(boardDetailDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity<?> deleteById2(@PathVariable Long boardId) {
        boardService.deleteById(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @GetMapping("/update/{boardId}")
    // public String updateForm(@PathVariable Long boardId, Model model) {
    //     BoardUpdateDTO boardUpdateDTO = boardService.findById2(boardId);
    //     model.addAttribute("boardUpdateDTO", boardUpdateDTO);
    //     return "board/update";
    // }

    @PutMapping("/update/{boardId}")
    public ResponseEntity<?> update2(@PathVariable Long boardId, @RequestBody BoardUpdateDTO boardUpdateDTO) {
        BoardDetailDTO boardDetailDTO = boardService.findById(boardId);
        if (boardUpdateDTO.getBoardPassword() == null || boardUpdateDTO.getBoardPassword().isBlank()) {
            boardUpdateDTO.setBoardPassword(boardDetailDTO.getBoardPassword());
        }
        if (!boardUpdateDTO.getBoardPassword().equals(boardDetailDTO.getBoardPassword())) {
            return new ResponseEntity<>("Incorrect password. Please try again.", HttpStatus.UNAUTHORIZED);
        }
        LocalDateTime updateTime = LocalDateTime.now();
        boardUpdateDTO.setUpdateDateTime(updateTime);
        boardService.update(boardUpdateDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
