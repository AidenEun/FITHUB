package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class DiaryDTO {
    private Long diaryNum;
    private String userId;
    private double todayWeight;
    private double exerCalories;
    private double foodCalories;
    private String todayExer;
    private String todayBreakfast;
    private String todayLunch;
    private String todayDinner;
    private String todaySnack;
    private double carbo;
    private double protein;
    private double fat;
    private String regdate;
    private String diaryTitle;
    private String diaryContent;
    private String myChallNum;
//    private String myINGChallNum;
    private int caloriesGoal;
    private int weightGoal;
    private double nowCal;
    private double todayKGGap;
    private double bfCal;
    private double lunchCal;
    private double dinnerCal;
    private double snackCal;

}
