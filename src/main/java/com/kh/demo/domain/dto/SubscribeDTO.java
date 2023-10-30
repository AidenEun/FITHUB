package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class SubscribeDTO {
    private Long subNum;
    private String userId;
    private String trainerId;
    private String  subDate;
}
