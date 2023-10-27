package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class LikeDTO {
    private Long like_idx;
    private String user_id;
    private String board_category;
    private Long board_num;
    private String update_date;

}
