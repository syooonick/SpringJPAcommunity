package com.study.board.service;

import com.study.board.entity.Flea;
import com.study.board.repository.FleaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class FleaService {
    @Autowired
    private FleaRepository fleaRepository;
    // 글쓰기
    public void write(Flea flea, MultipartFile file) throws Exception {

        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files"; // 저장경로 지정

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        flea.setFilename(fileName);
        flea.setFilepath("/static/files/" + fileName);

        fleaRepository.save(flea);
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
