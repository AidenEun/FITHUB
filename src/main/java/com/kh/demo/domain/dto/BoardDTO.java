package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private long board_num;
    private String board_category;
    private String board_title;
    private String board_content;
    private String user_id;
    private String admin_id;
    private String regdate;
    private String update_date;
    private long like_cnt;
    private long bookmark_cnt;
    private long view_cnt;
}
