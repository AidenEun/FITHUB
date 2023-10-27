package com.kh.demo.domain.dto;

import lombok.Data;

@Data
public class MyChallengeDTO {
    private long mychall_num;
    private String user_id;
    private long chall_num;
    private String regdate;
    private int succ_cnt;
}
