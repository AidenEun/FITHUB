package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ChallNoticeBoardDTO {
    private long challNum;
    private String challCategory;
    private String challContent;
    private int challTerm;
    private String regdate;
}
