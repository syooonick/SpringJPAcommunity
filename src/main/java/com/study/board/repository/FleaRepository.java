package com.study.board.repository;

import com.study.board.entity.Flea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleaRepository extends JpaRepository<Flea, Integer> {

    Page<Flea> findByTitleContaining(String searchKeyword, Pageable pageable);
}
