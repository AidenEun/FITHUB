package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class RiviewDTO {
    private long review_num;
    private String review_content;
    private long matching_num;
    private long matching_board_num;
    private int star_rating;
    private String user_id;
    private String regdate;
}
