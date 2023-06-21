package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.entity.User;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        public Board boardView(Long bno) {
            return boardRepository.findById(bno).get();
        }

        // 특정 게시글 삭제
        public void boardDelete(Long bno) {
            boardRepository.deleteById(bno);
        }

        // 검색 기능
        public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {
            return boardRepository.findByTitleContaining(searchKeyword, pageable);
        }

        // 조회수 증가
        @Transactional
        public void increaseViewCount(Long bno) {
            Board board = boardRepository.findById(bno).orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));
            board.setViewCount(board.getViewCount() + 1);
            boardRepository.save(board);
        }


}

