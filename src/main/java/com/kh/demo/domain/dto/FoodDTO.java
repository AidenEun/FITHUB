package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class FoodDTO {
    private long foodNum;
    private String foodName;
    private String foodBrand;
    private int kcal;
    private int foodCalories;
    private int carbo;
    private int protein;
    private int fat;
    private int cholesterol;
    private int dietaryfiber;
    private int sodium;
    private long viewCnt;
}
