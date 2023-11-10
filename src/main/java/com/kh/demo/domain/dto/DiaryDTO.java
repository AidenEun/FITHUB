package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class DiaryDTO {
    private Long diaryNum;
    private String userId;
    private Double todayWeight;
    private Double exerCalories;
    private Double foodCalories;
    private String todayExer;
    private String todayBreakfast;
    private String todayLunch;
    private String todayDinner;
    private String todaySnack;
    private Double carbo;
    private Double protein;
    private Double fat;
    private String regdate;
    private String diaryTitle;
    private String diaryContent;
    private String todayChallNum;

}
