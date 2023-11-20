package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ProductBoardDTO {
    private long boardNum;
    private String boardTitle;
    private String boardContent;
    private String prodImg;
    private String category;
    private String regdate;
    private String updateDate;
    private long likeCnt;
    private long bookmarkCnt;
    private long viewCnt;
    private String adminId;

}
