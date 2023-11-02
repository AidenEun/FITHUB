package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ChallNoticeBoardDTO {
    private long challNum;
    private String challCategory;
    private String challName;
    private String challContent;
    private int challTerm;
    private String regdate;

    //재우
    private String subRegdate;
    private int succCnt;
}
