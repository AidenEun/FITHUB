package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ProfileDTO {
    private String userId;
    private String sysName;
    private String orgName;
    private String profileCategory;
    /* P 프로필사진  C 경력사진 구분 */
}
