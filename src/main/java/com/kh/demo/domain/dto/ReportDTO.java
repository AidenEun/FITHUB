package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ReportDTO {
    private Long report_num;
    private String user_id;
    private String board_category;
    private Long report_boardnum;
    private String report_content;
    private String report_date;
}
