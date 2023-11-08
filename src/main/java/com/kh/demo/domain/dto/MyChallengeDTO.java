package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class MyChallengeDTO {
    private long mychallNum;
    private String userId;
    private long challNum;
    private String regdate;
    private int succCnt;
    private String challName;
}
