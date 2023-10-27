package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class Product_boardDTO {
    private long board_num;
    private String board_title;
    private String board_content;
    private String product;
    private String category;
    private String regdate;
    private String update_date;
    private long like_cnt;
    private long bookmark_cnt;
    private long view_cnt;
    private String admin_id;

}
