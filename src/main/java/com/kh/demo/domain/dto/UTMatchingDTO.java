package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class UTMatchingDTO {
    private long matchingNum;
    private String userId;
    private String trainerId;
    private String userCheck;
    private String trainerCheck;
    private String matchingDate;
}
