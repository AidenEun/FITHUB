package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ProfileDTO {
    private long profileNum;
    private String userId;
    private String sysName;
    private String orgName;
}
