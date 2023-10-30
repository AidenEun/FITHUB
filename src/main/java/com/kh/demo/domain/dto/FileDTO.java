package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class FileDTO {
    private Long fileNum;
    private String  sysName;
    private String  orgName;
    private String boardCategory;
    private Long boardNum;
}
