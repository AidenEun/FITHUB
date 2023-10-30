package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class BookMarkDTO {
    private Long bookmarkIdx;
    private String userId;
    private String boardCategory;
    private Long boardNum;
    private String regdate;

}
