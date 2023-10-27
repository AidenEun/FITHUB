package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class ChallCertBoardDTO {
    private long board_num;
    private String board_title;
    private String board_content;
    private long chall_num;
    private String start_date;
    private String finish_date;
    private String user_id;
    private String regdate;
    private String update_date;
    private long like_cnt;
    private long view_cnt;
}
