package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ReplyDTO {
    private Long reply_num;
    private String  user_id;
    private String  board_category;
    private Long board_num;
    private String reply_content;
    private String regdate;
    private String update_date;
}
