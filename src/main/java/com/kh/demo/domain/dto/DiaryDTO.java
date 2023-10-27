package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class DiaryDTO {
    private Long diary_num;
    private String user_id;
    private int today_weight;
    private int exer_calories;
    private int food_calories;
    private Long today_exer;
    private Long today_breakfast;
    private Long today_lunch;
    private Long today_dinner;
    private Long today_snack;
    private int carbo;
    private int protein;
    private int fat;
    private String regdate;
    private String diary_title;
    private String diary_content;
    private Long mychall_num;
}
