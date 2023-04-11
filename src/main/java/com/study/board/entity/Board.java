package com.study.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Board {
    @Id // primary key 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer bno;

    private String title;

    private String content;

}
