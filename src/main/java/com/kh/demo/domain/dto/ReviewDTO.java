package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private long reviewNum;
    private String reviewContent;
    private long matchingNum;
    private long matchingBoardNum;
    private int starRating;
    private String userId;
    private String regdate;
}
