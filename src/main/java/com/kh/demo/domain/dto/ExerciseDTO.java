package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ExerciseDTO {
    private long exerNum;
    private String exerName;
    private String exerContent;
    private int exerCalories;
    private long viewCnt;
}
