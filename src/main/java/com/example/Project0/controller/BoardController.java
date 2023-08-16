package com.example.Project0.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Project0.dto.BoardDetailDTO;
import com.example.Project0.dto.BoardUpdateDTO;
import com.example.Project0.dto.BoardWriteDTO;
import com.example.Project0.services.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j // 로그를 기록할 수 있는 라이브러리(어노테이션)
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/mainboard")
    public String mainboard() {
        return "board/mainboard";
    }
    
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("board", new BoardWriteDTO());
        return "board/write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute("board") BoardWriteDTO boardWriteDTO) {
        boardService.write(boardWriteDTO);
        return "board/mainboard";
    }

    @GetMapping("/")
    public String findAll(Model model) {
        log.info("글보기 메서드 호출. 요청글번호 {}");
        List<BoardDetailDTO> boardDetailDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDetailDTOList);
        return "board/findAll";
    }

    @GetMapping("/{boardId}")
    public String findById(@PathVariable Long boardId, Model model) {
        log.info("글보기 메서드 호출. 요청글번호 {}", boardId);
        BoardDetailDTO boardDetailDTO = boardService.findById(boardId);
        model.addAttribute("boardDetailDTO", boardDetailDTO);
        return "board/findById";
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteById2(@PathVariable Long boardId) {
        boardService.deleteById(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable Long boardId, Model model) {
        BoardUpdateDTO boardUpdateDTO = boardService.findById2(boardId);
        model.addAttribute("boardUpdateDTO", boardUpdateDTO);
        return "board/update";
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<?> update2(@RequestBody BoardUpdateDTO boardUpdateDTO) {
        boardService.update(boardUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
