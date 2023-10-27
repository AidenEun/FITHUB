package com.kh.demo.domain.dto;

import lombok.Data;
@Data
public class Trainer_matching_boardDTO {
    private long board_num;
    private String trainer_id;
    private String board_title;
    private String board_content;
    private String trainer_zipcode;
    private String trainer_addr;
    private String trainer_addrdetail;
    private String trainer_addretc;
    private String avail_consult_time;
    private String expiration_time;
    private long like_cnt;
    private long bookmark_cnt;
    private long view_cnt;
}
