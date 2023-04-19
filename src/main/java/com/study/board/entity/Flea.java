package com.study.board.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@Table(name = "flea")
public class Flea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name ="bno")
    private int bno;

    @Column(name ="title")
    private String title;

    @Column(name ="content")
    private String content;

    @Column(name ="img_name")
    private String imgName;

    @Column(name ="img_path")
    private String imgPath;

    @Column(name ="writer")
    private String writer;

    @Column(name ="count")
    private String count;

    @Column(name ="date")
    private Date date;

//    private int price;

}
