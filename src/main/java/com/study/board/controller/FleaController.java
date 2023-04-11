package com.study.board.controller;

import com.study.board.entity.Flea;
import com.study.board.service.FleaService;
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
import org.springframework.web.multipart.MultipartFile;

@Controller

public class FleaController {

    @Autowired
    private FleaService fleaService;

    @GetMapping("/flea/write")  // localhost:8080/fleawrite
    public String fleaWriteForm() {
        return "flea/fleawrite";
    }

    @PostMapping("/flea/writedo")
    public String fleaWritePro(Flea flea, Model model, MultipartFile file) throws Exception {

        fleaService.write(flea, file);

        model.addAttribute("message", "글 작성이 완료 되었습니다.");
        model.addAttribute("searchUrl", "/flealist");

        return "message";
    }

    @GetMapping("/flealist")
    public String fleaList(Model model, @PageableDefault(page = 0, size = 10, sort = "bno", direction = Sort.Direction.DESC)
                            Pageable pageable,
                            String searchKeyword) {

        Page<Flea> list = null;

        if(searchKeyword != null) {
            list = fleaService.fleaSearchList(searchKeyword, pageable);
        } else {
            list = fleaService.flealist(pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;               //int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 2, 1);                       //int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 3, list.getTotalPages());   //int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "flea/flealist";
    }
    @GetMapping("/fleaview")
    public String fleaView(Model model, Integer bno) {
        model.addAttribute("flea", fleaService.fleaView(bno));
        return "flea/fleaview";
    }

    @GetMapping("/flea/delete")
    public String fleaDelete(Integer bno, Model model) {
        fleaService.fleaDelete(bno);

        model.addAttribute("message", "글 삭제가 완료 되었습니다.");
        model.addAttribute("searchUrl", "/flealist");

        return "message";
    }

    @GetMapping("/flea/modify/{bno}")
    public String fleaModify(@PathVariable("bno") Integer bno, Model model) {
        model.addAttribute("flea", fleaService.fleaView(bno));
        return "flea/fleamodify";
    }

    @PostMapping("/flea/update/{bno}")
    public String fleaUpdate(@PathVariable("bno") Integer bno, Flea flea, Model model, MultipartFile file) throws Exception {

        Flea fleaTemp = fleaService.fleaView(bno);
        fleaTemp.setTitle(flea.getTitle());
        fleaTemp.setContent(flea.getContent());

        fleaService.write(fleaTemp, file);

        model.addAttribute("message", "글 수정이 완료 되었습니다.");
        model.addAttribute("searchUrl", "/flealist");

        return "message";
    }
}
