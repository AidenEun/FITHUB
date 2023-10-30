package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class DiaryDTO {
    private Long diaryNum;
    private String userId;
    private int todayWeight;
    private int exerCalories;
    private int foodCalories;
    private String todayExer;
    private String todayBreakfast;
    private String todayLunch;
    private String todayDinner;
    private String todaySnack;
    private int carbo;
    private int protein;
    private int fat;
    private String regdate;
    private String diaryTitle;
    private String diaryContent;
    private String todayChallNum;
}
