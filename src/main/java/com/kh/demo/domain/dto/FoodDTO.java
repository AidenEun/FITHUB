package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class FoodDTO {
    private long food_num;
    private String food_name;
    private String food_brand;
    private int kcal;
    private int food_calories;
    private int carbo;
    private int protein;
    private int fat;
    private int cholesterol;
    private int dietaryfiber;
    private int sodium;
    private long view_cnt;
}
