package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ExerciseDTO {
    private long exer_num;
    private String exer_name;
    private String exer_content;
    private int exer_calories;
    private long view_cnt;
}
