package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    // 글쓰기
    public void write(Board board) {
            boardRepository.save(board);
    }

    // 리스트 조회
    public Page<Board> boardlist(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }
    // 특정 게시글 조회
    public Board boardView(Integer bno) {
        return boardRepository.findById(bno).get();
    }
    // 특정 게시글 삭제
    public void boardDelete(Integer bno) {
        boardRepository.deleteById(bno);
    }
    // 검색 기능
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }
}
