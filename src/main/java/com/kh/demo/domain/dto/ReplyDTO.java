package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ReplyDTO {
    private Long replyNum;
    private String  userId;
    private String  boardCategory;
    private Long boardNum;
    private String replyContent;
    private String regdate;
    private String updateDate;
}
