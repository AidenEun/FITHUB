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
    private double relevanceScore; // 관련성 점수 필드 추가
    private int rank;

    // 생성자, getter 및 setter 메서드 등...

    public void setRelevanceScore(double relevanceScore) {
        this.relevanceScore = relevanceScore;
    }


    public void setRank(int i) {
        this.rank = i;
    }
}
