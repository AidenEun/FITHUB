package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class LikeDTO {
    private Long likeIdx;
    private String userId;
    private String boardCategory;
    private Long boardNum;
    private String regdate;
    private int heart;

}
