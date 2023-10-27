package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class DiaryDTO {
    private Long diary_num;
    private String user_id;
    private int today_weight;
    private int exer_calories;
    private int food_calories;
    private String today_exer;
    private String today_breakfast;
    private String today_lunch;
    private String today_dinner;
    private String today_snack;
    private int carbo;
    private int protein;
    private int fat;
    private String regdate;
    private String diary_title;
    private String diary_content;
    private String today_chall_num;
}
