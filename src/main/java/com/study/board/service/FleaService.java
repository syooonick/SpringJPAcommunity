package com.study.board.service;

import com.study.board.entity.Flea;
import com.study.board.repository.FleaRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class FleaService {
    @Autowired
    private FleaRepository fleaRepository;
    // 글쓰기
    public void write(Flea flea, @NotNull MultipartFile imgFile) throws Exception {

        String oriImgName = imgFile.getOriginalFilename();
        String imgName = "";

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files/"; // 저장경로 지정

        UUID uuid = UUID.randomUUID();

        String savedFileName = uuid + "_" + oriImgName; // 파일명 -> imgName

        imgName = savedFileName;

        // 이미지 파일을 버퍼에 로드
        byte[] bytes;
        try (InputStream inputStream = imgFile.getInputStream()) {
            bytes = IOUtils.toByteArray(inputStream);
        }

        File saveFile = new File(projectPath, imgName);
        FileUtils.writeByteArrayToFile(saveFile, bytes); // byte[]를 파일로 저장

        flea.setImgName(imgName);
        flea.setImgPath("/files/" + imgName);
        fleaRepository.save(flea);

        // HEIC 파일을 JPEG로 변환하는 코드
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ProcessBuilder builder = new ProcessBuilder("magick", "-quality", "80", "input.heic", "JPEG:-");

        builder.redirectErrorStream(true);

        Process process = builder.start();

        IOUtils.copy(process.getInputStream(), outputStream);

        process.waitFor();

        byte[] jpegData = outputStream.toByteArray();

    }


    // 리스트 조회
    public Page<Flea> flealist(Pageable pageable) {
        return fleaRepository.findAll(pageable);
    }

    // 특정 게시글 조회
    public Flea fleaView(Integer bno) {
        return fleaRepository.findById(bno).get();
    }

    // 특정 게시글 삭제
    public void fleaDelete(Integer bno) {
        fleaRepository.deleteById(bno);
    }

    // 검색 기능
    public Page<Flea> fleaSearchList(String searchKeyword, Pageable pageable) {
        return fleaRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
