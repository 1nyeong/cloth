package com.study.clothclone.controller;

import com.study.clothclone.entity.Board;
import com.study.clothclone.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @GetMapping("/board")
    public String board(){
        return "page/board";
    }



    @Autowired
    private BoardService boardService;

    @PostMapping("/boardlist")
    public String boardWritePro(Board board, Model model){

        boardService.write(board);
        model.addAttribute("list",boardService.boardList());

        return "page/boardlist";
    }


    @GetMapping("/boardlist")
    public String boardList(Model model){

        model.addAttribute("list",boardService.boardList());

        return "page/boardlist";
    }


    @GetMapping("/boardview")
    public String boardView(Model model,Integer id){

        model.addAttribute("board",boardService.boardView(id));
        return "page/boardview";
    }
}
