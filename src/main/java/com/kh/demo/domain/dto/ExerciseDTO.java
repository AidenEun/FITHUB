package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ExerciseDTO {
    private Long execNum;
    private String execName;
    private String execContent;
    private String execMETS;
    private String execCalories;
    private Long viewCnt;
    private Double relevanceScore; // 관련성 점수 필드 추가

    public void setRelevanceScore(double relevanceScore) {
        this.relevanceScore = relevanceScore;
    }

}
