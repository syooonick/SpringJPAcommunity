package com.study.board.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data
public class Flea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int bno;

    private String title;

    private String content;

    private String filename;

    private String filepath;

    private String writer;

    private String count;

    private Date date;

}
