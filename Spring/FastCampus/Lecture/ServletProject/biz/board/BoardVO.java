package com.rubypaper.biz.board;

import lombok.Data;

import java.sql.Date;

@Data
public class BoardVO {
    private int seq;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private int cnt;
}
