package com.study.clothclone.controller;

import com.study.clothclone.entity.Board;
import com.study.clothclone.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @GetMapping("/board")
    public String board() {
        return "page/board";
    }


    @Autowired
    private BoardService boardService;

    @PostMapping("/boardlist")
    public String boardWritePro(Board board, Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        boardService.write(board);

        Page<Board> list = boardService.boardList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", boardService.boardList(pageable));
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "page/boardlist";
    }


    @GetMapping("/boardlist")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> list = boardService.boardList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", boardService.boardList(pageable));
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "page/boardlist";
    }

    @GetMapping("/boardview")
    public String boardView(Model model, Integer id) {

        model.addAttribute("board", boardService.boardView(id));
        return "page/boardview";
    }

    @GetMapping("/boarddelete")

    public String boarddelete(Integer id) {

        boardService.BoardDelete(id);

        return "redirect:/boardlist";
    }

    @GetMapping("/boardmodify/{id}")
    public String boardmodify(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("board", boardService.boardView(id));


        return "page/boardmodify";
    }

    @PostMapping("/boardupdate/{id}")
    public String boardUpdate(@PathVariable("id")Integer id,Board board){

        Board boardTemp = boardService.boardView(id);
        boardTemp.setName(board.getName());
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);

        return "redirect:/boardlist";

    }

}



