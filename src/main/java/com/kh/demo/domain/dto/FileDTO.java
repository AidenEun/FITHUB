package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class FileDTO {
    private Long file_num;
    private String  sys_name;
    private String  org_name;
    private String board_category;
    private Long board_num;
}
