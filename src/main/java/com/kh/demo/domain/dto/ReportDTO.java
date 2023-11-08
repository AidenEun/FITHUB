package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ReportDTO {
    private Long reportNum;
    private String userId;
    private String boardCategory;
    private Long reportBoardnum;
    private String reportContent;
    private String reportDate;
    private String reportYn;
    private String reportedUser;
}
