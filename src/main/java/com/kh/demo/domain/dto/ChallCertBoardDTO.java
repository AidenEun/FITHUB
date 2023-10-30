package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ChallCertBoardDTO {
    private long boardNum;
    private String boardTitle;
    private String boardContent;
    private long challNum;
    private String startDate;
    private String finishDate;
    private String userId;
    private String regdate;
    private String updateDate;
    private long likeCnt;
    private long viewCnt;
}
