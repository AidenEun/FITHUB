package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private long boardNum;
    private String boardCategory;
    private String boardTitle;
    private String boardContent;
    private String userId;
    private String adminId;
    private String regdate;
    private String updateDate;
    private long likeCnt;
    private long bookmarkCnt;
    private long viewCnt;
}
