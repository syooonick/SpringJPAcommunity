package com.study.board.controller;

import com.study.board.entity.Flea;
import com.study.board.service.FleaService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller

public class FleaController {

    @Autowired
    private FleaService fleaService;

    @GetMapping("/flea/write")  // localhost:8080/fleawrite
    public String fleaWriteForm() {
        return "flea/fleawrite";
    }

    @PostMapping("/flea/writedo")
    public String fleaWritePro(Flea flea, Model model, MultipartFile imgFile) throws Exception {
        fleaService.write(flea, imgFile);

        if (imgFile != null) {

            try {
                imgFile.getOriginalFilename();

            } catch (NullPointerException e) {

                throw new Exception("이미지 파일을 선택해주세요.");

            }

        } else {
            throw new Exception("상품을 등록해주세요.");
        }

        model.addAttribute("message", "글 작성이 완료 되었습니다.");
        model.addAttribute("searchUrl", "/flealist");

        return "message";
    }

    @GetMapping("/flealist")
    public String fleaList(Model model, @PageableDefault(page = 0, size = 12, sort = "bno", direction = Sort.Direction.DESC)
    Pageable pageable, String searchKeyword) {

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
    public String fleaUpdate(@PathVariable("bno") Integer bno, Flea flea, Model model, MultipartFile imgFile) throws Exception {

        Flea fleaTemp = fleaService.fleaView(bno);

        fleaTemp.setTitle(flea.getTitle());
        fleaTemp.setContent(flea.getContent());
        fleaTemp.setPrice(flea.getPrice());
        fleaTemp.setContent(flea.getImgPath());

        fleaService.write(fleaTemp, imgFile);

        model.addAttribute("message", "글 수정이 완료 되었습니다.");
        model.addAttribute("searchUrl", "/flealist");

        return "message";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("imgFile") MultipartFile imgFile) {
        if (imgFile.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // HEIC 파일 처리
            byte[] bytes = imgFile.getBytes();

            return ResponseEntity.ok().body("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @GetMapping("/image")
    public void getImage(HttpServletResponse response) throws Exception {
        // HEIC 파일을 JPEG로 변환하는 코드
        File heicFile = new File("/static/files/");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ProcessBuilder builder = new ProcessBuilder("magick", "-quality", "80", heicFile.getAbsolutePath(), "JPEG:-");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        IOUtils.copy(process.getInputStream(), outputStream);
        process.waitFor();
        byte[] jpegData = outputStream.toByteArray();

        // HTTP 응답으로 JPEG 데이터 전송
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        response.getOutputStream().write(jpegData);
    }



}