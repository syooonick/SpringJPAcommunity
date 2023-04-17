package com.study.board.controller;


import com.study.board.entity.Board;
import com.study.board.service.BoardService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")  // localhost:8080/board/write
    public String boardWriteForm() {
        return "board/boardwrite";
    }

    @PostMapping("/board/writedo")
    public String boardWritePro(Board board, Model model) {

        boardService.write(board);

        model.addAttribute("message", "글 작성이 완료 되었습니다.");
        model.addAttribute("searchUrl", "/boardlist");

        return "message";
    }

    @GetMapping("/boardlist")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort = "bno", direction = Sort.Direction.DESC)
                            Pageable pageable,
                            String searchKeyword){
        Page<Board> list = null;

        if(searchKeyword != null) {
            list = boardService.boardSearchList(searchKeyword, pageable);
        } else {
            list = boardService.boardlist(pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;               //int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 2, 1);                       //int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 3, list.getTotalPages());   //int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/boardlist";
    }
    @GetMapping("/boardview")
    public String boardView(Model model, Integer bno) {
        model.addAttribute("board", boardService.boardView(bno));
        return "board/boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer bno, Model model) {
        boardService.boardDelete(bno);

        model.addAttribute("message", "글 삭제가 완료 되었습니다.");
        model.addAttribute("searchUrl", "/boardlist");

        return "message";
    }

    @GetMapping("/board/modify/{bno}")
    public String boardModify(@PathVariable("bno") Integer bno, Model model) {
        model.addAttribute("board", boardService.boardView(bno));
        return "board/boardmodify";
    }

    @PostMapping("/board/update/{bno}")
    public String boardUpdate(@PathVariable("bno") Integer bno, Board board, Model model){

        Board boardTemp = boardService.boardView(bno);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);

        model.addAttribute("message", "글 수정이 완료 되었습니다.");
        model.addAttribute("searchUrl", "/boardlist");

        return "message";
    }
}
