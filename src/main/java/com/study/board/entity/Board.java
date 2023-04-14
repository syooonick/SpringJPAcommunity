package com.study.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

import java.util.Date;


@Entity
@Data
public class Board {
    @Id // primary key 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer bno;

    private String title;

    private String content;

    private String writer;

    private String count;

    private Date date;

}
