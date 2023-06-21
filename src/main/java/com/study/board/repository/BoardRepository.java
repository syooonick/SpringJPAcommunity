package com.study.board.repository;

import com.study.board.entity.Board;
import com.study.board.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Modifying
    @Query("UPDATE Board b SET b.viewCount = b.viewCount + 1 WHERE b.bno = :bno")
    void increaseViewCount(@Param("bno") Long bno);

    @Query("SELECT b FROM Board b JOIN FETCH b.user")
    List<Board> findAll();

    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

}
