package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class FoodDTO {
    private Long foodNum;
    private String foodName;
    private String foodBrand;
    private String foodQuantity;
    private String foodCalories;
    private String carbo;
    private String protein;
    private String fat;
    private String sodium;
    private String cholesterol;
    private Long viewCnt;


}
