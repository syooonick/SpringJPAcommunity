package com.study.board.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;


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

    @Lob
    @Column(name = "img_data", columnDefinition = "MEDIUMBLOB")
    private byte[] imageData;

    @NotNull
    @Column(name ="img_name")
    private String imgName;

//    @NotNull
//    @Column(name ="img_path")
//    private String imgPath;

    private String imgPath;

    public String getImgPath() {
        return Objects.requireNonNullElse(imgPath, "");
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Column(name ="writer")
    private String writer;

    @Column(name ="count")
    private String count;

    @Column(name ="date")
    private Date date;

    @Column(name ="price")
    private String price;
}
