package com.study.board.entity;

import javax.persistence.*;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.format.DateTimeFormatter;



//@Entity
//@Data
//public class Board extends TimeEntity {
//    @Id // primary key 의미
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long bno;
//
//    private String title;
//
//    private String content;
//
//    private String writer;
//
//    private LocalDate date;
//
//    private int viewCount; // 조회수 필드 추가
//
//    public void increaseViewCount() {
//        this.viewCount++;
//    }
//
//    @PrePersist
//    public void bno() {
//        this.createDate = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String formattedDate = createDate.format(formatter);
//        this.date = LocalDate.parse(formattedDate, formatter);
//    }
//
//    private LocalDateTime createDate;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//}


@Entity
@Data
public class Board extends TimeEntity {
    @Id // primary key 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    private String writer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate date;

    private int viewCount; // 조회수 필드 추가

    public void increaseViewCount() {
        this.viewCount++;
    }

    @PrePersist
    public void bno() {
        this.createDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = createDate.format(formatter);
        this.date = LocalDate.parse(formattedDate, formatter);
    }

    private LocalDateTime createDate;


}

