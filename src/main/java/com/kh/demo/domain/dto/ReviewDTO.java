package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private long reviewNum;
    private String reviewContent;
    private long boardNum;
    private int starRating;
    private String userId;
    private String regdate;
    private String updatedate;
    private Double starRatingAv;
}
